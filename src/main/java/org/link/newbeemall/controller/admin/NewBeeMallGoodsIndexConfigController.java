package org.link.newbeemall.controller.admin;

import org.link.newbeemall.common.IndexConfigTypeEnum;
import org.link.newbeemall.common.NewBeeMallException;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.entity.IndexConfig;
import org.link.newbeemall.service.NewBeeMallIndexConfigService;
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
 * FileName:NewBeeMallGoodsIndexConfigController
 * Author:  7ink
 * Data:    2024/5/15 14:54
 * Description:首页配置管理控制器
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsIndexConfigController {

    @Resource
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;

    @GetMapping("/indexConfigs")
    public String indexConfigsPage(HttpServletRequest request, @RequestParam("configType") int configType){

        IndexConfigTypeEnum indexConfigTypeEnumByType = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);

        if (indexConfigTypeEnumByType.equals(IndexConfigTypeEnum.DEFAULT)) {
            NewBeeMallException.fail("参数异常");
        }
        request.setAttribute("path", indexConfigTypeEnumByType.getName());
        request.setAttribute("configType", configType);

        return "admin/newbee_mall_index_config";
    }

    /**
     * 列表
     * @param params
     * @return
     */

    @RequestMapping(value = "/indexConfigs/list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        if (Objects.isNull(params.get("page")) || Objects.isNull(params.get("limit")) || Objects.isNull(params.get("configType"))) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);

        return ResultGenerator.genSuccessResult(newBeeMallIndexConfigService.getConfigsPage(pageQueryUtil));
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/indexConfigs/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody IndexConfig indexConfig){
        if (Objects.isNull(indexConfig.getConfigType())
            || !StringUtils.hasText(indexConfig.getConfigName())
            || Objects.isNull(indexConfig.getConfigRank())){
            return ResultGenerator.genFailResult("参数异常");
        }

        String result = newBeeMallIndexConfigService.saveIndexConfig(indexConfig);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/indexConfigs/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody IndexConfig indexConfig) {
        if (Objects.isNull(indexConfig.getConfigType())
                || Objects.isNull(indexConfig.getConfigId())
                || !StringUtils.hasText(indexConfig.getConfigName())
                || Objects.isNull(indexConfig.getConfigRank())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = newBeeMallIndexConfigService.updateIndexConfig(indexConfig);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 详情
     */
    @GetMapping("/indexConfigs/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        IndexConfig config = newBeeMallIndexConfigService.getIndexConfigById(id);
        if (config == null) {
            return ResultGenerator.genFailResult("未查询到数据");
        }
        return ResultGenerator.genSuccessResult(config);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/indexConfigs/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (newBeeMallIndexConfigService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
