package org.digitalsprouts.example.http_client.resource;

import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;
import org.digitalsprouts.example.http_client.service.CustomerOnboardingService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customerOnboarding")
public class CustomerOnboardingResource {

    @Inject
    private CustomerOnboardingService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response onboardNewCustomer(final CustomerOnboardingRequest request) {
        CustomerOnboardingResponse result = service.onboardNewCustomer(request);

        return Response.ok(result).build();
    }

}