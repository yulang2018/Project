package com.yulang.project.controller;

import com.yulang.project.response.BaseResponse;
import com.yulang.project.response.CodeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name","lllllllllllllllllllll");
        return "index";
    }
}
