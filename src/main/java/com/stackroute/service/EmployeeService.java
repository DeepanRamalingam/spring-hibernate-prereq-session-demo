package com.stackroute.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.dao.EmployeeDAO;
import com.stackroute.model.Account;
import com.stackroute.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO empDao;

	public List<Employee> findAll() {
//		return jdbcTemplate.query("select * from employee", new EmployeeMapper());		
		return empDao.findAll();
	}

	public Employee findById(int id) {

//		String sql = "select * from employee where empid = ?";
//		return jdbcTemplate.queryForObject(sql, new Object[] {id},new EmployeeMapper());

		return empDao.findById(id);
	}
	
	public void addEmployee(Employee emp) {
//		Demonstrate OneToOne
//		Account account = new Account();
//		account.setType("Dummy acc of "+emp.getEmpname());
//		emp.setAccount(account);
//		-----------------------------------------------------------------
//		Demonstrate OneToMany
		Set<Account> accounts = new HashSet<Account>();
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setType("Personal acc of"+emp.getEmpname());
		account2.setType("Business acc of"+emp.getEmpname());
		accounts.add(account1);
		accounts.add(account2);
		emp.setAccounts(accounts);	
//		--------------------------------------------------------------------------
		empDao.addEmployee(emp);
	}
}
