### spring boot使用spring security安全框架开发步骤

1. 创建项目时需要指定加入spring security依赖
2. 在启动类上加入@EnableAutoConfiguration注解
3. 创建SpringSecurityConfig类并继承于WebSecurityConfigurerAdapter
4. 在SpringSecurityConfig上加入@Configurationt和@EnableWebSecurity注解
5. 覆盖configure(WebSecurity web)方法，添加忽略的静态资源拦截请求
6. 覆盖configure(HttpSecurity http)方法，添加需要拦截的方法请求
7. 覆盖configure(AuthenticationManagerBuilder auth)方法，添加用户登录验证处理方法，可使用内存验证，也可使用数据库验证。
8. 针对自定义验证，创建MyUserService类实现UserDetailsService接口，并覆盖UserDetails loadUserByUsername(String s)方法
9. 加入自定义密码加密实现，创建MyPasswordEncoder类实现PasswordEncoder接口，并覆盖接口方法
10. 在启动主类是加入@EnableGlobalMethodSecurity(prePostEnabled = true)注解，可以实现方法权限拦截
11. 在需要进行权限拦截的方法上加入注解，如下
<pre><code>
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
     * @param username
     * @param user
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
</code></pre>