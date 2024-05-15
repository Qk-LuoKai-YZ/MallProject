package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.NewBeeMallGoods;
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
     * 总数查询
     * @param pageQueryUtil
     * @return
     */
    int getTotalNewBeeMallGoods(PageQueryUtil pageQueryUtil);

    /**
     * 批量修改销售状态
     * @param goodsIds
     * @param sellStatus
     * @return
     */
    int batchUpdateSellStatus(@Param("goodsId") Long[] goodsIds,@Param("sellStatus") int sellStatus);
}
