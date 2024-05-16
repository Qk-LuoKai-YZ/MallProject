package org.link.newbeemall.controller.mall;

import org.link.newbeemall.common.Constants;
import org.link.newbeemall.common.NewBeeMallException;
import org.link.newbeemall.common.NewBeeMallOrderStatusEnum;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.controller.vo.NewBeeMallOrderDetailVO;
import org.link.newbeemall.controller.vo.NewBeeMallShoppingCartItemVO;
import org.link.newbeemall.controller.vo.NewBeeMallUserVO;
import org.link.newbeemall.entity.NewBeeMallOrder;
import org.link.newbeemall.service.NewBeeMallOrderService;
import org.link.newbeemall.service.NewBeeMallShoppingCartService;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.Result;
import org.link.newbeemall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:OrderController
 * Author:  7ink
 * Data:    2024/5/16 8:14
 * Description:订单模块控制器
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Controller
public class OrderController {

    @Autowired
    private NewBeeMallShoppingCartService newBeeMallShoppingCartService;

    @Resource
    private NewBeeMallOrderService newBeeMallOrderService;

    /**
     * 生成订单
     * @param httpSession
     * @return
     */

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession){
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);

        List<NewBeeMallShoppingCartItemVO> myShoppingCartItems = newBeeMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (!StringUtils.hasText(user.getAddress().trim())){
            // 没有设置收货地址
            NewBeeMallException.fail(ServiceResultEnum.NULL_ADDRESS_ERROR.getResult());
        }

        if (CollectionUtils.isEmpty(myShoppingCartItems)){
            // 购物车里面没有数据
            NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        }

        // 保存该订单并返回订单号
        String saveOrderResult = newBeeMallOrderService.saveOrder(user, myShoppingCartItems);
        // 跳转到订单详情页面
        return "redirect:/orders/" + saveOrderResult;
    }

    /**
     * 订单详情页面
     */
    @GetMapping("/orders/{orderNo}")
    public String orderDetailPage(HttpServletRequest request, @PathVariable("orderNo") String orderNo, HttpSession httpSession){
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        NewBeeMallOrderDetailVO orderDetailVO = newBeeMallOrderService.getOrderDetailByOrderNo(orderNo, user.getUserId());
        request.setAttribute("orderDetailVO", orderDetailVO);
        return "mall/order-detail";
    }

    /**
     * 我的订单列表
     * @param params
     * @param request
     * @param httpSession
     * @return
     */
    @GetMapping("/orders")
    public String orderListPage(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        params.put("userId", user.getUserId());
        if (ObjectUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.ORDER_SEARCH_PAGE_LIMIT);
        //封装我的订单数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("orderPageResult", newBeeMallOrderService.getMyOrders(pageUtil));
        request.setAttribute("path", "orders");
        return "mall/my-orders";
    }

    /**
     * 取消订单
     * @param orderNo
     * @param httpSession
     * @return
     */
    @PutMapping("/orders/{orderNo}/cancel")
    @ResponseBody
    public Result cancelOrder(@PathVariable("orderNo") String orderNo, HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        String cancelOrderResult = newBeeMallOrderService.cancelOrder(orderNo, user.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(cancelOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(cancelOrderResult);
        }
    }

    /**
     * 选择支付方式
     * @param request
     * @param orderNo
     * @param httpSession
     * @return
     */
    @GetMapping("/selectPayType")
    public String selectPayType(HttpServletRequest request, @RequestParam("orderNo") String orderNo, HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        NewBeeMallOrder newBeeMallOrder = newBeeMallOrderService.getNewBeeMallOrderByOrderNo(orderNo);
        //判断订单userId
        if (!user.getUserId().equals(newBeeMallOrder.getUserId())) {
            NewBeeMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        //判断订单状态
        if (newBeeMallOrder.getOrderStatus().intValue() != NewBeeMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
            NewBeeMallException.fail(ServiceResultEnum.ORDER_STATUS_ERROR.getResult());
        }
        request.setAttribute("orderNo", orderNo);
        request.setAttribute("totalPrice", newBeeMallOrder.getTotalPrice());
        return "mall/pay-select";
    }

    /**
     * 支付订单
     * @param request
     * @param orderNo
     * @param httpSession
     * @param payType
     * @return
     */
    @GetMapping("/payPage")
    public String payOrder(HttpServletRequest request, @RequestParam("orderNo") String orderNo, HttpSession httpSession, @RequestParam("payType") int payType) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        NewBeeMallOrder newBeeMallOrder = newBeeMallOrderService.getNewBeeMallOrderByOrderNo(orderNo);
        //判断订单userId
        if (!user.getUserId().equals(newBeeMallOrder.getUserId())) {
            NewBeeMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        //判断订单状态
        if (newBeeMallOrder.getOrderStatus().intValue() != NewBeeMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
            NewBeeMallException.fail(ServiceResultEnum.ORDER_STATUS_ERROR.getResult());
        }
        request.setAttribute("orderNo", orderNo);
        request.setAttribute("totalPrice", newBeeMallOrder.getTotalPrice());
        if (payType == 1) {
            return "mall/alipay";
        } else {
            return "mall/wxpay";
        }
    }

    /**
     * 支付成功了
     * @param orderNo
     * @param payType
     * @return
     */
    @GetMapping("/paySuccess")
    @ResponseBody
    public Result paySuccess(@RequestParam("orderNo") String orderNo, @RequestParam("payType") int payType) {
        String payResult = newBeeMallOrderService.paySuccess(orderNo, payType);
        if (ServiceResultEnum.SUCCESS.getResult().equals(payResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(payResult);
        }
    }

    /**
     * 结束订单--确认收货，结束订单
     * @param orderNo
     * @param httpSession
     * @return
     */
    @PutMapping("/orders/{orderNo}/finish")
    @ResponseBody
    public Result finishOrder(@PathVariable("orderNo") String orderNo, HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        String finishOrderResult = newBeeMallOrderService.finishOrder(orderNo, user.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(finishOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(finishOrderResult);
        }
    }
}
