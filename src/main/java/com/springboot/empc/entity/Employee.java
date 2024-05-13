package com.springboot.empc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Document(collection = "employee")
@Data
public class Employee {

  @Transient
  public static final String SEQUENCE_NAME = "emp_sequence";

  @Id
  @Field(name = "id")
  private Long empId;
  @NotBlank
  @Size(max = 50)
  @Indexed(unique = true)
  private String name;
  private Address address;
  private String mobile;
  private String password;
  private String otp;
  @NotBlank
  @Size(max = 100)
  @Indexed(unique = true)
  private String email;
  private boolean active;
  @Field(name = "is_admin")
  boolean isAdmin;
  @Field(name = "created_at")
  Long createdAt;
  @Field(name = "updated_at")
  Long updatedAt;

  public Employee(String name, Address address, String mobile, String password, String otp, String email,
      boolean active,
      boolean isAdmin,
      long createdAt, long updatedAt) {
    this.name = name;
    this.address = address;
    this.mobile = mobile;
    this.password = password;
    this.otp = otp;
    this.email = email;
    this.active = active;
    this.isAdmin = isAdmin;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}