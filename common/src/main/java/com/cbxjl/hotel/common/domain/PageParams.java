package com.cbxjl.hotel.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表分页参数
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:15
 */
@Data
@NoArgsConstructor
public class PageParams {

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 上一次页的最后一条数据的id的名字
     * <p>
     * 当前页数操超过500时需要传这个条件
     */
    private String cursorName;

    /**
     * 上一次页的最后一条数据的id的值
     * <p>
     * 当前页数操超过500时需要传这个条件
     */
    private String cursorValue;

    public PageParams(Integer page, Integer pageSize) {
        this.pageNum = page;
        this.pageSize = pageSize;
    }


}
