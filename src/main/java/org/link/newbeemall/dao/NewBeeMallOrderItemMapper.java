package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.NewBeeMallOrderItem;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallOrderItemMapper
 * Author:  7ink
 * Data:    2024/5/16 9:13
 * Description:订单从表持久层代码
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallOrderItemMapper {


    /**
     * 批量insert订单项数据
     *
     * @param orderItems
     * @return
     */
    int insertBatch(@Param("orderItems") List<NewBeeMallOrderItem> orderItems);

    /**
     * 根据订单id获取订单项列表
     *
     * @param orderId
     * @return
     */
    List<NewBeeMallOrderItem> selectByOrderId(Long orderId);

    /**
     * 根据订单id获取订单项列表
     *
     * @param orderIds
     * @return
     */
    List<NewBeeMallOrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);
}
