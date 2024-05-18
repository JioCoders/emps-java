package com.springboot.empc;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.springboot.empc.entity.Address;
import com.springboot.empc.entity.Employee;
import com.springboot.empc.repository.EmpRepository;
import com.springboot.empc.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskRunnable implements CommandLineRunner {

	@Autowired
	EmpRepository empRepository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Override
	public void run(String... args) throws Exception {
		if (empRepository.count() == 0) {
			insertData();
		}
		long id1 = 1L;
		empRepository.findById(id1).ifPresent(System.out::println);

		long id2 = 5L;
		Optional<Employee> optional = empRepository.findById(id2);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.printf("No employee found with id %d%n", id2);
		}

		List<Employee> employees = empRepository.findAll();
		employees.forEach(employee -> System.out.println(employee.toString()));
		// empRepository.deleteAll();
	}

	void insertData() {
		Address address = new Address("House no 140", "Sector-40", "Gurgaon", "Haryana", "122003");
		Employee e1 = new Employee("Ramesh", address, "8888888889", "112233", "332211",
				"ramesh@gmail.com",
				true, true, 1715283934L, 1715283934L, "2024-05-14 17:05:00", Calendar.getInstance().getTime());
		e1.setEmpId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		empRepository.save(e1);

		Employee e2 = new Employee("Tom", address, "8888899999", "112233", "332211",
				"tom@gmail.com", true, false,
				1715283934L, 1715283934L, "2024-05-14 16:05:00", Calendar.getInstance().getTime());
		e2.setEmpId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		empRepository.save(e2);
		log.info("=========> Inserted dummy data in Employee table");
	}

}
