package com.blog.mapper;

import com.blog.entity.Category;

import java.util.List;

public interface CategoryMapper {
    // 插入记录
    int insert(Category record);

    // 根据ID删除记录
    int deleteById(String id);

    // 根据ID更新记录
    int updateById(Category record);

    // 根据ID查询记录
    Category selectById(String id);

    // 查询所有记录
    List<Category> selectAll();

    // 根据父ID查询子分类
    List<Category> selectByParentId(String parentId);

    // 根据文档ID查询分类
    List<Category> selectByDocId(String docId);
}
