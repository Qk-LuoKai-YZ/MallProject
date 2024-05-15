package org.link.newbeemall.service;

import org.link.newbeemall.entity.NewBeeMallGoods;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallGoodsService
 * Author:  7ink
 * Data:    2024/5/14 19:39
 * Description:商品管理的接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallGoodsService {

    /**
     * 商品搜索的列表
     * @param pageUtil
     * @return
     */
    PageResult serchNewBeeMallGoods(PageQueryUtil pageUtil);

    /**
     * 添加商品
     * @param newBeeMallGoods
     * @return
     */
    String SaveNewBeeMallGoods(NewBeeMallGoods newBeeMallGoods);


    /**
     * 获取商品详情
     * @param id
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long id);


    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);


    /**
     * 后台分页
     * @param pageQueryUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageQueryUtil);


    /**
     * 批量修改上下架
     * @param ids
     * @param sellStatus
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);
}
