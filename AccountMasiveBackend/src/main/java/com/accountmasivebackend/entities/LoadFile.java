package com.accountmasivebackend.entities;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @autor DavidPro
 */
@Entity
@Table(name = "loadfile")
@NamedQueries({})
public class LoadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seqLoadFile")
    private Long seqLoadFile;
    @Column(name = "nameLoadFile")
    private String nameLoadFile;
    @Column(name = "dateProcess")
    private Date dateProcess;
    @Column(name = "status")
    private String status;
    @Column(name = "dateCreate")
    private Date dateCreate;
    @Column(name = "dateLastModify")
    private Date dateLastModify;
    @Column(name = "userCreate")
    private Long userCreate;
    @Column(name = "userLasModify")
    private Long userLasModify;

    public LoadFile() {
    }

    public Long getSeqLoadFile() {
        return seqLoadFile;
    }

    public void setSeqLoadFile(Long seqLoadFile) {
        this.seqLoadFile = seqLoadFile;
    }

    public String getNameLoadFile() {
        return nameLoadFile;
    }

    public void setNameLoadFile(String nameLoadFile) {
        this.nameLoadFile = nameLoadFile;
    }

    public Date getDateProcess() {
        return dateProcess;
    }

    public void setDateProcess(Date dateProcess) {
        this.dateProcess = dateProcess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateLastModify() {
        return dateLastModify;
    }

    public void setDateLastModify(Date dateLastModify) {
        this.dateLastModify = dateLastModify;
    }

    public Long getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Long userCreate) {
        this.userCreate = userCreate;
    }

    public Long getUserLasModify() {
        return userLasModify;
    }

    public void setUserLasModify(Long userLasModify) {
        this.userLasModify = userLasModify;
    }
}
