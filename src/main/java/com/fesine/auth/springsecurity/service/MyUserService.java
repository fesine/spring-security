package com.fesine.auth.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description: 用于数据库实现用户信息登录验证
 * @author: Fesine
 * @createTime:2018/1/9
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/9
 */
@Service
public class MyUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
