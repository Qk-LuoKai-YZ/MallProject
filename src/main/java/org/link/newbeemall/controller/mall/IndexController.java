package org.link.newbeemall.controller.mall;

import org.link.newbeemall.common.Constants;
import org.link.newbeemall.common.IndexConfigTypeEnum;
import org.link.newbeemall.common.NewBeeMallException;
import org.link.newbeemall.config.NeeBeeMallWebMvcConfigurer;
import org.link.newbeemall.controller.vo.NewBeeMallIndexCarouselVO;
import org.link.newbeemall.controller.vo.NewBeeMallIndexCategoryVO;
import org.link.newbeemall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import org.link.newbeemall.service.NewBeeMallCarouselService;
import org.link.newbeemall.service.NewBeeMallCategoryService;
import org.link.newbeemall.service.NewBeeMallIndexConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:IndexController
 * Author:  7ink
 * Data:    2024/5/15 13:04
 * Description:处理商城的首页请求并跳转到对应路径
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */


@Controller
public class IndexController {

    @Autowired
    private NewBeeMallCarouselService newBeeMallCarouselService;

    @Autowired
    private NewBeeMallCategoryService newBeeMallCategoryService;


    @Autowired
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;
    @GetMapping({"/index","/","/index.html"})
    public String indexPage(HttpServletRequest request){

        List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            NewBeeMallException.fail("分类数据不完善");
        }

        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);

        List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);

        request.setAttribute("categories", categories);//分类数据
        request.setAttribute("carousels", carousels); //轮播图

        request.setAttribute("hotGoodses", hotGoodses);//热销商品
        request.setAttribute("newGoodses", newGoodses);//新品
        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
        return "mall/index";
    }
}
