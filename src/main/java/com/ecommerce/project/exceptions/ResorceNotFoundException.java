package com.ecommerce.project.exceptions;

public class ResorceNotFoundException extends RuntimeException {
    String resorceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResorceNotFoundException() {
    }

    public ResorceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s: %s",resourceName,field,fieldName));
        this.resorceName = resorceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResorceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s: %d",resourceName,field,fieldId));
        this.resorceName = resorceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
