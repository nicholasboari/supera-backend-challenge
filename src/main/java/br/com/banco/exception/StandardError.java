package br.com.banco.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
