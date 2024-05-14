package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.util.PageQueryUtil;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:GoodsCategoryMapper
 * Author:  7ink
 * Data:    2024/5/14 14:01
 * Description:商品分类持久层代码
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface GoodsCategoryMapper {

    /**
     * 删除一条记录
     * @param categoryId
     * @return
     */
    int deleteByPrimaryKey(Long categoryId);

    /**
     * 保存一条记录
     * @param record
     * @return
     */
    int insert(GoodsCategory record);

    /**
     * 保存一条记录
     * @param record
     * @return
     */
    int insertSelective(GoodsCategory record);

    /**
     * 根据主键查询记录
     * @param categoryId
     * @return
     */
    GoodsCategory selectByPrimaryKey(Long categoryId);

    /**
     * 根据 分类等级、分类名称 查询记录
     * @param categoryLevel
     * @param categoryName
     * @return
     */
    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GoodsCategory record);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(GoodsCategory record);

    /**
     * 分页列表
     * @param pageUtil
     * @return
     */
    List<GoodsCategory> findGoodsCategoryList(PageQueryUtil pageUtil);

    /**
     * 查询总数
     * @param pageUtil
     * @return
     */
    int getTotalGoodsCategories(PageQueryUtil pageUtil);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    /**
     * 根据父类的分类，分类等级和数量 分页列表
     * @param parentIds
     * @param categoryLevel
     * @param number
     * @return
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds,
                                                           @Param("categoryLevel") int categoryLevel,
                                                           @Param("number") int number);


}
