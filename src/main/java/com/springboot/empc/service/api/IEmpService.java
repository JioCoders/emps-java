package com.springboot.empc.service.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.springboot.empc.entity.Employee;
import com.springboot.empc.model.ResponseBase;
import com.springboot.empc.model.req.ReqLogin;
import com.springboot.empc.model.res.ResLogin;
import com.springboot.empc.model.res.ResEmpList;

public interface IEmpService {
    public ResponseEntity<ResEmpList> findAll(String token);

    public ResponseEntity<ResEmpList> findByNameContaining(String token, String name);

    public Optional<Employee> findById(Long id);

    public ResponseEntity<ResponseBase> save(Employee emp);

    public Employee update(Long id, Employee emp);

    public Map<String, Boolean> deleteById(Long id);

    public Map<String, Boolean> deleteAll();

    public List<Employee> findByActive(boolean isActive);

    public ResponseEntity<ResLogin> loginEmp(ReqLogin request);
}
