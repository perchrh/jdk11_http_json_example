package org.digitalsprouts.example.http_client;

import org.digitalsprouts.example.http_client.json.CustomerOnboarding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerOnboardingResourceTest {

    private static TestServer testServer;
    private static HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    private static ObjectMapper objectMapper;


    @BeforeAll
    static void setUp() throws Exception {
        testServer = new TestServer();
        testServer.start();

        objectMapper = Application.createObjectMapper();
    }

    @Test
    void onboarding_serviceSuccess_canBeDeserialized() throws IOException, InterruptedException {
        HttpResponse<InputStream> response = makeGetRequest("customerOnboarding");

        CustomerOnboarding customerOnboarding = objectMapper.readValue(response.body(), CustomerOnboarding.class);

        assertThat(customerOnboarding).isNotNull();

        assertThat(customerOnboarding.getEmailAddress()).isNotNull();
        assertThat(customerOnboarding.getNationalRegistrationId()).isNotNull();
        assertThat(customerOnboarding.getPhoneNumber()).isNotNull();
        assertThat(customerOnboarding.getProductSpecification()).isNotNull();

        assertThat(customerOnboarding.getPhoneNumber()).isEqualTo("+155532771");
    }

    private HttpResponse<InputStream> makeGetRequest(String resourcePath) throws IOException, InterruptedException {
        URI baseUri = testServer.getBaseUri();
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        HttpRequest httpRequest = builder
                .header("X-CORRELATION-ID", UUID.randomUUID().toString())
                .uri(baseUri.resolve(resourcePath))
                .GET()
                .build();

        return client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
    }

    @AfterAll
    static void tearDown() {
        testServer.stop();
    }
}
