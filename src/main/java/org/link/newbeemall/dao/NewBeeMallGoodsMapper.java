package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.NewBeeMallGoods;
import org.link.newbeemall.entity.StockNumDTO;
import org.link.newbeemall.util.PageQueryUtil;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallGoodsMapper
 * Author:  7ink
 * Data:    2024/5/14 20:52
 * Description:商品管理模块的持久层接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallGoodsMapper {

    /**
     * 根据首页的搜索框 查询分页数据
     * @param pageUtil
     * @return
     */
    List<NewBeeMallGoods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    /**
     * 根据首页的搜索框 查询总数
     * @param pageUtil
     * @return
     */
    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);
    /**
     * 新增一条记录
     * @param record
     * @return
     */
    int insert(NewBeeMallGoods record);

    /**
     * 新增一条记录
     * @param record
     * @return
     */
    int insertSelective(NewBeeMallGoods record);

    /**
     * 根据主键查询商品信息
     * @param id
     * @return
     */
    NewBeeMallGoods selectByPrimaryKey(Long id);

    List<NewBeeMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    /**
     * 根据商品id和商品分类id查询商品信息
     * @param goodsName
     * @param goodsCategoryId
     * @return
     */
    NewBeeMallGoods selectByCategoryIdAndName(@Param("goodsName") String goodsName, @Param("goodsCategoryId") Long goodsCategoryId);


    /**
     * 修改一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(NewBeeMallGoods record);

    /**
     * 分页查询
     * @param pageQueryUtil
     * @return
     */
    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageQueryUtil);

    /**
     * 批量修改销售状态
     * @param goodsIds
     * @param sellStatus
     * @return
     */
    int batchUpdateSellStatus(@Param("goodsId") Long[] goodsIds,@Param("sellStatus") int sellStatus);

    /**
     * 批量修改库存-减少
     * @param stockNumDTOS
     * @return
     */
    // TODO 购物车结算商品大于一个的时候，动态sql批量减少库存sql报错
    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    /**
     * 批量修改库存-增加
     * @param stockNumDTOS
     * @return
     */
    int recoverStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);
}
