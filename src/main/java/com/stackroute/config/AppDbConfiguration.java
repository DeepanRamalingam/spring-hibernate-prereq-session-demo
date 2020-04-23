package com.stackroute.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.model.Account;
import com.stackroute.model.Employee;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:mysql.properties")
public class AppDbConfiguration {

	@Value("${jdbc.drverClass}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String user;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	public DataSource getDataSource() {
	DriverManagerDataSource ds = new DriverManagerDataSource();
	ds.setDriverClassName(driver);
	ds.setUrl(url);
	ds.setUsername(user);
	ds.setPassword(password);
	return ds;
	}
	
//	@Bean
//	public DataSource getH2DataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.HSQL)
//				.addScript("classpath:sql/schema.sql")
//				.addScript("classpath:sql/test-data.sql").build();
//	}
	
	
//	@Bean
//	public JdbcTemplate getTemplate(DataSource ds) {
//		
//		JdbcTemplate template = new JdbcTemplate(ds);
//		template.setResultsMapCaseInsensitive(true);
//		
//		return template;
//	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource ds) throws IOException {
		
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(ds);
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create");
		sessionFactoryBean.setAnnotatedClasses(Employee.class,Account.class);
		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transaction = new HibernateTransactionManager();
		transaction.setSessionFactory(sessionFactory);
		return transaction;
	}

	
}
