package com.yulang.project.service.impl;

import com.yulang.project.mapper.GoodsMapper;
import com.yulang.project.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Map<String,String>> getGoodsList(){
        return goodsMapper.getGoodsList();
    }

}
