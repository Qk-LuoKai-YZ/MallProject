package org.link.newbeemall.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.entity.Carousel;
import org.link.newbeemall.service.NewBeeMallCarouselService;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.Result;
import org.link.newbeemall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallCarouselController
 * Author:  7ink
 * Data:    2024/5/14 7:53
 * Description:轮播图的跳转逻辑
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Controller
@RequestMapping("/admin")
public class NewBeeMallCarouselController {


    @Resource
    NewBeeMallCarouselService newBeeMallCarouselService;
    @GetMapping("/carousels")
    public String carouselPage(HttpServletRequest request){
        request.setAttribute("path", "newbee_mall_carousel");

        return "admin/newbee_mall_carousel";
    }

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/carousels/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (ObjectUtil.isEmpty(params.get("page")) || ObjectUtil.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常!");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallCarouselService.getCarouselPage(pageUtil));
    }

    /**
     * 添加
     * @param carousel
     * @return
     */

    @RequestMapping(value = "/carousels/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Carousel carousel){
        if (!StringUtils.hasText(carousel.getCarouselUrl()) || Objects.isNull(carousel.getCarouselRank())){
            return ResultGenerator.genFailResult("参数异常!");
        }

        String result = newBeeMallCarouselService.saveCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * 修改
     * @param carousel
     * @return
     */

    @RequestMapping(value = "/carousels/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Carousel carousel){
        if (!StringUtils.hasText(carousel.getCarouselUrl()) || Objects.isNull(carousel.getCarouselRank()) || Objects.isNull(carousel.getCarouselId())){
            return ResultGenerator.genFailResult("参数异常!");
        }

        String result = newBeeMallCarouselService.updateCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 详情
     */
    @GetMapping("/carousels/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){

        Carousel carousel = newBeeMallCarouselService.getCarouselById(id);

        if (carousel == null) {
            return ResultGenerator.genFailResult("未查询到数据");
        }
        return ResultGenerator.genSuccessResult(carousel);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/carousels/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length < 1){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (newBeeMallCarouselService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
