package com.springboot.empc.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "address")
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
}
