package com.stackroute.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Employee;

@Repository
@Transactional
public class EmployeeDAO {

//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll(){
//		return jdbcTemplate.query("select * from employee", new EmployeeMapper());		
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}
	
	public Employee findById(int id) {
		
//		String sql = "select * from employee where empid = ?";
//		return jdbcTemplate.queryForObject(sql, new Object[] {id},new EmployeeMapper());
		
		return sessionFactory.getCurrentSession().get(Employee.class, id);
	}
	
	public void addEmployee(Employee emp) {
		
		sessionFactory.getCurrentSession().save(emp);
	}
}
