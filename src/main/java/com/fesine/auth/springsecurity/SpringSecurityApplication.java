package com.fesine.auth.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration
        .EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "hello spring boot";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    /**
     * 只有admin的权限才可以访问
     * 使用此方法需要加新的注解
     *
     * @return
     * @EnableGlobalMethodSecurity(prePostEnabled = true)
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roleAuth")
    public String roleAuth() {
        return "admin auth";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasAuthority('')")//方法请求前拦截
    @PostAuthorize("hasRole('')")//方法完成后拦截
    @PreFilter("")
    @PostFilter("")
    @GetMapping("/role")
    public String role() {
        return null;
    }

    /**
     * 对基本数据类型的拦截处理
     * @param id 小于10
     * @param username ==用户登录的username
     * @param user username=abc
     * @return
     */
    @PreAuthorize("#id < 10 or principal.username.equals(#username) " +
            "and #user.username.equals('abc')")
    @PostAuthorize("returnObject%2==0")
    @GetMapping("/test")
    public Integer test(Integer id, String username, User user) {
        //...
        return id;
    }

    /**
     * PreFilter和PostFilter是对集合数据的处理
     * 验证输入的list大小为偶数
     * 输出的list大小为4的倍数
     * @param idList
     * @return
     */
    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @GetMapping("/test2")
    public List<Integer> test2(List<Integer> idList) {
        //...
        return idList;
    }

}
