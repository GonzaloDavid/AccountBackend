package com.accountmasivebackend.dto;

public class ResponseUploadFile {
    private String code;
    private String message;
    private String pathFile;
    private ProcessFile resultFile;

    public ResponseUploadFile() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public ProcessFile getResultFile() {
        return resultFile;
    }

    public void setResultFile(ProcessFile resultFile) {
        this.resultFile = resultFile;
    }
}
