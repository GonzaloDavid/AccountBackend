package com.accountmasivebackend.rest;

import com.accountmasivebackend.dto.ProcessFile;
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

/**
 *
 * @author DavidPro
 */
@Stateless
@Path("bulkAccount")
public class AccountRest extends AbstractFacade<LoadFile> {

    @EJB
    private LoadfileServiceImpl loadfileService;
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
    @Path("uploadloadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseUploadFile uploadloadFile(@FormDataParam("file") InputStream fileObject,
                                          @FormDataParam("namefile") String namefile) {

       return loadfileService.uploadFile(fileObject, namefile);

    }

    @POST
    @Path("processfile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProcessFile processFile(@QueryParam("pathFile") String pathFile) {

        return loadfileService.manageProcessFile(pathFile);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
