package com.yulang.project.service;

import com.yulang.project.entity.User;
import com.yulang.project.response.BaseResponse;
import com.yulang.project.response.CodeMessage;
import com.yulang.project.vo.LoginVo;
import com.yulang.project.vo.RegisterVo;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public User getUserByUserId(Integer pkid) throws Exception;

    public BaseResponse login(LoginVo loginVo, HttpServletResponse response) throws Exception;

    BaseResponse register(RegisterVo registerVo) throws Exception;
}
