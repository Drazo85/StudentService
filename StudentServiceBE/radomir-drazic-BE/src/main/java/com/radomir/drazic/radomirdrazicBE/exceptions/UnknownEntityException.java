package com.radomir.drazic.radomirdrazicBE.exceptions;

public class UnknownEntityException extends ProjectException {

    private static final long serialVersionUID = 8397412761127411143L;

    private final Object entity;

    public UnknownEntityException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

}
