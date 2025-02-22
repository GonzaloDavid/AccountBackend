package com.accountmasivebackend.service;

import com.accountmasivebackend.dto.ResponseUploadFile;
import jakarta.ejb.Singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static com.accountmasivebackend.util.Constants.PATH_FILE_ACCOUNT;

/**
 *
 * @author DavidPro
 */
@Singleton
public class LoadfileServiceImpl  {


    public ResponseUploadFile uploadFile(InputStream fileObject, String namefile) {
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
                while ((bytesRead = fileObject.read(buffer)) != -1) {
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
}
