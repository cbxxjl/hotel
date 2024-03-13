package com.cbxjl.hotel.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表分页参数
 *
 * @author : cbxjl
 * @date : 2024/2/28 14:15
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 每页记录数
     */
    private int size;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int current;
    /**
     * 列表数据
     */
    private List<T> records;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<T> list, int totalCount, int pageSize, int currPage) {
        this.records = list;
        this.total = totalCount;
        this.size = pageSize;
        this.current = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public PageResult(IPage<T> page) {
        this.records = page.getRecords();
        this.total = (int) page.getTotal();
        this.size = (int) page.getSize();
        this.current = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
    }


    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<T> list, long totalCount, long pageSize, long currPage) {
        this.records = list;
        this.total = (int) totalCount;
        this.size = (int) pageSize;
        this.current = (int) currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }


}
