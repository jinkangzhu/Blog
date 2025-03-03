package com.blog.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConfigLookup extends ObjectEntity{

    private String id; // 主键ID

    private String configKey; // 配置键

    private String configValue; // 配置值

    private String groupCode; // 配置分组

    private String description; // 配置描述

    private String itemAttr1; // 扩展字段1

    private String itemAttr2; // 扩展字段2

    private String itemAttr3; // 扩展字段3

    private String itemAttr4; // 扩展字段4

    private String itemAttr5; // 扩展字段5

    private String itemAttr6; // 扩展字段6
}