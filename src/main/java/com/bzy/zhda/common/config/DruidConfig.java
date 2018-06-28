package com.bzy.zhda.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: DruidConfig
 */
@Configuration
public class DruidConfig {

    /**
     * @remark: lkw created by time: 2018/6/25 21:34
     */
    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(10000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * @remark: lkw created by time: 2018/6/25 21:29
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet());
        bean.addInitParameter("resetEnable", "false");
        bean.addInitParameter("loginUsername", "druid");
        bean.addInitParameter("loginPassword", "druid");
        bean.addInitParameter("allow", "128.242.127.1/24,128.242.128.1,127.0.0.1");
        bean.addInitParameter("deny", "128.242.127.4");
        bean.addUrlMappings("/druid/*");
        return bean;
    }

    /**
     * @remark: lkw created by time: 2018/6/25 21:29
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.addUrlPatterns("/*");
        return bean;
    }

    /**
     * @remark: lkw created by time: 2018/6/25 21:29
     */
    @Bean("druidDataSource")
    public DataSource dataSource(StatFilter statFilter) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("Bzy!!2018");
        dataSource.setUrl("jdbc:mysql://www.mycoffice.xin:3306/test?useSSL=false&characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(3);
        dataSource.setMaxActive(20);
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter);
        dataSource.setProxyFilters(filters);
        dataSource.setFilters("wall,log4j2");
        dataSource.setUseGlobalDataSourceStat(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }

}
