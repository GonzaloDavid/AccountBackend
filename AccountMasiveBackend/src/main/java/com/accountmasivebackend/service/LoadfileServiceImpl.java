package com.accountmasivebackend.service;

import com.accountmasivebackend.dao.AccountDAO;
import com.accountmasivebackend.dao.GenericDAO;
import com.accountmasivebackend.dao.LoadfileDAO;
import com.accountmasivebackend.dto.FileAccount;
import com.accountmasivebackend.dto.ProcessFile;
import com.accountmasivebackend.dto.ResponseUploadFile;
import com.accountmasivebackend.entities.Accounts;
import com.accountmasivebackend.entities.LoadFile;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.accountmasivebackend.util.Constants.PATH_FILE_ACCOUNT;

/**
 *
 * @author DavidPro
 */
@Stateless
public class LoadfileServiceImpl extends GenericDAO<LoadFile> {
    @EJB
    private AccountDAO accountDAO;
    @EJB
    private LoadfileDAO loadfileDAO;
    @EJB
    private AccountServiceImpl accountService;

    public LoadfileServiceImpl(){
        super(LoadFile.class);
    }

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

            ProcessFile resultFile=manageProcessFile(filePath, namefile);
            response.setResultFile(resultFile);

            return response;
        } catch (Exception e) {
            response.setCode("01");
            response.setMessage("Error en el proceso de carga: "+ e.getMessage());
            return response;
        }
    }

    /**
     * Administra el flujo del procesamiento de archivos
     * @param filePath
     * @param namefile
     * @return
     */
    public ProcessFile manageProcessFile(String filePath, String namefile)
    {
        //Crear cabecera de ejecucion de archivos
        LoadFile loadfileInserted=loadfileDAO.createLoadfileObject(namefile);

        //Obtener la informacion del archivo
        List<FileAccount> fileList=processFile(filePath);

        //Validar los campos del archivo
        ProcessFile resultFile=validateFields(fileList);

        List<Integer> accountCodeList= accountService.generateUniqueCodeAccount(resultFile.getSucessfulList().size());

        int count=0;
        for(Accounts accountsObj:resultFile.getSucessfulList())
        {
            Integer codeAccount= accountCodeList.get(count);
            accountsObj.setCodeAccount(codeAccount);

            //Insertar de manera masiva las cuentas
            accountDAO.createAccount(codeAccount, accountsObj.getName(), accountsObj.getLastName(), accountsObj.getLastName(), accountsObj.getAge(), accountsObj.getDateTransaction(), accountsObj.getEmail());

            count++;
        }

        resultFile.setLoadfileInserted(loadfileInserted);
        return resultFile;

    }

    /**
     * Abre el archivo y lo carga en memoria en una lista
     * @param pathFile
     * @return
     */
    public List<FileAccount> processFile(String pathFile)
    {
        BufferedReader lector = null;
        List<FileAccount> processedFile=new ArrayList<>();
        try {
            System.out.println("[*****Procesando archivo *****]");
            lector = new BufferedReader(new FileReader(pathFile));
            String linea;

            int count=1;

            while ((linea = lector.readLine()) != null) {
                FileAccount f=new FileAccount();
                f.setRowNumber(count);
                f.setRowValue(linea);
                processedFile.add(f);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  processedFile;
    }

    public ProcessFile validateFields(List<FileAccount> processedFile)
    {
        List<Accounts> sucessfulList=new ArrayList<>();
        List<FileAccount> errorList=new ArrayList<>();

        for(FileAccount lineObj: processedFile)
        {
             boolean existError=false;
            //Validamos que la linea del archivo no este vacio
            if(lineObj.equals(""))
            {
                String message="Linea "+lineObj.getRowValue() + " no contiene informacion";
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            String[] rowValueList=lineObj.getRowValue().split(",");
            if(rowValueList.length ==0 )
            {
                String message="Linea "+ lineObj.getRowValue() + " no contiene todos los elementos";
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            //Obtener el valor de los campos
            String nameCustomer= rowValueList[0];
            String lastName= rowValueList[1];
            String identification= rowValueList[2];
            String age= rowValueList[3];
            String dateTransaction= rowValueList[4];
            String email= rowValueList[5];

            if(!validateemptyfields(nameCustomer))
            {
                String message="Linea "+ lineObj.getRowNumber() + " en campo nombre no existe : "+ nameCustomer;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateemptyfields(lastName))
            {
                String message="Linea "+ lineObj.getRowNumber() + " en campo lastName no existe : "+ lastName;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateemptyfields(identification))
            {
                String message="Linea "+ lineObj.getRowNumber() + " en campo identification no existe : "+ identification;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }
            if(!validateemptyfields(dateTransaction))
            {
                String message="Linea "+ lineObj.getRowNumber() + " en campo dateTransaction no existe : "+ dateTransaction;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateemptyfields(email))
            {
                String message="Linea "+ lineObj.getRowNumber() + " en campo email no existe : "+ email;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateLettersOnly(nameCustomer)){
                String message="Linea "+ lineObj.getRowNumber() + " en campo nombre contiene valores invalidos : "+ nameCustomer;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateLettersOnly(lastName)){
                String message="Linea "+ lineObj.getRowNumber() + " en campo apellido contiene valores invalidos : "+ lastName;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateNumberOnly(age)){
                String message="Linea "+ lineObj.getRowNumber() + " en campo edad contiene valores invalidos : "+ age;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!validateEmail(email)){
                String message="Linea "+ lineObj.getRowNumber() + " en campo email contiene valores invalidos : "+ email;
                FileAccount f=new FileAccount();
                f.setRowNumber(lineObj.getRowNumber());
                f.setRowValue(message);

                errorList.add(f);
                existError=true;
                System.out.println(message);
            }

            if(!existError)
            {
                Accounts a=new Accounts();
                a.setName(nameCustomer);
                a.setIdentification(identification);
                a.setLastName(lastName);
                a.setAge(Integer.parseInt(age));
                a.setDateTransaction(parserDateTransaction(dateTransaction));
                a.setEmail(email);
                sucessfulList.add(a);
            }
        }
        ProcessFile p=new ProcessFile();
        p.setSucessfulList(sucessfulList);
        p.setErrorList(errorList);
        return p;

    }

    /**
     * Convierte el formato de string a tipo date
     * @param dateTransactionStr
     * @return
     */
    private Date parserDateTransaction(String dateTransactionStr){

        LocalDate dateTransactionTemp = LocalDate.parse(dateTransactionStr);
        Date dateTransaction = Date.from(dateTransactionTemp.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateTransaction;
    }

    /**
     * Verifica que los campos no contengan valores vacios
     * @param strToValidate
     * @return
     */
    private boolean validateemptyfields(String strToValidate){
        boolean isValid=true;
        if(strToValidate.equals(""))
        {
            isValid=false;
        }
        return isValid;
    }

    /**
     * Valida que los nombre / apellidos solo contengas letras
     * @param strToValidate
     * @return
     */
    private boolean validateLettersOnly(String strToValidate)
    {
        return strToValidate.matches("[a-zA-Z]+");
    }

    /**
     * Valida que solo haya numeros
     * @param strToValidate
     * @return
     */
    private boolean validateNumberOnly(String strToValidate)
    {
        return strToValidate.matches("\\d+");
    }

    /**
     * Valida que el email tenga el formato
     * @param email
     * @return
     */
    private boolean validateEmail(String email)
    {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
