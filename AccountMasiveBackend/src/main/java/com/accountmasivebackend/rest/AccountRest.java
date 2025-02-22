package com.accountmasivebackend.rest;

import com.accountmasivebackend.dto.ResponseUploadFile;
import com.accountmasivebackend.entities.LoadFile;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static com.accountmasivebackend.util.Constants.PATH_FILE_ACCOUNT;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void processFile(){

    }

    /**
     * Webservice carga archivo en ruta de servidor y retorna ruta
     * @param uploadedInputStream
     * @param fileName
     * @param namefile
     * @return
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseUploadFile processFile(@FormDataParam("file") InputStream uploadedInputStream,
                                          @FormDataParam("file") String fileName,
                                          @FormDataParam("namefile") String namefile) {

        ResponseUploadFile response=new ResponseUploadFile();
        try {

            String uploadDir = PATH_FILE_ACCOUNT;

            String filePath = uploadDir + namefile;
            File outputFile = new File(filePath);

            File parentDir = outputFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = uploadedInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            response.setCode("00");
            response.setMessage("Archivo cargado exitosamente");
            response.setPathFile(filePath);
            return response;
        } catch (Exception e) {
            response.setCode("01");
            response.setMessage("Error en el proceso de carga: "+ e.getMessage());
            return response;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
