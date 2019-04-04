package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;

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

public class ApiClient {

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    private final ObjectWriter objectWriter = Application.createObjectMapper().writer();
    private final JsonFactory jsonFactory = objectWriter.getFactory();

    private final URI baseUri;

    public ApiClient(URI baseUri) {
        this.baseUri = baseUri;
    }

    public HttpResponse<InputStream> makePostRequest(String resourcePath, Object data) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpRequest httpRequest = builder
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
                .uri(baseUri.resolve(resourcePath))
                .POST(createStreamingBody(data))
                .build();

        return client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
    }

    /*
    1) Simple code for creating a Json body as a String. Inefficient.
     */
    private HttpRequest.BodyPublisher createSimpleBody(Object requestBody) throws IOException {
        String json = objectWriter.writeValueAsString(requestBody);
        return HttpRequest.BodyPublishers.ofString(json);
    }

    /*
    2) Creates a Json body without wasting time on creating a String representation of the body
     */
    private HttpRequest.BodyPublisher createBody(Object requestBody) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator jGenerator = jsonFactory.createGenerator(stream, JsonEncoding.UTF8);

        objectWriter.writeValue(jGenerator, requestBody);
        return HttpRequest.BodyPublishers.ofByteArray(stream.toByteArray());
    }

    /*
    3) Creates a Json body using streaming (most efficient, necessary for big streams)
    */
    private HttpRequest.BodyPublisher createStreamingBody(Object requestBody) {
        return HttpRequest.BodyPublishers.ofInputStream(new JsonBodyPublisher(requestBody));
    }

}
