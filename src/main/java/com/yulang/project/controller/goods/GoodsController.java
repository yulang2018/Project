package com.yulang.project.controller.goods;

import com.yulang.project.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String list(User user){
        System.out.println(user);
        return "goods_list";
    }

}
