package com.accountmasivebackend.dto;

import com.accountmasivebackend.entities.Accounts;

import java.util.List;

public class ProcessFile {
    private List<Accounts> sucessfulList;
    private List<String> errorList;

    public ProcessFile() {
    }

    public List<Accounts> getSucessfulList() {
        return sucessfulList;
    }

    public void setSucessfulList(List<Accounts> sucessfulList) {
        this.sucessfulList = sucessfulList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
