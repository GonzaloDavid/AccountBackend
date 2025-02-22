package com.accountmasivebackend.rest;

import com.accountmasivebackend.entities.LoadFile;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

/**
 *
 * @author DavidPro
 */
@Stateless
@Path("bulkAccount")
public class AccountRest extends AbstractFacade<LoadFile> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public AccountRest() {
        super(LoadFile.class);
    }

    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    @POST
    @Path("upload1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void processFile(){

    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response processFile(@FormDataParam("file") InputStream uploadedInputStream,
                                @FormDataParam("file") String fileName) {
        try {

            String uploadDir = "/Users/davidmac/Documents/Trabajo/PERSONAL_PROYECTS/AccountMasiveBackend/AccountBackend/AccountMasiveBackend/";

            return Response.ok("Archivo " + fileName + " cargado exitosamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
