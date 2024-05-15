package org.link.newbeemall.service.impl;

import org.link.newbeemall.common.NewBeeMallCategoryLevelEnum;
import org.link.newbeemall.common.NewBeeMallException;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.dao.GoodsCategoryMapper;
import org.link.newbeemall.dao.NewBeeMallGoodsMapper;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.entity.NewBeeMallGoods;
import org.link.newbeemall.service.NewBeeMallGoodsService;
import org.link.newbeemall.util.NewBeeMallUtils;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallGoodsServiceImpl
 * Author:  7ink
 * Data:    2024/5/14 19:39
 * Description:商品管理接口的实现类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper newBeeMallGoodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public String SaveNewBeeMallGoods(NewBeeMallGoods newBeeMallGoods) {

        if ( newBeeMallGoodsMapper.insertSelective(newBeeMallGoods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }else {
            return ServiceResultEnum.DB_ERROR.getResult();
        }
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long id) {
        NewBeeMallGoods newBeeMallGoods = newBeeMallGoodsMapper.selectByPrimaryKey(id);

        if (newBeeMallGoods == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        return newBeeMallGoods;
    }

    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goods.getGoodsCategoryId());
        // 分类不存在或者不是三级分类，则该参数字段异常
        if (goodsCategory == null || goodsCategory.getCategoryLevel().intValue() != NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        NewBeeMallGoods temp = newBeeMallGoodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        NewBeeMallGoods temp2 = newBeeMallGoodsMapper.selectByCategoryIdAndName(goods.getGoodsName(), goods.getGoodsCategoryId());
        if (temp2 != null && !temp2.getGoodsId().equals(goods.getGoodsId())) {
            //name和分类id相同且不同id 不能继续修改
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }

        // 清理字符串
        goods.setGoodsName(NewBeeMallUtils.cleanString(goods.getGoodsName()));
        goods.setGoodsIntro(NewBeeMallUtils.cleanString(goods.getGoodsIntro()));
        goods.setTag(NewBeeMallUtils.cleanString(goods.getTag()));
        goods.setUpdateTime(new Date());
        if (newBeeMallGoodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageQueryUtil) {
        List<NewBeeMallGoods> goodsList = newBeeMallGoodsMapper.findNewBeeMallGoodsList(pageQueryUtil);
        int total = newBeeMallGoodsMapper.getTotalNewBeeMallGoods(pageQueryUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return newBeeMallGoodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }
}
