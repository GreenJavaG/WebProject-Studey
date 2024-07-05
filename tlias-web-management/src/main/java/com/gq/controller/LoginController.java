package com.gq.controller;

import com.gq.pojo.Emp;
import com.gq.service.EmpService;
import com.gq.uitl.JwtUtils;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(HttpServletResponse response,HttpServletRequest request,@RequestBody Emp emp){
        log.info("输入的登录信息为，{}",emp);
        Emp emp1 = empService.login(emp);
        log.info("查询到的用户信息为：{}",emp1);

        //登录成功，生成令牌，下发令牌
        if (emp1 != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",emp1.getId());
            claims.put("name", emp1.getName());
            claims.put("username", emp1.getUsername());
            //jwt包含了当前登录的员工信息
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.err("用户或密码错误");

    }
    /*会话技术1、Cookie*/
    //设置cookie
    @GetMapping("/setCookie")
    public Result setCookie(HttpServletResponse response){
        Emp emp = new Emp();
        emp.setUsername("hhhhhhh");
        //设置Cookie/响应Cookie
        response.addCookie(new Cookie("login_username",emp.getUsername()));
        log.info("{}的Cookie设置成功",emp.getUsername());
        return Result.success();
    }
    //获取cookie
    @GetMapping("/getCookie")
    public Result getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login_username")){
                    System.out.println("login_username:"+cookie.getValue());
                }
            }
        }
        log.info("获取cookie成功");
        return Result.success();
    }
    /*会话技术2、session*/
    //往HttpSession中存储值
    @GetMapping("/setSession")
    public Result setSession(HttpSession session){
        log.info("HttpSession-s1:{}",session.hashCode());

        session.setAttribute("login_username","tom");
        return Result.success();
    }

    //从HttpSession中取值
    @GetMapping("/getSession")
    public Result getSeesion(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object login_username = session.getAttribute("login_username");
        return Result.success(login_username);

    }
}
