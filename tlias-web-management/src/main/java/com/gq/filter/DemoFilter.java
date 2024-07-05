package com.gq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有
//@WebFilter(urlPatterns = "/login")//只有访问/login路径时，才会被拦截
//@WebFilter(urlPatterns = "/emps/*")//访问/emps下的所有资源，都会被拦截
public class DemoFilter implements Filter {
    //初始化方法，只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 过滤器初始化方法执行了");
    }
    //拦截到请求之后调用，调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器拦截到请求...放行前逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("过滤器拦截到请求...放行后逻辑");
    }
    //销毁方法，只调用一次
    @Override
    public void destroy() {
        System.out.println("destroy 过滤器销毁方法执行了");
    }
    /*注：其实初始化和销毁方法可以不写，底层默认实现*/
}
