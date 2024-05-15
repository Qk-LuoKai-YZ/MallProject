package org.link.newbeemall.service.impl;

import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.controller.vo.NewBeeMallIndexCarouselVO;
import org.link.newbeemall.dao.CarouselMapper;
import org.link.newbeemall.entity.Carousel;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.service.NewBeeMallCarouselService;
import org.link.newbeemall.util.BeanUtil;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallCarouselServiceImpl
 * Author:  7ink
 * Data:    2024/5/14 8:55
 * Description:轮播图业务层代码实现
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Service
public class NewBeeMallCarouselServiceImpl implements NewBeeMallCarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public PageResult getCarouselPage(PageQueryUtil pageUtil) {
        List<Carousel> carouselList = carouselMapper.findCarouselList(pageUtil);
        int total = carouselMapper.getTotalCarousels(pageUtil);
        PageResult pageResult = new PageResult(carouselList, total, pageUtil.getLimit(), pageUtil.getPage());

        return pageResult;
    }

    @Override
    public String saveCarousel(Carousel carousel) {
        if (carouselMapper.insertSelective(carousel) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateCarousel(Carousel carousel) {

        Carousel temp = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        if (null == temp){
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }

        temp.setCarouselRank(carousel.getCarouselRank());
        temp.setRedirectUrl(carousel.getRedirectUrl());
        temp.setCarouselUrl(carousel.getCarouselUrl());
        temp.setUpdateTime(new Date());
        if (carouselMapper.updateByPrimaryKeySelective(temp) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Carousel getCarouselById(Integer id) {
        return carouselMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1){
            return false;
        }
        return carouselMapper.deleteBatch(ids) > 0;
    }


    @Override
    public List<NewBeeMallIndexCarouselVO> getCarouselsForIndex(int number) {
        List<NewBeeMallIndexCarouselVO> newBeeMallIndexCarouselVOS = new ArrayList<>(number);

        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);

        if (!CollectionUtils.isEmpty(carousels)){
            // 本质还是调用了 BeanUtils.copyProperties(source, target, ignoreProperties);
            newBeeMallIndexCarouselVOS = BeanUtil.copyList(carousels, NewBeeMallIndexCarouselVO.class);
        }
        return newBeeMallIndexCarouselVOS;
    }
}
