package com.blog.controller;

import com.blog.entity.ConfigLookupGroup;
import com.blog.service.ConfigLookupGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configLookupGroup")
public class ConfigLookupGroupController {

    @Autowired
    private ConfigLookupGroupService configLookupGroupService;

    /**
     * 根据ID查询配置分组
     */
    @GetMapping("/{id}")
    public ConfigLookupGroup findById(@PathVariable String id) {
        return configLookupGroupService.findById(id);
    }

    /**
     * 查询所有配置分组
     */
    @GetMapping("/list")
    public List<ConfigLookupGroup> findAll() {
        return configLookupGroupService.findAll();
    }

    /**
     * 插入配置分组
     */
    @PostMapping("/insert")
    public String insert(@RequestBody ConfigLookupGroup configLookupGroup) {
        configLookupGroupService.insert(configLookupGroup);
        return "插入成功";
    }

    /**
     * 更新配置分组
     */
    @PostMapping("/update")
    public String update(@RequestBody ConfigLookupGroup configLookupGroup) {
        configLookupGroupService.update(configLookupGroup);
        return "更新成功";
    }

    /**
     * 删除配置分组
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        configLookupGroupService.delete(id);
        return "删除成功";
    }
}