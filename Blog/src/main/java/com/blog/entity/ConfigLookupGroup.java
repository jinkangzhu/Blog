package com.blog.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 配置分组表实体类
 */
@Data
public class ConfigLookupGroup extends BaseEntity{

    private String id; // 主键ID

    private String groupCode; // 分组编码

    private String groupName; // 分组名称

    private String description; // 配置描述

    private String status; // 状态

}