package com.wrpxcx.mapper;

import com.wrpxcx.POJO.Goods;
import com.wrpxcx.POJO.GoodsKind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    // 通过卖家的openid获取商品列表
    List<Goods> getGoodsByOpenid(String openid);

    // 通过商品id获取具体某一商品
    Goods getGoodsByGoodsId(int goodsId);

    // 通过学校id和种类获取商品列表
    List<Goods> getGoodsBySchoolAndKind(@Param("schoolId")int schoolId, @Param("kindId")int kindId);

    // 通过学校id和种类获取商品列表  一次返回一部分，从nowNumber 开始number的
    List<Goods> getGoodsBySchoolAndKindPage(@Param("schoolId")Integer schoolId,
                                        @Param("kindId")Integer kindId,
                                        @Param("nowNumber")Integer nowNumber,
                                        @Param("number")Integer number);

    //返回某个openid发布的指定页的商品
    List<Goods> getMyPublicGoodsPage(@Param("openid")String openid,
                                     @Param("nowNumber")Integer nowNumber,
                                     @Param("number")Integer number);

    //通过商品名称搜索商品，返回的为同一所学校的
    List<Goods> searchGoodsBySchoolIdAndName(@Param("schoolId")Integer schoolId,
                                          @Param("name") String name,
                                          @Param("nowNumber")Integer nowNumber,
                                          @Param("number")Integer number);


    // 添加商品
    int addGoods(Goods goods);

    //更新商品信息
    int updateGoods(Goods goods);

    //通过物品id删除商品
    int deleteGoodsByGoodsId(int goodsId);

    //返回商品分类信息
    List<GoodsKind> getNav();

}
