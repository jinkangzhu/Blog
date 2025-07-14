package com.blog.service.impl;

import cn.hutool.core.lang.UUID;
import com.blog.entity.ConfigLookupGroup;
import com.blog.exception.PageResult;
import com.blog.mapper.ConfigLookupGroupMapper;
import com.blog.service.ConfigLookupGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigLookupGroupServiceImpl implements ConfigLookupGroupService {


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
     * 分页查询
     */
    public PageInfo<ConfigLookupGroup> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConfigLookupGroup> configLookupGroups = configLookupGroupMapper.findAll();
        return new PageInfo<>(configLookupGroups);
    }

    /**
     * 插入配置分组
     */
    public void insert(ConfigLookupGroup configLookupGroup) {
        configLookupGroup.setCreateInfo();
        configLookupGroup.setId(UUID.randomUUID().toString());
        configLookupGroupMapper.insert(configLookupGroup);
    }

    /**
     * 更新配置分组
     */
    public void update(ConfigLookupGroup configLookupGroup) {
        configLookupGroup.setUpdateInfo();
        configLookupGroupMapper.update(configLookupGroup);
    }

    /**
     * 删除配置分组
     */
    public void delete(String id) {
        configLookupGroupMapper.delete(id);
    }
}
