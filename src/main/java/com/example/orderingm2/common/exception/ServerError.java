package com.example.orderingm2.common.exception;

import org.springframework.http.HttpStatus;

public enum ServerError {
    PAGE_INVALID("Invalid Page"),
    NEW_ORDER_CREATION_FAILED("New Order Creation Failed"),
    FAILED_TO_FIND_ORDER("Failed to find order with id %s"),
    FAILED_TO_FIND_ORDERS("Failed to find orders"),
    ORDER_WITH_SPECIFIED_ID_NOT_EXIST("Order with % id is not exist"),
    NO_ORDERS_FOUND("No orders found"),

    INTERNAL_SERVER_ERROR("internal server error");


    public enum Type {
        ERROR, WARNING, INFO;
    }

    private Type type;
    private transient HttpStatus status;
    private boolean notifyDevelopers;
    private String message;

    private ServerError(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    private ServerError(String message, HttpStatus status) {
        this(message, Type.ERROR, status, false);
    }

    private ServerError(String message, Type t) {
        this(message, t, HttpStatus.BAD_REQUEST, false);
    }

    private ServerError(String message, Type t, HttpStatus status) {
        this(message, t, status, false);
    }

    private ServerError(String message, Type t, HttpStatus status, boolean notifyDevloper) {
        this.type = t;
        this.status = status;
        this.notifyDevelopers = notifyDevloper;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public boolean isNotifyDevelopers() {
        return notifyDevelopers;
    }

    public void setNotifyDevelopers(boolean notifyDevelopers) {
        this.notifyDevelopers = notifyDevelopers;
    }

    public String getMessage() {
        return message;
    }
}
