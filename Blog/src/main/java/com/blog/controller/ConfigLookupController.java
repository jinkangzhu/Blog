package com.blog.controller;

import com.blog.entity.ConfigLookup;
import com.blog.exception.BusinessMsgEnum;
import com.blog.exception.PageResult;
import com.blog.exception.ResponseResult;
import com.blog.service.ConfigLookupService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configLookup")
public class ConfigLookupController {

    @Autowired
    private ConfigLookupService configLookupService;

    /**
     * 根据ID查询配置项
     */
    @GetMapping("/{id}")
    public ResponseResult<ConfigLookup> findById(@PathVariable String id) {
        return ResponseResult.success(configLookupService.findById(id));
    }

    /**
     * 查询所有配置项
     */
    @GetMapping("/list")
    public ResponseResult<List<ConfigLookup>> findAll() {
        return ResponseResult.success(configLookupService.findAll());
    }
    /**
     * 分页查询
     */
    @GetMapping("/list/page")
    public ResponseResult<PageResult<ConfigLookup>> findAll(@RequestParam(required = false) Integer pageNum,
                                                            @RequestParam(required = false) Integer pageSize) {
        BusinessMsgEnum.ILLEGAL_ARGUMENT.assertNotNull(pageNum, "ConfigLookup pageNum");
        BusinessMsgEnum.ILLEGAL_ARGUMENT.assertNotNull(pageSize, "ConfigLookup pageSize");
        PageInfo<ConfigLookup> pageInfo = configLookupService.findAll(pageNum, pageSize);
        PageResult<ConfigLookup> pageResult = new PageResult<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getList());
        return ResponseResult.success(pageResult);
    }

    /**
     * 插入配置项
     */
    @PostMapping("/insert")
    public ResponseResult<Object> insert(@RequestBody ConfigLookup configLookup) {
        configLookupService.insert(configLookup);
        return ResponseResult.success();
    }

    /**
     * 更新配置项
     */
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody ConfigLookup configLookup) {
        configLookupService.update(configLookup);
        return ResponseResult.success();
    }

    /**
     * 删除配置项
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id) {
        configLookupService.delete(id);
        return ResponseResult.success();
    }
}