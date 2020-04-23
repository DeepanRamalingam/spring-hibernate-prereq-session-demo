package com.stackroute.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Account;

@Repository
@Transactional
public class AccountDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<Account> findAll(){
//		return jdbcTemplate.query("select * from employee", new EmployeeMapper());		
		return sessionFactory.getCurrentSession().createQuery("from Account").list();
	}
	
	public Account findById(int id) {
		
//		String sql = "select * from employee where empid = ?";
//		return jdbcTemplate.queryForObject(sql, new Object[] {id},new EmployeeMapper());
		
		return sessionFactory.getCurrentSession().get(Account.class, id);
	}
	
	public void addAccount(Account account) {
		
		sessionFactory.getCurrentSession().save(account);
	}
}
