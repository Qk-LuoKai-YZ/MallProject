package org.link.newbeemall.service;

import org.link.newbeemall.controller.vo.NewBeeMallIndexCategoryVO;
import org.link.newbeemall.dao.GoodsCategoryMapper;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallCategoryService
 * Author:  7ink
 * Data:    2024/5/14 14:03
 * Description:商品分类业务层接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallCategoryService {

    /**
     * 查询后台管理系统分页
     * @param pageUtil
     * @return
     */
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    /**
     * 新增分类记录
     * @param goodsCategory
     * @return
     */
    String saveCategory(GoodsCategory goodsCategory);

    /**
     * 修改分类记录
     * @param goodsCategory
     * @return
     */
    String updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 根据主键查询分类记录
     * @param id
     * @return
     */
    GoodsCategory getGoodsCatetoryById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    /**
     * 根据parentId和level获取 列表
     * @param parentIds
     * @param categoryLevel
     * @return
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);

    /**
     * 根据id查询分类记录
     * @param categoryId
     * @return
     */
    GoodsCategory getGoodsCategoryById(Long categoryId);

    /**
     * 返回三级分类数据(首页调用)
     * @return
     */
    List<NewBeeMallIndexCategoryVO> getCategoriesForIndex();
}
