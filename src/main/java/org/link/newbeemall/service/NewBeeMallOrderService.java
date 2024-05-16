package org.link.newbeemall.service;

import org.link.newbeemall.controller.vo.NewBeeMallOrderDetailVO;
import org.link.newbeemall.controller.vo.NewBeeMallOrderItemVO;
import org.link.newbeemall.controller.vo.NewBeeMallShoppingCartItemVO;
import org.link.newbeemall.controller.vo.NewBeeMallUserVO;
import org.link.newbeemall.entity.NewBeeMallOrder;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallOrderService
 * Author:  7ink
 * Data:    2024/5/16 8:17
 * Description:订单模块的业务层接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallOrderService {

    /**
     * 保存订单
     * @param user
     * @param myShoppingCartItems
     * @return
     */
    String saveOrder(NewBeeMallUserVO user, List<NewBeeMallShoppingCartItemVO> myShoppingCartItems);

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    NewBeeMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * 我的订单列表
     * @param pageUtil
     * @return
     */
    Object getMyOrders(PageQueryUtil pageUtil);

    /**
     * 手动取消订单
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String cancelOrder(String orderNo, Long userId);


    /**
     * 获取订单详情
     * @param orderNo
     * @return
     */
    NewBeeMallOrder getNewBeeMallOrderByOrderNo(String orderNo);

    /**
     * 支付成功
     * @param orderNo
     * @param payType
     * @return
     */
    String paySuccess(String orderNo, int payType);


    /**
     * 确认收货
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String finishOrder(String orderNo, Long userId);

    /**
     * 订单列表-(管理端调用)
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallOrdersPage(PageQueryUtil pageUtil);

    /**
     * 修改订单信息(管理端调用)
     * @param newBeeMallOrder
     * @return
     */
    String updateOrderInfo(NewBeeMallOrder newBeeMallOrder);

    /**
     * 获取订单从表详情-(管理端调用)
     * @param id
     * @return
     */
    List<NewBeeMallOrderItemVO> getOrderItems(Long id);

    /**
     * 批量修改订单状态-备货-(管理端调用)
     * @param ids
     * @return
     */
    String checkDone(Long[] ids);

    /**
     * 批量修改订单状态-出库-(管理端调用)
     * @param ids
     * @return
     */
    String checkOut(Long[] ids);

    /**
     * 批量修改订单状态-关闭订单-(管理端调用)
     * @param ids
     * @return
     */
    String closeOrder(Long[] ids);
}
