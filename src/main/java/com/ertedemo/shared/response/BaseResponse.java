package com.ertedemo.shared.response;

import java.util.List;

public class BaseResponse<T> {
    private T objectResult;
    private List<T> listResult;
    private String errorCode;
    private String errorMessage;
    private boolean isError;

    public BaseResponse() {}

    public BaseResponse(T objectResult) {
        this.objectResult = objectResult;
        this.isError = false;
    }

    public BaseResponse(T objectResult, String errorCode, String errorMessage, boolean isError) {
        this.objectResult = objectResult;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    public BaseResponse(List<T> listResult, String errorCode, String errorMessage, boolean isError) {
        this.listResult = listResult;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    public T getObjectResult() {
        return objectResult;
    }

    public void setObjectResult(T objectResult) {
        this.objectResult = objectResult;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }
}