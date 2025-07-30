package com.blog.vo;

import lombok.Data;
import lombok.Getter;

@Getter
public class PageVO {

    /**
     * 大小
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Integer currentPage;

    private Integer offset;

    public PageVO(){
        this.currentPage=1;
        this.pageSize=10;
        this.offset = 0;
    }

    public PageVO(Integer currentPage,Integer pageSize){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        if (currentPage<=0){
            throw new IllegalArgumentException("分页页数不能小于1");
        }
        this.offset = (this.currentPage - 1) * this.pageSize;
    }


    public void setPageSize(Integer pageSize){
        if (pageSize>3500){
            this.pageSize =3500;
        }else {
            this.pageSize = pageSize;
        }
        this.offset = (currentPage - 1) * pageSize;
        this.offset = (this.currentPage - 1) * this.pageSize;
    }

    public void setCurrentPage(Integer currentPage){
        this.currentPage = currentPage;
        this.offset = (this.currentPage - 1) * this.pageSize;
    }

}
