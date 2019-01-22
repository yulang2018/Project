package com.yulang.project.controller.goods;

import com.yulang.project.entity.User;
import com.yulang.project.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodService;

    @RequestMapping("/to_list")
    public String list(Model model , User user){
        List list = goodService.getGoodsList();
        model.addAttribute("goodList",list);
        return "goods_list";
    }

}
