package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.NewBeeMallOrder;
import org.link.newbeemall.util.PageQueryUtil;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallOrderMapper
 * Author:  7ink
 * Data:    2024/5/16 9:13
 * Description:订单主表持久层代码
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallOrderMapper {

    /**
     * 插入订单
     * @param newBeeMallOrder
     * @return
     */
    int insertSelective(NewBeeMallOrder newBeeMallOrder);

    /**
     * 插入订单
     * @param record
     * @return
     */
    int insert(NewBeeMallOrder record);

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    NewBeeMallOrder selectByOrderNo(String orderNo);

    /**
     * 我的订单-列表总数
     * @param pageUtil
     * @return
     */
    int getTotalNewBeeMallOrders(PageQueryUtil pageUtil);

    /**
     * 我的订单-列表
     * @param pageUtil
     * @return
     */
    List<NewBeeMallOrder> findNewBeeMallOrderList(PageQueryUtil pageUtil);

    /**
     * 取消订单
     * @param longs
     * @param orderStatus
     * @return
     */
    int closeOrder(@Param("orderIds") List<Long> longs,  @Param("orderStatus")int orderStatus);

    /**
     * 更新订单支付状态
     * @param newBeeMallOrder
     * @return
     */
    int updateByPrimaryKeySelective(NewBeeMallOrder newBeeMallOrder);

    /**
     * 查询订单-订单id
     * @param orderId
     * @return
     */
    NewBeeMallOrder selectByPrimaryKey(Long orderId);

    /**
     * 查询订单-订单id集合
     * @param list
     * @return
     */
    List<NewBeeMallOrder> selectByPrimaryKeys(@Param("orderIds") List<Long> list);

    /**
     *
     * @param orderIds
     * @return
     */
    int checkOut(@Param("orderIds") List<Long> orderIds);

    /**
     * 备货
     * @param asList
     * @return
     */
    int checkDone(@Param("orderIds") List<Long> asList);
}
