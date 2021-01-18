package ru.akhatov.amir.exception;

public enum DiplomResponseCode {
    UNKNOWN_EXCEPTION(0, "Unknown exception"),
    DIFFERENT_NODE_TYPES(1, "Different node types"),
    NODE_NOT_FOUND(2, "Node not found"),
    ROUTE_NOT_FOUND(3, "Route not found"),
    NODE_ALREADY_EXISTS(4, "Node already exists"),
    NODE_TYPE_NOT_FOUND(5, "Node type not found"),
    ;

    private Long errorCode;
    private String errorMessage;

    DiplomResponseCode(Integer errorCode, String errorMessage) {
        this.errorCode = Long.valueOf(errorCode);
        this.errorMessage = errorMessage;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
