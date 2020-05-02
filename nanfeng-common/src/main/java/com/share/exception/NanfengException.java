package com.share.exception;

import com.share.enums.ExceptionResult;

import lombok.Getter;

@Getter
public class NanfengException extends RuntimeException {

    int  status;
    public NanfengException(ExceptionResult es ){

        super(es.getMessage());
        this.status=es.getStatus();
    }

    public NanfengException(ExceptionResult message, Throwable cause) {
        super(message.getMessage(), cause);
        this.status = message.getStatus();

    }
}
