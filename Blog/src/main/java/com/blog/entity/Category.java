package com.blog.entity;

import lombok.Data;

/**
 * 分类定义实体类
 * 对应数据库表：def_category
 */
@Data
public class Category extends BaseEntity{

    /**
     * 主键ID
     * 数据库字段：id VARCHAR(64) NOT NULL
     */
    private String id;

    /**
     * 文档ID
     * 数据库字段：doc_id VARCHAR(64)
     */
    private String docId;

    /**
     * 分类等级
     * 数据库字段：level VARCHAR(10)
     * 示例值："1"/"2"/"3" 表示一级/二级/三级分类
     */
    private String level;

    /**
     * 父级分类ID
     * 数据库字段：parent_id VARCHAR(64) NOT NULL
     * 说明：根节点的parent_id通常指向特定标识（如0或空）
     */
    private String parentId;

    /**
     * 分类名称
     * 数据库字段：category_name VARCHAR(255)
     * 注意：原始表结构字段名拼写为catecory_name
     */
    private String categoryName;
}