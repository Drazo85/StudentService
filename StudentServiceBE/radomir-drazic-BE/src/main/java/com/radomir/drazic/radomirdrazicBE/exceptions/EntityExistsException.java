package com.radomir.drazic.radomirdrazicBE.exceptions;

public class EntityExistsException extends ProjectException {

    private static final long serialVersionUID = -6231455820775577287L;

    private final Object entity;

    public EntityExistsException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

}