package com.blog.mapper;



import com.blog.entity.ConfigLookup;
import com.blog.entity.ConfigLookupGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfigLookupGroupMapper {

    ConfigLookupGroup findById(String id);

    void insert(ConfigLookupGroup configLookupGroup);

    void update(ConfigLookupGroup configLookupGroup);

    void delete(String id);

    List<ConfigLookupGroup> findAll();
}