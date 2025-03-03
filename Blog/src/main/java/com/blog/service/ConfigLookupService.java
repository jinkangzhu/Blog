package com.blog.service;

import com.blog.entity.ConfigLookup;
import java.util.List;

public interface ConfigLookupService {

    ConfigLookup findById(String id);

    List<ConfigLookup> findAll();

    void insert(ConfigLookup configLookup);

    void update(ConfigLookup configLookup);

    void delete(String id);
}