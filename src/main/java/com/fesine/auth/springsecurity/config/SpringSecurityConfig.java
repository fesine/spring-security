package com.fesine.auth.springsecurity.config;

import com.fesine.auth.springsecurity.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders
        .AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2018/1/9
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/9
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    /**
     * 配置验证登录校验，用户信息保存在内存中
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        ////可以添加多个用户
        //auth.inMemoryAuthentication().withUser("fesine").password("fesine").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("demo").password("demo").roles("USER");

        //通过此方法可以使用自定义的数据库验证处理
        auth.userDetailsService(myUserService);
    }

    /**
     * 添加自定义拦截请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();

    }

    /**
     * 配置取消拦截路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
