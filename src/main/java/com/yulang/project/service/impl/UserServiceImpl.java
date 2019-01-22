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
import com.yulang.project.util.UUIDUtils;
import com.yulang.project.vo.LoginVo;
import com.yulang.project.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User getUserByUserId(Integer pkid) throws Exception {
        return userMapper.getUserById(pkid);
    }

    @Override
    public BaseResponse login(LoginVo loginVo, HttpServletResponse response) throws Exception {
        User user =userMapper.getUserByPhone(loginVo.getPhone());
        if(user==null){
            return BaseResponse.erro(CodeMessage.USER_NOT_EXITS);
        }else{
            String salt =user.getSalt();
            String dbPass = Md5Util.formPassToDbPass(loginVo.getPassword(),salt);
            System.out.print(dbPass);
            if((Md5Util.formPassToDbPass(loginVo.getPassword(),salt)).equals(user.getPassword()) ){
                //校验成功将数据存入cookie并放入redis
                String token = UUIDUtils.uuid();
                CookiesTimeOut.setCookiesTimeOut(response,user,redisTemplate,token);
                return BaseResponse.success(user);
            }else{
                return BaseResponse.erro(CodeMessage.USER_PASSWORD_ERROR);
            }
        }
    }

    @Transactional
    @Override
    public BaseResponse register(RegisterVo registerVo) throws Exception {
        User user = userMapper.getUserByPhone(registerVo.getPhone().toString());
        if(user!=null){
            return BaseResponse.erro(CodeMessage.USER_EXITES);
        }else{
            String salt= UUIDUtils.uuid();
            //对传入的密码进行md5加密
            String dbPass = Md5Util.formPassToDbPass(registerVo.getPassword(),salt);
            //将数据存入数据库
            user=new User();
            user.setId(UUIDUtils.uuid());
            user.setLastLoginDate(new Date());
            user.setRegisterDate(new Date());
            user.setSalt(salt);
            user.setNickname(registerVo.getNickname());
            user.setPhone(registerVo.getPhone());
            user.setPassword(dbPass);
            userMapper.insertUser(user);
        }
        return BaseResponse.success(user);
    }
}
