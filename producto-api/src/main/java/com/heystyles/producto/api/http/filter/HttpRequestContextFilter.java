package com.heystyles.producto.api.http.filter;

import com.heystyles.producto.api.http.util.HttpRequestContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
        String usuario = wrapper.getHeader("usuario");

        try {
            if (StringUtils.isEmpty(usuario)) {
                HttpRequestContextHolder.setUsuario(null);
            }
            else {
                HttpRequestContextHolder.setUsuario(usuario);
            }
            chain.doFilter(request, response);
        }
        catch (Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void destroy() {
    }

}
