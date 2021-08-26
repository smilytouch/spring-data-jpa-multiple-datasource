package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "rlsEntityManagerFactory", transactionManagerRef = "rlsTransactionManager",
		basePackages = {"com.example.demo.repository.rls" })
public class RlsDBConfig {

	@Autowired
	private Environment env;


//	public RlsDBConfig() {
//		super();
//	}

	@Bean(name = "rlsDataSource")
	public DataSource userDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.rls.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.rls.datasource.jdbcUrl"));
		dataSource.setUsername(env.getProperty("spring.rls.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.rls.datasource.password"));
		return dataSource;
	}
//	@ConfigurationProperties(prefix = "spring.rls.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Bean(name = "rlsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean rlsEntityManagerFactory(@Qualifier("rlsDataSource") DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.example.demo.entities.rls");

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("spring.jpa.show-sql",env.getProperty("spring.jpa.show-sql"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean(name = "rlsTransactionManager")
	public PlatformTransactionManager rlsTransactionManager(
			@Qualifier("rlsEntityManagerFactory") EntityManagerFactory rlsEntityManagerFactory) {
		return new JpaTransactionManager(rlsEntityManagerFactory);
	}
}
