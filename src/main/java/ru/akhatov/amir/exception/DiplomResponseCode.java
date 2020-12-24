package ru.akhatov.amir.exception;

public enum DiplomResponseCode {
    UNKNOWN_EXCEPTION(0, "Unknown exception"),
    VERTEX_NOT_FOUND(1, "Vertex not found"),
    ID_MUST_NOT_BE_NULL(2, "Id must not be null"),
    VERTEX_ALREADY_EXISTS(3, "Vertex already exists"),
    COST_MUST_NOT_BE_NULL(4, "Cost must not be null for edge"),
    EDGE_NOT_FOUND(5, "Edge not found"),
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
