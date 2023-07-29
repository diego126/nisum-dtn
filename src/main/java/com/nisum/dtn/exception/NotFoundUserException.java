package com.nisum.dtn.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotFoundUserException extends RuntimeException{
    private final String message;
}
