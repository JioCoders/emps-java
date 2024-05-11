package com.springboot.empc.model.res.data;

import lombok.Data;

@Data
public class EmpData {
    Long empId;
    String name;
    String email;
    String mobile;
    String password;
    String otp;
    String address;
    boolean isActive;
    boolean isAdmin;
    Long createdAt;
    Long updatedAt;
}
