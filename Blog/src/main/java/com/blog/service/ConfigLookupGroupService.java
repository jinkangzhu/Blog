package com.blog.service;

import com.blog.entity.ConfigLookupGroup;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfigLookupGroupService {
    ConfigLookupGroup findById(String id);

    List<ConfigLookupGroup> findAll();

    PageInfo<ConfigLookupGroup> findAll(Integer pageNum, Integer pageSize);

    void insert(ConfigLookupGroup configLookupGroup);

    void update(ConfigLookupGroup configLookupGroup);

    void delete(String id);

}
