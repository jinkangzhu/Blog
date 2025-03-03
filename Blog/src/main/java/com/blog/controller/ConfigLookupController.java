package com.blog.controller;

import com.blog.entity.ConfigLookup;
import com.blog.service.ConfigLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configLookup")
public class ConfigLookupController {

    @Autowired
    private ConfigLookupService configLookupService;

    /**
     * 根据ID查询配置项
     */
    @GetMapping("/{id}")
    public ConfigLookup findById(@PathVariable String id) {
        return configLookupService.findById(id);
    }

    /**
     * 查询所有配置项
     */
    @GetMapping("/list")
    public List<ConfigLookup> findAll() {
        return configLookupService.findAll();
    }

    /**
     * 插入配置项
     */
    @PostMapping("/insert")
    public String insert(@RequestBody ConfigLookup configLookup) {
        configLookupService.insert(configLookup);
        return "插入成功";
    }

    /**
     * 更新配置项
     */
    @PostMapping("/update")
    public String update(@RequestBody ConfigLookup configLookup) {
        configLookupService.update(configLookup);
        return "更新成功";
    }

    /**
     * 删除配置项
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        configLookupService.delete(id);
        return "删除成功";
    }
}