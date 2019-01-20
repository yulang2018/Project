package com.yulang.project.controller.user;

import com.yulang.project.entity.User;
import com.yulang.project.response.BaseResponse;
import com.yulang.project.response.CodeMessage;
import com.yulang.project.service.UserService;
import com.yulang.project.util.Md5Util;
import com.yulang.project.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/user")
    public User getUserById(Integer pkid){
        redisTemplate.opsForValue().set("liming","999999999999999");
        return userService.getUserByUserId(pkid);
    }

    @RequestMapping("/to_login")
    public String to_login(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public BaseResponse do_login(@Validated LoginVo loginVo, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return BaseResponse.erro(CodeMessage.PARAMS_ERROR);
        }
        String password = loginVo.getPassword();
        return userService.login(loginVo,response);
    }

}
