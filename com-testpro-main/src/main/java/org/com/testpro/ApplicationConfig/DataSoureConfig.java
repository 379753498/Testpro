package org.com.testpro.ApplicationConfig;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.com.testpro.Aop.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "org.com.testpro.Mybatis.*")
public class DataSoureConfig {


    @Autowired
    RestTemplateBuilder builder;
    @Autowired
    Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    /**
     * 经过测试 这个Bean 必须注册否则获取不到日志
     */
    @Bean
    public Slf4jLogFilter getSlf4jLogFilter() {
        Slf4jLogFilter filter = new Slf4jLogFilter();
        filter.setResultSetLogEnabled(false);
        filter.setConnectionLogEnabled(false);
        filter.setStatementParameterClearLogEnable(false);
        filter.setStatementCreateAfterLogEnabled(false);
        filter.setStatementCloseAfterLogEnabled(false);
        filter.setStatementParameterSetLogEnabled(false);
        filter.setStatementPrepareAfterLogEnabled(false);
        return filter;
    }

    @Bean(name = "DataSource13", autowire = Autowire.BY_TYPE)
    public DataSource Get13dataSource() throws PropertyVetoException,
            SQLException {


        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.oracle.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.oracelDB13.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.oracelDB13.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.oracelDB13.password"));
        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }

    @Bean(name = "DataSource15", autowire = Autowire.BY_TYPE)
    public DataSource Get15dataSource() throws PropertyVetoException,
            SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.oracle.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.oracelDB15.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.oracelDB15.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.oracelDB15.password"));
        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(300000);
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }


    @Bean(name = "DataSourceHbase", autowire = Autowire.BY_TYPE)
    public DataSource GetDataSourceHbase() throws PropertyVetoException,
            SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.Hbase.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.hbase.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.hbase.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.hbase.password"));
        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(300000);
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }


    @Bean(name = "DataSourceHive", autowire = Autowire.BY_TYPE)
    public DataSource GetDataSourceHive() throws PropertyVetoException,
            SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.hive.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.hive.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.hive.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.hive.password"));
        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(300000);
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }

    @Bean
    public DynamicDataSource getDynamicDataSource()
            throws PropertyVetoException, SQLException {
        DynamicDataSource DynamicDataSource = new DynamicDataSource();
        Map <Object, Object> targetDataSources = new HashMap <Object, Object>();
        targetDataSources.put("DataSource13", Get13dataSource());
        targetDataSources.put("DataSource15", Get15dataSource());
        targetDataSources.put("DataSourceHbase", GetDataSourceHbase());
        targetDataSources.put("DataSourceHive", GetDataSourceHive());
        DynamicDataSource.setTargetDataSources(targetDataSources);
        DynamicDataSource.setDefaultTargetDataSource(Get13dataSource());
        return DynamicDataSource;

    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException,
            SQLException {
        JdbcTemplate JdbcTemplate = new JdbcTemplate(getDynamicDataSource());
        return JdbcTemplate;

    }


    @Bean(name = "SqlSessionFactoryBean")
    public SqlSessionFactoryBean getSqlSessionFactoryBean()
            throws PropertyVetoException, SQLException {
        SqlSessionFactoryBean SqlSessionFactoryBean = new SqlSessionFactoryBean();
        SqlSessionFactoryBean.setDataSource(getDynamicDataSource());
        return SqlSessionFactoryBean;

    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager()
            throws PropertyVetoException, SQLException {

        DataSourceTransactionManager DataSourceTransactionManager = new DataSourceTransactionManager();
        DataSourceTransactionManager.setDataSource(getDynamicDataSource());
        return DataSourceTransactionManager;

    }
}
