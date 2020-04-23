package com.stackroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.dao.AccountDao;
import com.stackroute.model.Account;

@Service
public class AccountService {

	@Autowired
	AccountDao accountdao;
	
	public List<Account> findAll(){
//		
		return accountdao.findAll();
	}
	
	public Account findById(int id) {
		
		
		return accountdao.findById(id);
	}
	
	public void addAccount(Account account) {
		
//		simulating many accounts for one employee by setting same id
//		Employee employee = new Employee();
//		employee.setEmpid(100);
//		employee.setEmpname("Employee of "+account.getType());
//		employee.setCity("Dummy");
//		employee.setDesign("Dummy");
//		employee.setGender('F');
//		employee.setSalary(2000);
//		account.setEmployee(employee);
//		---------------------------------------------------------------------------
		accountdao.addAccount(account);
	}
}
