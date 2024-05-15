package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.IndexConfig;
import org.link.newbeemall.util.PageQueryUtil;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:IndexConfigMapper
 * Author:  7ink
 * Data:    2024/5/15 15:29
 * Description:首页配置实体
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface IndexConfigMapper {

    /**
     * 删除一条记录
     * @param configId
     * @return
     */
    int deleteByPrimaryKey(Long configId);

    /**
     * 保存一条新纪录
     * @param indexConfig
     * @return
     */
    int insert(IndexConfig indexConfig);

    /**
     * 保存一条新记录
     * @param indexConfig
     * @return
     */
    int insertSelective(IndexConfig indexConfig);

    /**
     * 根据主键查询记录
     * @param configId
     * @return
     */
    IndexConfig selectByPrimaryKey(Long configId);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(IndexConfig record);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(IndexConfig record);

    /**
     * 查询分页数据
     * @param pageUtil
     * @return
     */
    List<IndexConfig> findIndexConfigList(PageQueryUtil pageUtil);

    /**
     * 查询总数
     * @param pageUtil
     * @return
     */
    int getTotalIndexConfigs(PageQueryUtil pageUtil);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatach(Long[] ids);

    /**
     * 根据配置类型查询固定数量的记录
     * @param configType
     * @param number
     * @return
     */
    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);

    /**
     * 根据配置类型和商品id查询记录
     * @param configType
     * @param goodsId
     * @return
     */
    IndexConfig selectByTypeAndGoodsId(Byte configType, Long goodsId);

}
