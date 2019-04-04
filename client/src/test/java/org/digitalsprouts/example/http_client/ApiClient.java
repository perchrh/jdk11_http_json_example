package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static java.net.http.HttpClient.Version.*;

public class ApiClient {

    private final HttpClient client = HttpClient.newBuilder()
            .version(HTTP_2)
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    private final ObjectMapper objectMapper = Application.createObjectMapper();
    private final JsonFactory jsonFactory = new JsonFactory();

    private final URI baseUri;

    public ApiClient(URI baseUri) {
        this.baseUri = baseUri;
    }

    private HttpRequest.BodyPublisher createBody(Object dummyRequest) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator jGenerator = jsonFactory.createGenerator(stream, JsonEncoding.UTF8);

        objectMapper.writeValue(jGenerator, dummyRequest);
        return HttpRequest.BodyPublishers.ofByteArray(stream.toByteArray());
    }

    public HttpResponse<InputStream> makePostRequest(String resourcePath, Object data) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpRequest httpRequest = builder
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
                .uri(baseUri.resolve(resourcePath))
                .POST(createBody(data))
                .build();

        return client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
    }

}
