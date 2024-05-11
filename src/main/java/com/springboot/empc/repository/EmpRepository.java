package com.springboot.empc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.empc.entity.Employee;

@Repository
public interface EmpRepository extends MongoRepository<Employee, Long> {
  List<Employee> findByActive(boolean published);

  List<Employee> findByNameContaining(String title);

  Optional<Employee> findFirstByEmailAndPassword(String email, String password);

}