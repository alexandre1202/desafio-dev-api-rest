package br.com.dockbank.bankaccount.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
public class AuditingRequestContextFilter {
    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        RequestContextFilter contextFilter = new RequestContextFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(1);
        System.out.println("AuditingRequestContextFilter.contextFilterRegistrationBean() - registrationBean: " + registrationBean);
        return registrationBean;
    }
}
