package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
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
    private static ObjectReader objectReader;
    private static JsonFactory jsonFactory;

    @BeforeAll
    static void setUp() throws Exception {
        System.setProperty("example.client.test.mode", "true");

        testServer = new TestServer();
        testServer.start();

        apiClient = new ApiClient(testServer.getBaseUri());
        ObjectMapper objectMapper = Application.createObjectMapper();
        objectReader = objectMapper.reader();
        jsonFactory = objectMapper.getFactory();
    }

    @AfterAll
    static void tearDown() {
        testServer.stop();
    }

    @Test
    void onboarding_serviceSuccess_canBeDeserialized() throws IOException, InterruptedException {
        HttpResponse<InputStream> rawResponse = apiClient.makePostRequest("customerOnboarding", createDummyRequest());

        JsonParser jsonParser = jsonFactory.createParser(rawResponse.body());
        CustomerOnboardingResponse response = objectReader.readValue(jsonParser, CustomerOnboardingResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getAgreementNumber()).isNotNull();
        assertThat(response.getAgreementStatus()).isEqualTo(Agreement.AgreementStatus.DISPATCHED);
    }
}
