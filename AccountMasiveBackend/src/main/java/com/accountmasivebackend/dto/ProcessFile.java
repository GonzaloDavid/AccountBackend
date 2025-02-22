package com.accountmasivebackend.dto;

import com.accountmasivebackend.entities.Accounts;

import java.util.List;

public class ProcessFile {
    private List<Accounts> sucessfulList;
    private List<FileAccount> errorList;

    public ProcessFile() {
    }

    public List<Accounts> getSucessfulList() {
        return sucessfulList;
    }

    public void setSucessfulList(List<Accounts> sucessfulList) {
        this.sucessfulList = sucessfulList;
    }

    public List<FileAccount> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<FileAccount> errorList) {
        this.errorList = errorList;
    }
}
