package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.digitalsprouts.example.backend.service.AgreementService;
import org.digitalsprouts.example.backend.service.CustomerService;
import org.digitalsprouts.example.backend.service.LetterService;
import org.digitalsprouts.example.http_client.resource.CustomerOnboardingResource;
import org.digitalsprouts.example.http_client.resource.Root;
import org.digitalsprouts.example.http_client.service.CustomerOnboardingService;
import org.digitalsprouts.example.http_client.service.CustomerOnboardingServiceImpl;
import org.digitalsprouts.example.http_client.service.MockCustomerOnboardingService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;

@ApplicationPath("/")
public class Application extends ResourceConfig {

    public Application() {
        register(Root.class);
        register(CustomerOnboardingResource.class);

        final ObjectMapper objectMapper = createObjectMapper();
        setupObjectMapper(objectMapper);

        register(getBinder());
    }

    protected AbstractBinder getBinder() {
        AgreementService agreementService = new AgreementService();
        CustomerService customerService = new CustomerService();
        LetterService letterService = new LetterService();
        return new AbstractBinder() {
            @Override
            protected void configure() {
                String testModeRaw = System.getProperty("example.client.test.mode", "false");
                boolean isTestMode = Boolean.valueOf(testModeRaw);

                if (isTestMode) {
                    bind(new MockCustomerOnboardingService()).to(CustomerOnboardingService.class);
                } else {
                    bind(new CustomerOnboardingServiceImpl(agreementService, customerService, letterService)).to(CustomerOnboardingService.class);
                }
            }
        };
    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    private ObjectMapper setupObjectMapper(ObjectMapper objectMapper) {

        register(new ContextResolver<ObjectMapper>() {
            @Override
            public ObjectMapper getContext(final Class<?> type) {
                return objectMapper;
            }
        });

        return objectMapper;
    }
}
