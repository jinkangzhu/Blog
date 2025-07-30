package com.blog.mapper;

import com.blog.entity.Document;
import com.blog.exception.PageResult;
import com.blog.vo.PageVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper {
    int insert(Document record);
    int deleteById(String id);
    int updateById(Document record);
    Document selectById(String id);
    List<Document> selectAll();
    List<Document> selectByCondition(Document condition);
    // 分页查询方法
    PageResult<Document> selectPage(@Param("pageVO") PageVO pageVO);
    // 带条件分页查询
    PageResult<Document> selectPageByCondition(@Param("condition") Document condition,
                                               @Param("pageVO") PageVO pageVO);
    // 统计总数
    int countAll();
    // 按条件统计
    int countByCondition(Document condition);
}
