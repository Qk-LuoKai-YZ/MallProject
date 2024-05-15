
package org.link.newbeemall.service;


import org.link.newbeemall.controller.vo.NewBeeMallIndexCarouselVO;
import org.link.newbeemall.entity.Carousel;
import org.link.newbeemall.entity.GoodsCategory;
import org.link.newbeemall.util.PageQueryUtil;
import org.link.newbeemall.util.PageResult;

import java.util.List;

public interface NewBeeMallCarouselService {

    /**
     * 获取轮播图分页数据
     * @param pageUtil 分页查询工具对象，包含分页和排序信息
     * @return 返回轮播图的分页结果，包括总数和数据列表
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    /**
     * 保存轮播图信息
     * @param carousel 轮播图对象，包含轮播图的全部信息
     * @return 返回保存操作的结果信息，通常为成功或失败的消息
     */
    String saveCarousel(Carousel carousel);

    /**
     * 更新轮播图信息
     * @param carousel 轮播图对象，包含需要更新的轮播图信息
     * @return 返回更新操作的结果信息，通常为成功或失败的消息
     */
    String updateCarousel(Carousel carousel);

    /**
     * 根据ID获取轮播图信息
     * @param id 轮播图的ID，用于唯一标识一个轮播图
     * @return 返回根据ID查询到的轮播图对象
     */
    Carousel getCarouselById(Integer id);

    /**
     * 批量删除轮播图
     * @param ids 轮播图ID数组，用于指定需要删除的轮播图
     * @return 返回批量删除操作的结果，通常为成功或失败的布尔值
     */
    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回固定数量的轮播图对象(首页调用)
     * @param indexCarouselNumber
     * @return
     */
    List<NewBeeMallIndexCarouselVO> getCarouselsForIndex(int indexCarouselNumber);
}
