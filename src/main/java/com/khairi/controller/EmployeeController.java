package com.khairi.controller;

import com.khairi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.khairi.service.EmployeeService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/")
	public String viewHomepage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/newEmployee")
	public String newEmployee(Model model){
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "newEmployee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showUpdate/{id}")
	public String showUpdate(@PathVariable(value="id")long id, Model model){
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "showUpdate";
	}

	@GetMapping("/showDelete/{id}")
	public String showDelete(@PathVariable(value="id")long id){
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
}
