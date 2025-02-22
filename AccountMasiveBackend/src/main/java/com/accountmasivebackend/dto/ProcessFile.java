package com.accountmasivebackend.dto;

import java.util.List;

public class ProcessFile {
    private List<String> sucessfulList;
    private List<String> errorList;

    public ProcessFile() {
    }

    public List<String> getSucessfulList() {
        return sucessfulList;
    }

    public void setSucessfulList(List<String> sucessfulList) {
        this.sucessfulList = sucessfulList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
