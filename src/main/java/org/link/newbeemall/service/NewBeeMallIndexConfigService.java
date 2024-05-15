package org.link.newbeemall.service;

import org.link.newbeemall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import org.link.newbeemall.entity.IndexConfig;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallIndexConfigService
 * Author:  7ink
 * Data:    2024/5/15 15:56
 * Description:配置代码的业务层
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallIndexConfigService {

    /**
     * 查询后台管理系统-->首页配置的分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getConfigsPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条首页配置记录
     * @param indexConfig
     * @return
     */
    String saveIndexConfig(IndexConfig indexConfig);

    /**
     * 修改一条首页配置记录
     * @param indexConfig
     * @return
     */
    String updateIndexConfig(IndexConfig indexConfig);

    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     * @param configType
     * @param number
     * @return
     */
    List<NewBeeMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteBatch(Long[] ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    IndexConfig getIndexConfigById(Long id);
}
