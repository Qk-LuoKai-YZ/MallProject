package org.link.newbeemall.common;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallCategoryLevelEnum
 * Author:  7ink
 * Data:    2024/5/14 16:39
 * Description:三级联动的分类级别
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public enum NewBeeMallCategoryLevelEnum {

    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类"),
    LEVEL_THREE(3, "三级分类");
    private int level;

    private String name;

    NewBeeMallCategoryLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    // 设置级别
    public static NewBeeMallCategoryLevelEnum getNewBeeMallOrderStatusEnumByLevel(int level){
        for (NewBeeMallCategoryLevelEnum newBeeMallCategoryLevelEnum : NewBeeMallCategoryLevelEnum.values()) {
            if (newBeeMallCategoryLevelEnum.getLevel() == level) {
                return newBeeMallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

    /**
     * 获取
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
