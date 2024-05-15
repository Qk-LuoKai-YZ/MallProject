package org.link.newbeemall.service.impl;

import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.dao.GoodsCategoryMapper;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.service.NewBeeMallCategoryService;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallCategoryServiceImpl
 * Author:  7ink
 * Data:    2024/5/14 14:35
 * Description:分类管理的实现类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
@Service
public class NewBeeMallCategoryServiceImpl implements NewBeeMallCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        List<GoodsCategory> categoryList = goodsCategoryMapper.findGoodsCategoryList(pageUtil);
        int total = goodsCategoryMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList,total,pageUtil.getLimit(),pageUtil.getPage());

        return pageResult;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {

        GoodsCategory temp = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp != null) {
            // 已存在分类
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (goodsCategoryMapper.insertSelective(goodsCategory)> 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
        if (temp == null) {
            // 不存在要更新的分类
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory temp2 = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(goodsCategory.getCategoryId())){
            // 同名但是不同id 不能继续修改
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }

        goodsCategory.setUpdateTime(new Date());

        if (goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)> 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsCategory getGoodsCatetoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1){
            return false;
        }
        return goodsCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);//0代表查询所有

    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long categoryId) {
        return goodsCategoryMapper.selectByPrimaryKey(categoryId);
    }
}
