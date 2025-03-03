package com.blog.mapper;

import com.blog.entity.ConfigLookup;
import java.util.List;

public interface ConfigLookupMapper {

    ConfigLookup findById(String id);

    List<ConfigLookup> findAll();

    void insert(ConfigLookup configLookup);

    void update(ConfigLookup configLookup);

    void delete(String id);
}