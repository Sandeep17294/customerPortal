package com.aetins.customerportal.core.configuration;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

@EnableTransactionManagement

@ComponentScan("com.aetins.customerportal.web")
public class PersistenceJNDIConfig {

	private Logger logger = LoggerFactory.getLogger(PersistenceJNDIConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() throws NamingException {
		logger.info("Datasource registered ");
		logger.info("Datasource : {} ", env.getProperty("database.jndi.ds.name"));
		return (DataSource) new JndiTemplate().lookup(env.getProperty("database.jndi.ds.name"));
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws Exception {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.aetins.customerportal.web" });
		sessionFactory.setHibernateProperties(additionalProperties());
		logger.info("Hibernate Session Registered");
		logger.info("=====================================================================================");
		logger.info("=====================================================================================");
		return sessionFactory;
	}

	@Bean

	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) throws Exception {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		return hibernateProperties;
	}

}
