package com.accountmasivebackend.dto;

import com.accountmasivebackend.entities.Accounts;
import com.accountmasivebackend.entities.LoadFile;

import java.util.List;

public class ProcessFile {
    private List<Accounts> sucessfulList;
    private List<FileAccount> errorList;
    private LoadFile loadfileInserted;

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

    public LoadFile getLoadfileInserted() {
        return loadfileInserted;
    }

    public void setLoadfileInserted(LoadFile loadfileInserted) {
        this.loadfileInserted = loadfileInserted;
    }
}
