package com.blog.service.impl;

import com.blog.entity.ConfigLookupGroup;
import com.blog.mapper.ConfigLookupGroupMapper;
import com.blog.service.ConfigLookupGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigLookupGroupImpl implements ConfigLookupGroupService {


    @Autowired
    private ConfigLookupGroupMapper configLookupGroupMapper;

    /**
     * 根据ID查询配置分组
     */
    public ConfigLookupGroup findById(String id) {
        return configLookupGroupMapper.findById(id);
    }

    /**
     * 查询所有配置分组
     */
    public List<ConfigLookupGroup> findAll() {
        return configLookupGroupMapper.findAll();
    }

    /**
     * 插入配置分组
     */
    public void insert(ConfigLookupGroup configLookupGroup) {
        configLookupGroupMapper.insert(configLookupGroup);
    }

    /**
     * 更新配置分组
     */
    public void update(ConfigLookupGroup configLookupGroup) {
        configLookupGroupMapper.update(configLookupGroup);
    }

    /**
     * 删除配置分组
     */
    public void delete(String id) {
        configLookupGroupMapper.delete(id);
    }
}
