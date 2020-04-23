package com.stackroute.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.model.Account;
import com.stackroute.model.Employee;
import com.stackroute.service.AccountService;
import com.stackroute.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/employees")
	public String printAllEmployees(ModelMap map) {
		
		List<Employee> employees = empService.findAll();
		List<Account> accounts = accountService.findAll();
//		for(Employee emp: employees) {
//			System.out.println(emp);
//		}
		map.addAttribute("employees", employees);
		map.addAttribute("accounts",accounts);
		return "welcome";
	}
	
	@GetMapping("/employee")
	public String printMessage(@RequestParam("empid")int empid,ModelMap map) {
		Employee employee = empService.findById(empid);
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(employee);
			System.out.println(employee);
		map.addAttribute("employees", emps);
		return "welcome";
	}
	
	@PostMapping("/addNew")
	public String addEmployeeHandle(@ModelAttribute("emp") Employee emp, ModelMap map) {
		
		empService.addEmployee(emp);
		map.addAttribute("employees", empService.findAll());
		map.addAttribute("accounts", accountService.findAll());
		return "welcome";
		
	}
	
	@PostMapping("/addNewAccount")
	public String addAccountHandle(@ModelAttribute("account") Account account, ModelMap map) {
		
		accountService.addAccount(account);
		map.addAttribute("accounts",accountService.findAll());
		map.addAttribute("employees", empService.findAll());
		return "welcome";
		
	}
}
