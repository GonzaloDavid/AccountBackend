package com.accountmasivebackend.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(CorsApp.class);
        resources.add(AccountRest.class);
        resources.add(MultiPartFeature.class);
        return resources;
    }

}
