package com.baojia.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baojia.config.RedisUtil;
import com.baojia.model.Users;
import com.baojia.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/user")
/**
　　* @Description: ${todo}
　　* @param ${tags} 
　　* @return ${return_type} 
　　* @throws
　　* @author ceshi
　　* @date 2018/5/17 10:04 
　　*/
public class UserController{

    //这里模拟数据库
    private final Map<String, List<String>> userDb = new HashMap<>();

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    public UserController() {
        userDb.put("tom", Arrays.asList("user"));
        userDb.put("wen", Arrays.asList("user", "admin"));
    }
    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }


    /**
     *
     * @param name
     * @param response
     * @return
     * @throws ServletException
     */
    /**
    　　* @Description: ${todo}
    　　* @param ${tags} 
    　　* @return ${return_type} 
    　　* @throws
    　　* @author ceshi
    　　* @date 2018/5/17 10:06 
    　　*/
    /*以上是模拟数据库，并往数据库插入tom和sally两条记录*/
    @ApiOperation(value="用户登录", notes="根据用户名密码登录")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public LoginResponse login(@RequestParam String name,HttpServletResponse response)
            throws ServletException {
        if (name == null || !userDb.containsKey(name)) {
            throw new ServletException("Invalid login");
        }
        Map<String,String> data = new HashMap<String,String>();

        String token =Jwts.builder().setSubject(name)
                .claim("roles", userDb.get(name)).setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis()+30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        data.put("code","200");
        data.put("message",token);
        response.setHeader("Authorization","Bearer "+token);
        //加密生成token
        return new LoginResponse(data.toString());
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "role/{name}", method = RequestMethod.GET)
    public Boolean login(@PathVariable final String role, @RequestAttribute("name") String name) throws ServletException {
        return name.equals("tom");
    }

    @Reference
    IUserService userService;

   /* @RequestMapping("getUser")*/
    @PostMapping("getUser")
    public Users getUserAll(){
        return userService.getUserAll();
    }


    @Autowired
    private RedisUtil redisUtil ;

    /**
     * 设置redis
     */
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void demoTest(){
        redisUtil.set("1","value22222");
    }
    /**
     * 获取redis
     */
    @RequestMapping(value = "/getRedis",method = RequestMethod.POST)
    public String getRedis(){
        return redisUtil.get("1").toString();
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.selectListAll(pageNum,pageSize);
    }

}