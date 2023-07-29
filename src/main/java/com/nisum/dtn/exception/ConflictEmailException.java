package com.nisum.dtn.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConflictEmailException extends RuntimeException{
    private final String message;
}
