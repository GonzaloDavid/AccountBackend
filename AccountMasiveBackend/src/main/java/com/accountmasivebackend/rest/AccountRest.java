package com.accountmasivebackend.rest;

import com.accountmasivebackend.dto.ResponseUploadFile;
import com.accountmasivebackend.entities.LoadFile;
import com.accountmasivebackend.service.LoadfileServiceImpl;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

import static com.accountmasivebackend.util.Constants.PATH_FILE_ACCOUNT;

/**
 *
 * @author DavidPro
 */
@Stateless
@Path("bulkAccount")
public class AccountRest extends AbstractFacade<LoadFile> {

    //@EJB
    private LoadfileServiceImpl loadfileService=new LoadfileServiceImpl();
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

    /**
     * Webservice carga archivo en ruta de servidor y retorna ruta
     * @param fileObject
     * @param namefile
     * @return
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseUploadFile processFile(@FormDataParam("file") InputStream fileObject,
                                          @FormDataParam("namefile") String namefile) {

       return loadfileService.uploadFile(fileObject, namefile);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
