package com.blog.mapper;

import com.blog.entity.ConfigLookup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigLookupMapper {

    ConfigLookup findById(String id);

    List<ConfigLookup> findAll();

    void insert(ConfigLookup configLookup);

    void update(ConfigLookup configLookup);

    void delete(String id);


    List<ConfigLookup> queryLookupItems(@Param("groupCode") String groupCode);

    ConfigLookup queryLookupItem(@Param("groupCode") String groupCode,@Param("itemCode") String itemCode);
}