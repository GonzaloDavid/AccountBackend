package com.accountmasivebackend.entities;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @autor DavidPro
 */
@Entity
@Table(name = "loadfileDet")
@NamedQueries({})
public class loadfileDet {

    @Id
    @Column(name = "seqLoadFile")
    private Long seqLoadFile;
    @Column(name = "seqLoadfileDet")
    private Long seqLoadfileDet;
    @Column(name = "nameCustomer")
    private String nameCustomer;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "identification")
    private String identification;
    @Column(name = "age")
    private Long age;
    @Column(name = "dateTransaction")
    private Date dateTransaction;
    @Column(name = "email")
    private String email;

    public loadfileDet() {
    }

    public Long getSeqLoadFile() {
        return seqLoadFile;
    }

    public void setSeqLoadFile(Long seqLoadFile) {
        this.seqLoadFile = seqLoadFile;
    }

    public Long getSeqLoadfileDet() {
        return seqLoadfileDet;
    }

    public void setSeqLoadfileDet(Long seqLoadfileDet) {
        this.seqLoadfileDet = seqLoadfileDet;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
