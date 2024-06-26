package com.springboot.empc.service.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.empc.entity.Employee;
import com.springboot.empc.model.ResponseBase;
import com.springboot.empc.model.req.ReqLogin;
import com.springboot.empc.model.res.ResEmpList;
import com.springboot.empc.model.res.ResLogin;
import com.springboot.empc.model.res.data.EmpData;
import com.springboot.empc.model.res.data.LoginData;
import com.springboot.empc.repository.EmpRepository;
import com.springboot.empc.security.AuthScope;
import com.springboot.empc.security.JwtService;
import com.springboot.empc.service.SequenceGeneratorService;
import com.springboot.empc.utils.ApiConstant;
import com.springboot.empc.utils.Common;
import com.springboot.empc.utils.StrConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpService implements IEmpService {

  @Autowired
  EmpRepository empRepository;

  @Autowired
  SequenceGeneratorService sequenceGeneratorService;

  @Autowired
  JwtService jwtService;

  public ResponseEntity<ResEmpList> findAll(String token) {
    ResEmpList response = new ResEmpList();
    // if (!Common.checkNotNull(token)) {
    // response.setMessage(StrConstant.TOKEN_NOT_FOUND);
    // response.setStatus(ApiConstant.INVALID_REQUEST_CODE);
    // return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // // CHECK ADMIN USER
    // String bearer = jwtService.resolveToken(token);
    // if (!jwtService.validateToken(bearer, AuthScope.USER)
    // && !jwtService.validateToken(bearer, AuthScope.ADMIN)) {
    // response.setMessage(StrConstant.UN_AUTHORISE_ACCESS);
    // response.setStatus(ApiConstant.INVALID_REQUEST_CODE);
    // return new ResponseEntity<>(response, HttpStatus.OK);
    // }
    // CHECK VALID USER ID
    // int loginUserId = Integer.parseInt(jwtService.extractUserId(bearer));
    // log.info("UserId={}", loginUserId);

    List<Employee> empRepoList = empRepository.findAll();
    List<EmpData> empList = new ArrayList<>();
    for (Employee e : empRepoList) {
      EmpData data = new EmpData();
      data.setEmpId(e.getEmpId());
      data.setEmail(e.getEmail());
      empList.add(data);
    }
    response.setEmpList(empList);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<ResEmpList> findByNameContaining(String token, String name) {
    ResEmpList response = new ResEmpList();
    List<Employee> empRepoList = empRepository.findByNameContaining(name);
    List<EmpData> empList = new ArrayList<>();
    for (Employee e : empRepoList) {
      EmpData data = new EmpData();
      data.setEmpId(e.getEmpId());
      data.setEmail(e.getEmail());
      empList.add(data);
    }
    response.setEmpList(empList);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public Optional<Employee> findById(Long id) {
    return empRepository.findById(id);
  }

  public ResponseEntity<ResponseBase> save(Employee emp) {
    ResponseBase response = new ResponseBase();
    String statusMessage = null;
    int statusCode = 0;

    if (!Common.checkNotNull(emp.getMobile())) {
      statusCode = -1;
      statusMessage = "Invalid mobile number.";
    } else if (!Common.validMobile(emp.getMobile())) {
      statusCode = -1;
      statusMessage = "Please enter correct mobile number.";
    } else if (!Common.checkNotNull(emp.getName())) {
      statusCode = -1;
      statusMessage = "Please enter correct mobile number.";
    } else {
      emp.setEmpId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
      try {
        Employee e = empRepository.save(emp);
        log.info("Data Inserted " + e.toString());
        statusCode = 1;
        statusMessage = StrConstant.INSERT_SUCCESS;
      } catch (Exception e) {
        e.printStackTrace();
        statusCode = -1;
        statusMessage = StrConstant.FAILED_TO_SAVE;
      }
      response.setStatus(statusCode);
      response.setMessage(statusMessage);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public Employee update(Long id, Employee emp) {
    Optional<Employee> result = empRepository.findById(id);
    if (result.isPresent()) {
      emp.setEmpId(id);
      return empRepository.save(emp);
    }
    return null;
  }

  public Map<String, Boolean> deleteById(Long id) {
    boolean result = false;
    try {
      empRepository.deleteById(id);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", result);
    return response;
  }

  public Map<String, Boolean> deleteAll() {
    boolean result = false;
    try {
      empRepository.deleteAll();
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", result);
    return response;
  }

  public List<Employee> findByActive(boolean isActive) {
    return empRepository.findByActive(isActive);
  }

  @Override
  public ResponseEntity<ResLogin> loginEmp(ReqLogin requestLogin) {
    ResLogin responseLogin = new ResLogin();
    if (!Common.checkNotNull(requestLogin.getEmail()) || !Common.checkNotNull(requestLogin.getPassword())
        || requestLogin.getPassword().length() < 6) {
      responseLogin.setMessage(StrConstant.PLEASE_PROVIDE_MANDATORY_FIELD);
      responseLogin.setStatus(ApiConstant.INVALID_REQUEST_CODE);
      return new ResponseEntity<>(responseLogin, HttpStatus.OK);
    }
    Pattern pattern = Pattern.compile(Common.regexEmail);
    Matcher matcher = pattern.matcher(requestLogin.getEmail());
    if (!matcher.matches()) {
      responseLogin.setMessage(StrConstant.INVALID_EMAIL);
      responseLogin.setStatus(ApiConstant.INVALID_REQUEST_CODE);
      return new ResponseEntity<>(responseLogin, HttpStatus.OK);
    }

    Optional<Employee> empRepo = empRepository.findFirstByEmailAndPassword(requestLogin.getEmail(),
        requestLogin.getPassword());
    if (empRepo.isPresent()) {
      Employee emp = empRepo.get();
      LoginData loginData = new LoginData();
      loginData.setEmpId(emp.getEmpId());
      loginData.setEmail(emp.getEmail());
      loginData.setMobile(emp.getMobile());
      loginData.setAddress(emp.getAddress());
      loginData.setName(emp.getName());
      loginData.setActive(emp.isActive());
      loginData.setAdmin(emp.isAdmin());
      loginData.setCreatedAt(emp.getCreatedAt());
      loginData.setUpdatedAt(emp.getUpdatedAt());
      try {
        AuthScope scope;
        String token = null;
        if (emp.isAdmin()) {
          scope = AuthScope.ADMIN;
        } else {
          scope = AuthScope.USER;
        }
        token = jwtService.generateToken(emp, scope);
        loginData.setToken(token);
      } catch (Exception e) {
        e.printStackTrace();
      }

      // String otpString = String.valueOf(Common.generateOTP());
      // emailService.sendSMS(user.getMobileNo(), otpString);
      // emailService.sendEmailOTP(user.getEmailId(), otpString);
      // user.setOtp(otpString);

      responseLogin.setLoginData(loginData);
      responseLogin.setMessage(StrConstant.SUCCESS);
      responseLogin.setStatus(ApiConstant.SUCCESS_CODE);
    } else {
      responseLogin.setMessage(StrConstant.PLEASE_PROVIDE_VALID_EMAIL_PASSWORD);
      responseLogin.setStatus(ApiConstant.INVALID_REQUEST_CODE);
    }
    return new ResponseEntity<>(responseLogin, HttpStatus.OK);
  }
}