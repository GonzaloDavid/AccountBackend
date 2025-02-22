package com.accountmasivebackend.service;

import com.accountmasivebackend.dao.AccountDAO;
import com.accountmasivebackend.dao.GenericDAO;
import com.accountmasivebackend.entities.Accounts;
import com.accountmasivebackend.entities.LoadFile;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DavidPro
 */
@Stateless
public class AccountServiceImpl extends GenericDAO<Accounts> {

    @EJB
    private AccountDAO accountDAO;
    public AccountServiceImpl(){
        super(Accounts.class);
    }

    /**
     * Genera el codigo unico de 9 digitos para la cuenta
     * @param numberRecords
     * @return
     */
    public List<Integer> generateUniqueCodeAccount(int numberRecords)
    {
        int inicialValue=100000000;
        List<Integer> accountCodeList=new ArrayList<>();

        //Consulta si existe alguna cuenta creada
        Integer maxValueAccount=accountDAO.getMaxAccountCode();
        if(maxValueAccount == 0)
        {
            //Sino existe crea la cuenta desde el la cuenta cero 100000000
            for(int i=inicialValue+1; i<(inicialValue + numberRecords +1) ; i++ )
            {
                accountCodeList.add(i);
            }
        }else{

            //Verifica la cuenta maxima creada y asigna al numero de cuentas solicitadas
            for(int i=maxValueAccount +1 ; i<(maxValueAccount + numberRecords + 1) ; i++ )
            {
                accountCodeList.add(i);
            }
        }
        return accountCodeList;
    }





}
