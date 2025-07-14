package com.blog.exception;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T>  implements Serializable {
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> data;

    public PageResult() {}
    public PageResult(int pageNum, int pageSize, long total, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }
}
