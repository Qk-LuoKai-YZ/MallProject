package org.link.newbeemall.common;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:PayStatusEnum
 * Author:  7ink
 * Data:    2024/5/16 19:22
 * Description:支付状态
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public enum PayStatusEnum {
    DEFAULT(-1, "支付失败"),
    PAY_ING(0, "支付中"),
    PAY_SUCCESS(1, "支付成功");

    private int payStatus;

    private String name;

    PayStatusEnum(int payStatus, String name) {
        this.payStatus = payStatus;
        this.name = name;
    }

    public static PayStatusEnum getPayStatusEnumByStatus(int payStatus) {
        for (PayStatusEnum payStatusEnum : PayStatusEnum.values()) {
            if (payStatusEnum.getPayStatus() == payStatus) {
                return payStatusEnum;
            }
        }
        return DEFAULT;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
