/*
 * package com.aetins.customerportal.core.configuration;
 * 
 * import java.util.Properties;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.hibernate.SessionFactory; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.jdbc.datasource.DriverManagerDataSource; import
 * org.springframework.orm.hibernate4.HibernateTransactionManager; import
 * org.springframework.orm.hibernate4.LocalSessionFactoryBean; import
 * org.springframework.transaction.annotation.EnableTransactionManagement;
 * 
 * import com.mchange.v2.c3p0.ComboPooledDataSource; import
 * com.mchange.v2.c3p0.PooledDataSource;
 * 
 *//**
	 * Annotation based configuration for data persistence
	 * 
	 * @author avinash
	 *
	 */
/*
 * 
 * @Configuration
 * 
 * @EnableTransactionManagement public class PersistenceJPAConfig {
 * 
 * private Logger logger = LoggerFactory.getLogger(PersistenceJPAConfig.class);
 * 
 *//** DB properties **/
/*
 * 
 * @Value("${db.driver}") private String dbDriver;
 * 
 * @Value("${db.url}") private String dbUrl;
 * 
 * @Value("${db.username}") private String dbUsername;
 * 
 * @Value("${db.password}") private String dbPassword;
 * 
 *//** Hibernate properties **/
/*
 * 
 * @Value("${hibernate.hbm2ddl.auto}") private String hbm2ddl;
 * 
 * @Value("${hibernate.show_sql}") private String show_sql;
 * 
 * @Value("${hibernate.dialect}") private String dialect;
 * 
 *//** C3P0 connection pool properties **/
/*
 * 
 * @Value("${c3p0.connection.providerclass}") private String
 * connectionProviderClass;
 * 
 * @Value("${c3p0.min_size}") private String c3p0MinSize;
 * 
 * @Value("${c3p0.max_size}") private String c3p0MaxSize;
 * 
 * @Value("${c3p0.max_statements}") private String c3p0MaxStatements;
 * 
 * @Value("${c3p0.idle_test_period}") private String c3p0IdleTestPeriod;
 * 
 * @Value("${c3p0.preferredTestQuery}") private String c3p0PreferredTestQuery;
 * 
 * @Value("${c3p0.testConnectionOnCheckout}") private String
 * c3p0TestConnectionOnCheckout;
 * 
 * @Bean public LocalSessionFactoryBean sessionFactory() throws Exception {
 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
 * sessionFactory.setDataSource(comboPooledDataSource());
 * sessionFactory.setPackagesToScan(new String[] {
 * "com.aetins.customerportal.web" });
 * sessionFactory.setHibernateProperties(additionalProperties());
 * logger.info("Hibernate Session Registered"); logger.info(
 * "====================================================================================="
 * ); logger.info("hibernate.show_sql: {}", show_sql);
 * logger.info("hibernate.dialect: {}", dialect);
 * logger.info("C3P0.connection.provider_class: {}", connectionProviderClass);
 * 
 * logger.info("hibernate.c3p0.min_size: {}", c3p0MinSize);
 * logger.info("hibernate.c3p0.max_size: {}", c3p0MaxSize);
 * logger.info("hibernate.c3p0.max_statements: {}", c3p0MaxStatements);
 * logger.info("hibernate.c3p0.idle_test_period: {}", c3p0IdleTestPeriod);
 * logger.info("hibernate.c3p0.preferredTestQuery: {}", c3p0PreferredTestQuery);
 * logger.info("hibernate.c3p0.testConnectionOnCheckout: {}",
 * c3p0TestConnectionOnCheckout);
 * 
 * logger.info(
 * "====================================================================================="
 * ); return sessionFactory; }
 * 
 * public DataSource dataSource() { DriverManagerDataSource dataSource = new
 * DriverManagerDataSource(); dataSource.setDriverClassName(dbDriver);
 * dataSource.setUrl(dbUrl); dataSource.setUsername(dbUsername);
 * dataSource.setPassword(dbPassword); logger.info("Database details");
 * logger.info("============================"); logger.info("db url: {}",
 * dbUrl); logger.info("db driver: {}", dbDriver);
 * logger.info("============================"); return dataSource; }
 * 
 *//**
	 * C3P0 connection pooling
	 * 
	 * @return
	 * @throws Exception
	 */
/*
 * 
 * @Bean public PooledDataSource comboPooledDataSource() throws Exception {
 * 
 * ComboPooledDataSource dataSource = new ComboPooledDataSource();
 * dataSource.setJdbcUrl(dbUrl); dataSource.setUser(dbUsername);
 * dataSource.setPassword(dbPassword); dataSource.setDriverClass(dbDriver);
 * dataSource.setIdleConnectionTestPeriod(60 * 5); dataSource.setMinPoolSize(5);
 * dataSource.setMaxPoolSize(1000); dataSource.setInitialPoolSize(4);
 * dataSource.setIdleConnectionTestPeriod(500);
 * dataSource.setPreferredTestQuery("SELECT 1"); return dataSource; }
 * 
 *//**
	 * Hibernate Transaction manager
	 * 
	 * @param sessionFactory
	 * @return
	 * @throws Exception
	 */
/*
 * 
 * @Bean
 * 
 * @Autowired public HibernateTransactionManager
 * transactionManager(SessionFactory sessionFactory) throws Exception {
 * HibernateTransactionManager txManager = new HibernateTransactionManager();
 * txManager.setSessionFactory(sessionFactory().getObject()); return txManager;
 * }
 * 
 *//**
	 * Hibernate Properties
	 * 
	 * @return
	 *//*
		 * Properties additionalProperties() { Properties properties = new Properties();
		 * properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		 * properties.setProperty("hibernate.show_sql", show_sql);
		 * properties.setProperty("hibernate.dialect", dialect);
		 * properties.setProperty("hibernate.connection.CharSet", "utf8");
		 * properties.setProperty("hibernate.connection.characterEncoding", "utf8");
		 * properties.setProperty("hibernate.connection.useUnicode", "true"); return
		 * properties; } }
		 */