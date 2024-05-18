package com.springboot.empc.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.empc.entity.Employee;
import com.springboot.empc.model.ResponseBase;
import com.springboot.empc.model.req.ReqLogin;
import com.springboot.empc.model.res.ResEmpList;
import com.springboot.empc.model.res.ResLogin;
import com.springboot.empc.service.api.IEmpService;

// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmpController {

  @Autowired
  IEmpService iService;

  @GetMapping("/emp-token")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResEmpList> getAllEmp(@RequestHeader(AUTHORIZATION) String token,
      @RequestParam(required = false) String title) {
    if (title == null)
      return iService.findAll(token);
    else
      return iService.findByNameContaining(token, title);
  }

  @GetMapping("/emp")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResEmpList> getAllEmpb(@RequestParam(required = false) String title) {
    if (title == null)
      return iService.findAll("");
    else
      return iService.findByNameContaining("", title);
  }

  @GetMapping("/emp/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Employee> getEmployeeById(@PathVariable("id") Long id) {
    return iService.findById(id);
  }

  @PostMapping("/login")
  public ResponseEntity<ResLogin> loginUser(@RequestBody ReqLogin request) {
    System.out.println("UserController => loginUser()=>" + request.getEmail());
    return iService.loginEmp(request);
  }

  @PostMapping("/emp")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ResponseBase> createEmployee(@RequestBody Employee emp) {
    return iService
        .save(new Employee(emp.getName(), emp.getAddress(), emp.getMobile(), emp.getPassword(), "332211",
            emp.getEmail(), true, false,
            System.currentTimeMillis(), System.currentTimeMillis(), emp.getRequestDate(), emp.getReqDate()));
  }

  @PutMapping("/emp/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Employee updateEmp(@PathVariable("id") long id,
      @RequestBody Employee emp) {
    return iService.update(id, emp);
  }

  @DeleteMapping("/emp/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Boolean> deleteEmployee(@PathVariable("id") long id) {
    return iService.deleteById(id);
  }

  @DeleteMapping("/emp")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Boolean> deleteAllemp() {
    return iService.deleteAll();
  }

  @GetMapping("/emp/active")
  @ResponseStatus(HttpStatus.OK)
  public List<Employee> findByActive() {
    return iService.findByActive(true);
  }
}