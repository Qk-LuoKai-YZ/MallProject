package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.NewBeeMallShoppingCartItem;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallShoppingCartItemMapper
 * Author:  7ink
 * Data:    2024/5/15 22:31
 * Description:购物车的持久层接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallShoppingCartItemMapper {

    /**
     *  保存一条记录
     * @param record
     * @return
     */
    int insertSelective(NewBeeMallShoppingCartItem record);

    /**
     * 根据userId和goodsId查询记录
     * @param newBeeMallUserId
     * @param goodsId
     * @return
     */
    NewBeeMallShoppingCartItem selectByUserIdAndGoodsId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("goodsId") Long goodsId);

    /**
     * 根据userId查询当前用户购物车添加了多少条记录
     * @param newBeeMallUserId
     * @return
     */
    int selectCountByUserId(Long newBeeMallUserId);

    /**
     * 根据id查询购物车
     * @param cartItemId
     * @return
     */
    NewBeeMallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    /**
     * 根据id更新购物车
     * @param newBeeMallShoppingCartItemUpdate
     * @return
     */
    int updateByPrimaryKeySelective(NewBeeMallShoppingCartItem newBeeMallShoppingCartItemUpdate);

    /**
     * 根据id删除购物车
     * @param shoppingCartItemId
     * @return
     */
    int deleteByPrimaryKey(Long shoppingCartItemId);

    /**
     * 根据userId查询购物车
     * @param newBeeMallUserId
     * @param shoppingCartItemTotalNumber
     * @return
     */
    List<NewBeeMallShoppingCartItem> selectByUserId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("number") int shoppingCartItemTotalNumber);

    /**
     * 批量删除购物车数据
     * @param itemIdList
     * @return
     */
    int deleteBatch(List<Long> itemIdList);
}
