package com.radomir.drazic.radomirdrazicBE.exceptions;


public class InvalidEntityException extends ProjectException {
  
	private static final long serialVersionUID = -5760136845329857650L;
	
	private final Object entity;

    public InvalidEntityException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
