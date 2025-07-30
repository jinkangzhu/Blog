package com.blog.service;

import com.blog.entity.Document;
import com.blog.exception.PageResult;
import com.blog.vo.PageVO;

import java.util.List;

public interface DocumentService {
    int insert(Document document);
    int deleteById(String id);
    int updateById(Document document);
    Document selectById(String id);
    List<Document> selectAll();
    List<Document> selectByCondition(Document condition);
    // 分页查询
    PageResult<Document> selectPage(PageVO pageVO);
    // 带条件分页查询
    PageResult<Document> selectPageByCondition(Document condition, PageVO pageVO);
}
