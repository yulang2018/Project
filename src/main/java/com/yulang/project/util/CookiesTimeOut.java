package com.yulang.project.util;

import com.alibaba.fastjson.JSONObject;
import com.yulang.project.constant.Constant;
import com.yulang.project.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class CookiesTimeOut {

    public static void setCookiesTimeOut(HttpServletResponse response, User user, RedisTemplate redisTemplate,String token){
        String jsonUser = JSONObject.toJSONString(user);
        System.out.println(jsonUser);
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(token,jsonUser, Constant.COOKIES_TIMEOUT, TimeUnit.SECONDS);
        Cookie cookie=new Cookie("token",token);
        cookie.setValue(token);
        cookie.setMaxAge(Constant.COOKIES_TIMEOUT);
        response.addCookie(cookie);
    }

}
