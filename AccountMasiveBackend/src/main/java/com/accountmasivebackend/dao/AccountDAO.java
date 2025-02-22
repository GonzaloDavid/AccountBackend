package com.accountmasivebackend.dao;

import com.accountmasivebackend.entities.Accounts;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;

/**
 *
 * @author DavidPro
 */
@Stateless
public class AccountDAO extends GenericDAO<Accounts>{

    public AccountDAO(){
        super(Accounts.class);
    }

    public Integer getMaxAccountCode()
    {
        String sql
                = " SELECT MAX (a.codeAccount ) "
                + " FROM Accounts a ";

        Query query = this.em.createQuery(sql);
        List<Integer> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return 0;
        }else{
            if(resultList.get(0) ==null)
            {
                return 0;
            }else{
                return resultList.get(0).intValue();
            }
        }
    }

    public void createAccount(Integer codeAccount,String name, String lastName, String identification, Integer age, Date dateTransaction, String email){

        Accounts accounts=new Accounts();
        accounts.setCodeAccount(codeAccount);
        accounts.setName(name);
        accounts.setLastName(lastName);
        accounts.setIdentification(identification);
        accounts.setAge(age);
        accounts.setDateTransaction(dateTransaction);
        accounts.setEmail(email);

        insert(accounts);

    }

}
