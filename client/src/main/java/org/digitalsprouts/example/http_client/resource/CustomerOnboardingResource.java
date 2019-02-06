package org.digitalsprouts.example.http_client.resource;

import org.digitalsprouts.example.http_client.api.CustomerOnboarding;
import org.digitalsprouts.example.http_client.service.CustomerOnboardingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customerOnboarding")
public class CustomerOnboardingResource {

    @Inject
    private CustomerOnboardingService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTomatoes() {
        CustomerOnboarding current = service.onboardNewCustomer();

        return Response.ok(current).build();
    }

}