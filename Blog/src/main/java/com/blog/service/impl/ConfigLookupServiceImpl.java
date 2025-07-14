package com.blog.service.impl;

import cn.hutool.core.lang.UUID;
import com.blog.entity.ConfigLookup;
import com.blog.entity.ConfigLookupGroup;
import com.blog.mapper.ConfigLookupMapper;
import com.blog.service.ConfigLookupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConfigLookupServiceImpl implements ConfigLookupService {

    @Autowired
    private ConfigLookupMapper configLookupMapper;

    @Override
    public ConfigLookup findById(String id) {
        return configLookupMapper.findById(id);
    }

    @Override
    public List<ConfigLookup> findAll() {
        return configLookupMapper.findAll();
    }

    @Override
    public PageInfo<ConfigLookup> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConfigLookup> configLookups = configLookupMapper.findAll();
        return new PageInfo<>(configLookups);
    }

    @Override
    public void insert(ConfigLookup configLookup) {
        configLookup.setCreateInfo();
        configLookup.setId(UUID.randomUUID().toString());
        configLookupMapper.insert(configLookup);
    }

    @Override
    public void update(ConfigLookup configLookup) {
        configLookup.setUpdateInfo();
        configLookupMapper.update(configLookup);
    }

    @Override
    public void delete(String id) {
        configLookupMapper.delete(id);
    }
}