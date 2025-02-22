package com.accountmasivebackend.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author DavidPro
 */
@Path("test")
public class AccountRest {

    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

}
