package com.blog.service.impl;

import com.blog.entity.ConfigLookup;
import com.blog.mapper.ConfigLookupMapper;
import com.blog.service.ConfigLookupService;
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
    public void insert(ConfigLookup configLookup) {
        // 设置默认值
        configLookup.setCreatedDate(LocalDateTime.now());
        configLookup.setUpdatedDate(LocalDateTime.now());
        configLookupMapper.insert(configLookup);
    }

    @Override
    public void update(ConfigLookup configLookup) {
        // 设置更新时间
        configLookup.setUpdatedDate(LocalDateTime.now());
        configLookupMapper.update(configLookup);
    }

    @Override
    public void delete(String id) {
        configLookupMapper.delete(id);
    }
}