package com.gq.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gq.uitl.JwtUtils;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源方法运行前运行，返回true：放行，放回false，不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器：preHandle运行。。。 ");

        //1、获取请求url
        String url = request.getRequestURL().toString();
        log.info("拦截器判断：请求的url为：{}",url);

        //2、判断请求url中是否包含login,如果包含，说明是登录操作，放行
        if (url.contains("login")){
            log.info("拦截器判断：登录操作，放行....");
            return true;
        }
        //3、获取请求头中的令牌（token）
        String token = request.getHeader("token");
        //4、判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)){
            log.info("拦截器判断：请求头token为空，返回未登录的信息");
            Result error = Result.err("NOT_LOGIN");
            //手动转换 对象--json————>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;

        }
        //5、解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("拦截器判断：解析令牌失败，返回未登录错误信息");
            Result error = Result.err("NOT_LOGIN");
            //手动转换 对象--json————>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //6、放行
        log.info("拦截器判断：令牌合法，放行");

        return true;
    }
    //目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器：postHandle运行。。。");
    }
    //视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器：afterCompletion运行。。。");
    }
}
