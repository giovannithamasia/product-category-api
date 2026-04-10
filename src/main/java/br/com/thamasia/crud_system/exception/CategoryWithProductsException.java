package br.com.thamasia.crud_system.exception;

public class CategoryWithProductsException extends RuntimeException {
    public CategoryWithProductsException(String message) {
        super(message);
    }
}
