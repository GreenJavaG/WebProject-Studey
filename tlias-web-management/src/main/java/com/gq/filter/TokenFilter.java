package com.gq.filter;

import com.alibaba.fastjson.JSONObject;
import com.gq.uitl.JwtUtils;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*拦截所有请求，判断当前用户是否登录*/
@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url为：{}",url);

        //2、判断请求url中是否包含login,如果包含，说明是登录操作，放行
        if (url.contains("login")){
            log.info("登录操作，放行....");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3、获取请求头中的令牌（token）
        String token = request.getHeader("token");
        //4、判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.err("NOT_LOGIN");
            //手动转换 对象--json————>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;

        }
        //5、解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.err("NOT_LOGIN");
            //手动转换 对象--json————>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //6、放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
