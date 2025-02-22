package com.accountmasivebackend.entities;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @autor DavidPro
 */
@Entity
@Table(name = "accounts")
@NamedQueries({})
public class Accounts {

    @Id
    @Column(name = "codeAccount")
    private Integer codeAccount;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "identification")
    private String identification;
    @Column(name = "age")
    private Integer age;
    @Column(name = "dateTransaction")
    private Date dateTransaction;
    @Column(name = "dateCreateAccount")
    private Date dateCreateAccount;
    @Column(name = "email")
    private String email;

    public Accounts() {
    }

    public Integer getCodeAccount() {
        return codeAccount;
    }

    public void setCodeAccount(Integer codeAccount) {
        this.codeAccount = codeAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Date getDateCreateAccount() {
        return dateCreateAccount;
    }

    public void setDateCreateAccount(Date dateCreateAccount) {
        this.dateCreateAccount = dateCreateAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
