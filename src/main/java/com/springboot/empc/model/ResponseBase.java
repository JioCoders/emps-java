package com.springboot.empc.model;

import lombok.Data;

@Data
public class ResponseBase {
    String message;
    int status;
}
