package com.fesine.auth.springsecurity.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: 密码加密工具类
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
public class MyPasswordEncoder implements PasswordEncoder{
    private static final String SALT="123456";
    @Override
    public String encode(CharSequence charSequence) {
        //使用md5进行加密
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        return md5.encodePassword(charSequence.toString(),SALT);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //使用md5进行解密
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        return md5.isPasswordValid(s,charSequence.toString(),SALT);
    }
}
