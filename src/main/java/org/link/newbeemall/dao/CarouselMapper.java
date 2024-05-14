package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.Carousel;
import org.link.newbeemall.util.PageQueryUtil;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:CarouselMapper
 * Author:  7ink
 * Data:    2024/5/14 8:21
 * Description:轮播图实体的dao
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface CarouselMapper {

    /**
     * 删除一条记录
     * @param carouselId
     * @return
     */
    int deleteByPrimaryKey(Integer carouselId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    /**
     * 保存一条记录
     * @param record
     * @return
     */
    int insert(Carousel record);

    /**
     * 保存一条记录
     * @param record
     * @return
     */
    int insertSelective(Carousel record);

    /**
     * 根据主键查询记录
     * @param carouselId
     * @return
     */
    Carousel selectByPrimaryKey(Integer carouselId);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Carousel record);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Carousel record);

    /**
     * 查询分页数据
     * @param pageQueryUtil
     * @return
     */
    List<Carousel> findCarouselList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalCarousels(PageQueryUtil pageQueryUtil);

    /**
     * 查询固定数量的记录
     * @param number
     * @return
     */
    List<Carousel> findCarouselsByNum(@Param(("number")) int number);
}
