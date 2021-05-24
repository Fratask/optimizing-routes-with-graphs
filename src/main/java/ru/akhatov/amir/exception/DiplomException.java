package ru.akhatov.amir.exception;

public class DiplomException extends RuntimeException {

    private Long errorCode;
    private String errorMessage;

    public DiplomException(Long errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DiplomException(DiplomResponseCode diplomResponseCode) {
        super(diplomResponseCode.getErrorMessage());
        this.errorCode = diplomResponseCode.getErrorCode();
        this.errorMessage = diplomResponseCode.getErrorMessage();
    }

    public DiplomException(DiplomResponseCode diplomResponseCode, String field, String value) {
        super(diplomResponseCode.getErrorMessage().concat(", ").concat(field).concat(" : ").concat(value));
        this.errorCode = diplomResponseCode.getErrorCode();
        this.errorMessage = diplomResponseCode.getErrorMessage().concat(", ").concat(field).concat(" : ").concat(value);
    }

    public DiplomException(DiplomResponseCode diplomResponseCode, String field) {
        super(diplomResponseCode.getErrorMessage().concat(", ").concat(field));
        this.errorCode = diplomResponseCode.getErrorCode();
        this.errorMessage = diplomResponseCode.getErrorMessage().concat(", ").concat(field);
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
