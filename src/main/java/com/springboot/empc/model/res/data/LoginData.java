package com.springboot.empc.model.res.data;

import com.springboot.empc.entity.Address;

import lombok.Data;

@Data
public class LoginData {
    Long empId;
    String name;
    String email;
    String mobile;
    String password;
    String otp;
    Address address;
    boolean isActive;
    boolean isAdmin;
    Long createdAt;
    Long updatedAt;
    String token;
}
