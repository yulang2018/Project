package com.yulang.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yulang.project.constant.Constant;
import com.yulang.project.entity.User;
import com.yulang.project.mapper.UserMapper;
import com.yulang.project.response.BaseResponse;
import com.yulang.project.response.CodeMessage;
import com.yulang.project.service.UserService;
import com.yulang.project.util.CookiesTimeOut;
import com.yulang.project.util.Md5Util;
import com.yulang.project.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;
import reactor.core.support.UUIDUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User getUserByUserId(Integer pkid) {
        return userMapper.getUserById(pkid);
    }

    @Override
    public BaseResponse login(LoginVo loginVo, HttpServletResponse response) {
        User user =userMapper.getUserByPhone(loginVo.getPhone());
        if(user==null){
            return BaseResponse.erro(CodeMessage.USER_NOT_EXITS);
        }else{
            String salt =user.getSalt();
            if((Md5Util.formPassToDbPass(loginVo.getPassword(),salt)).equals(user.getPassword()) ){
                //校验成功将数据存入cookie并放入redis
                String token = UUIDUtils.random().toString();
                CookiesTimeOut.setCookiesTimeOut(response,user,redisTemplate,token);
                return BaseResponse.success(user);
            }else{
                return BaseResponse.erro(CodeMessage.USER_PASSWORD_ERROR);
            }
        }
    }
}
