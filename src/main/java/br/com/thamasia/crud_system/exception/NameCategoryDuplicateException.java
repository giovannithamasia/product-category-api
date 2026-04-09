package br.com.thamasia.crud_system.exception;

public class NameCategoryDuplicateException extends RuntimeException {
    public NameCategoryDuplicateException(String message) {
        super(message);
    }
}
