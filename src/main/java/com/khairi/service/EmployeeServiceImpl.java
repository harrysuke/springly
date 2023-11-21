package com.khairi.service;

import java.util.List;
import java.util.Optional;

import com.khairi.model.Employee;
import com.khairi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if(optional.isPresent()){
			employee = optional.get();
		}else{
			throw new RuntimeException("Record not found for ID "+id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}
}
