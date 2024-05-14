package org.link.newbeemall.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import org.link.newbeemall.common.NewBeeMallCategoryLevelEnum;
import org.link.newbeemall.common.NewBeeMallException;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.service.NewBeeMallCarouselService;
import org.link.newbeemall.service.NewBeeMallCategoryService;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;
import org.link.newbeemall.util.Result;
import org.link.newbeemall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallGoodsCategoryController
 * Author:  7ink
 * Data:    2024/5/14 13:21
 * Description:分类管理模块控制器
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsCategoryController {

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request,
                                 @RequestParam("categoryLevel") Byte categoryLevel,
                                 @RequestParam("parentId") Long parentId,
                                 @RequestParam("backParentId") Long backParentId){
        if (categoryLevel == null || categoryLevel <1 || categoryLevel > 3){
            // return "error/error_5xx;
            // 使用自定义异常
            NewBeeMallException.fail("参数异常");
        }

        request.setAttribute("path", "newbee_mall_category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/newbee_mall_category";
    }

    /**
     * 三级联动测试-废弃
     * @param request
     * @return
     */
    @GetMapping("/coupling-test")
    public String couplingTest(HttpServletRequest request){
        return "admin/coupling-test";
    }

    /**
     * 列表分页
     */
    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (ObjectUtil.isEmpty(params.get("page"))
            || ObjectUtil.isEmpty(params.get("limit"))
                || ObjectUtils.isEmpty(params.get("categoryLevel"))
                || ObjectUtils.isEmpty(params.get("parentId"))){
            ResultGenerator.genFailResult("参数异常！");
        }

        PageQueryUtil pageUtil = new PageQueryUtil(params);

        PageResult categorisPage = newBeeMallCategoryService.getCategorisPage(pageUtil);

        return ResultGenerator.genSuccessResult(categorisPage);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody GoodsCategory goodsCategory){
        if (Objects.isNull(goodsCategory.getCategoryLevel())
                || !StringUtils.hasText(goodsCategory.getCategoryName())
                || Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        String result = newBeeMallCategoryService.saveCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody GoodsCategory goodsCategory){
        if (Objects.isNull(goodsCategory.getCategoryId())
                || Objects.isNull(goodsCategory.getCategoryLevel())
                || !StringUtils.hasText(goodsCategory.getCategoryName())
                || Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        String result = newBeeMallCategoryService.updateGoodsCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }

    }

    /**
     * 详情
     */
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id){

        GoodsCategory result = newBeeMallCategoryService.getGoodsCatetoryById(id);
        if (result == null) {
            return ResultGenerator.genFailResult("未查询到数据");
        }

        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 分类删除
     */
    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length < 1){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (newBeeMallCategoryService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    /**
     * 列表-三级联动
     */
    @RequestMapping(value = "/categories/listForSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result listForSelect(@RequestParam("categoryId") Long categoryId){
        if (categoryId == null || categoryId < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        GoodsCategory category = newBeeMallCategoryService.getGoodsCatetoryById(categoryId);

        // 不是一级分类 也不是 二级分类
        if (category == null || category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        Map categoryResult = new HashMap(4);
        if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {
            //如果是一级分类 则返回当前一级分类下的所有二级分类，以及二级分类列表中第一条数据下的所有三级分类列表
            //根据parentId和categoryLevel获取子分类列表
            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)){
                // 查询二级分类列表中第一个实体的所有的三级分类
                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());

                categoryResult.put("secondLevelCategories", secondLevelCategories);
                categoryResult.put("thirdLevelCategories", thirdLevelCategories);
            }
        }

        if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
            //如果是二级分类 则返回当前分类下的所有三级分类列表
            //根据parentId和categoryLevel获取子分类列表
            List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }
        return ResultGenerator.genSuccessResult(categoryResult);
    }
}
