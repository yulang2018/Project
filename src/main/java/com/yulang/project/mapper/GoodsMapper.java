package com.yulang.project.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    @Select("select g.* ,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from goods g left join miaosha_goods mg on g.id=mg.goods_id ")
    public List<Map<String,String>> getGoodsList();
}
