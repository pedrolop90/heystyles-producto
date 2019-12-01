package com.heystyles.producto.api.config;

import com.heystyles.producto.api.http.filter.HttpRequestContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean sedeUsuarioContextFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new HttpRequestContextFilter());
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setEnabled(Boolean.TRUE);
        filterRegBean.setName("Usuario Context Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        filterRegBean.setOrder(1);
        return filterRegBean;
    }

}
