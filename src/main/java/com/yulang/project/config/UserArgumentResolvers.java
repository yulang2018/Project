package com.yulang.project.config;

import com.alibaba.fastjson.JSONObject;
import com.yulang.project.constant.Constant;
import com.yulang.project.entity.User;
import com.yulang.project.util.CookiesTimeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class clazz =methodParameter.getParameterType();
        return clazz== User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request =nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String paramToken=request.getParameter(Constant.PARAMS_NAME_TOKEN);
        String cookiesToken=getCookiesValues(request,Constant.PARAMS_NAME_TOKEN);
        if(paramToken==null&&cookiesToken==null){
            return null;
        }
        String token=paramToken==null?cookiesToken:paramToken;
        redisTemplate.setStringSerializer(new GenericToStringSerializer<String>(String.class));
        String jsonUser = (String) redisTemplate.opsForValue().get(token);
        //重新设置cookies和token有效时间
        User user=JSONObject.parseObject(jsonUser,User.class);
        CookiesTimeOut.setCookiesTimeOut(response,user,redisTemplate,token);
        return user;
    }

    private String getCookiesValues(HttpServletRequest request, String paramsNameToken) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(paramsNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
