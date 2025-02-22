package com.accountmasivebackend.entities;

import jakarta.persistence.*;

/**
 * @autor DavidPro
 */
@Entity
@Table(name = "accounts")
@NamedQueries({})
public class Accounts {

    @Id
    @Column(name = "codeAccount")
    private String codeAccount;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "identification")
    private String identification;
    @Column(name = "age")
    private String age;
    @Column(name = "dateTransaction")
    private String dateTransaction;
    @Column(name = "dateCreateAccount")
    private String dateCreateAccount;
    @Column(name = "email")
    private String email;

    public Accounts() {
    }

    public String getCodeAccount() {
        return codeAccount;
    }

    public void setCodeAccount(String codeAccount) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getDateCreateAccount() {
        return dateCreateAccount;
    }

    public void setDateCreateAccount(String dateCreateAccount) {
        this.dateCreateAccount = dateCreateAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
