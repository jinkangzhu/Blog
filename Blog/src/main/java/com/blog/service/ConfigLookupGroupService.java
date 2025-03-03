package com.blog.service;

import com.blog.entity.ConfigLookupGroup;

import java.util.List;

public interface ConfigLookupGroupService {
    ConfigLookupGroup findById(String id);

    List<ConfigLookupGroup> findAll();

    void insert(ConfigLookupGroup configLookupGroup);

    void update(ConfigLookupGroup configLookupGroup);

    void delete(String id);

}
