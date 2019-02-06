package org.digitalsprouts.example.http_client;

import org.digitalsprouts.example.http_client.resource.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.digitalsprouts.example.http_client.service.CustomerOnboardingService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;

@ApplicationPath("/")
public class Application extends ResourceConfig {

    public Application() {
        register(Root.class);
        register(org.digitalsprouts.example.http_client.resource.CustomerOnboardingResource.class);

        final ObjectMapper objectMapper = createObjectMapper();
        setupObjectMapper(objectMapper);

        register(getBinder());
    }

    protected AbstractBinder getBinder() {
        CustomerOnboardingService customerOnboardingServiceSingleton = new CustomerOnboardingService();
        return new AbstractBinder() {
            @Override
            protected void configure() {
                bind(customerOnboardingServiceSingleton).to(CustomerOnboardingService.class);
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
