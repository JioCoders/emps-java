package com.springboot.empc.base;

import lombok.Data;

@Data
public class ResponseBase {
    String message;
    int status;
    boolean error;
}
