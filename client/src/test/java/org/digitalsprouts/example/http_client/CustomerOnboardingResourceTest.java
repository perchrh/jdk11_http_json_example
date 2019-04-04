package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.digitalsprouts.example.backend.api.Agreement;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.digitalsprouts.example.http_client.Testdata.createDummyRequest;

public class CustomerOnboardingResourceTest {

    private static TestServer testServer;
    private static ApiClient apiClient;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws Exception {
        System.setProperty("example.client.test.mode", "true");

        testServer = new TestServer();
        testServer.start();

        apiClient = new ApiClient(testServer.getBaseUri());
        objectMapper = Application.createObjectMapper();
    }

    @AfterAll
    static void tearDown() {
        testServer.stop();
    }

    @Test
    void onboarding_serviceSuccess_canBeDeserialized() throws IOException, InterruptedException {
        HttpResponse<InputStream> rawResponse = apiClient.makePostRequest("customerOnboarding", createDummyRequest());

        CustomerOnboardingResponse response = objectMapper.readValue(rawResponse.body(), CustomerOnboardingResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getAgreementNumber()).isNotNull();
        assertThat(response.getAgreementStatus()).isEqualTo(Agreement.AgreementStatus.DISPATCHED);
    }
}
