package com.blog.service;

import com.blog.entity.ConfigLookup;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfigLookupService {

    ConfigLookup findById(String id);

    List<ConfigLookup> findAll();

    PageInfo<ConfigLookup> findAll(Integer pageNum, Integer pageSize);

    void insert(ConfigLookup configLookup);

    void update(ConfigLookup configLookup);

    void delete(String id);
}