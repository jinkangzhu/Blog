package com.blog.controller;

import com.blog.entity.ConfigLookupGroup;
import com.blog.exception.BusinessMsgEnum;
import com.blog.exception.PageResult;
import com.blog.exception.ResponseResult;
import com.blog.service.ConfigLookupGroupService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configLookupGroup")
public class ConfigLookupGroupController {

    @Autowired
    private ConfigLookupGroupService configLookupGroupService;

    /**
     * 根据ID查询配置分组
     */
    @GetMapping("/{id}")
    public ResponseResult<ConfigLookupGroup> findById(@PathVariable String id) {
        return ResponseResult.success(configLookupGroupService.findById(id));
    }

    /**
     * 查询所有配置分组
     */
    @GetMapping("/list")
    public ResponseResult<List<ConfigLookupGroup>> findAll() {
        List<ConfigLookupGroup> configLookupGroups = configLookupGroupService.findAll();
        return ResponseResult.success(configLookupGroups);

    }

    /**
     * 分页查询
     */
    @GetMapping("/list/page")
    public ResponseResult<PageResult<ConfigLookupGroup>> findAll( @RequestParam(required = false) Integer pageNum,
                                                                  @RequestParam(required = false) Integer pageSize) {
        BusinessMsgEnum.ILLEGAL_ARGUMENT.assertNotNull(pageNum, "ConfigLookupGroup pageNum");
        BusinessMsgEnum.ILLEGAL_ARGUMENT.assertNotNull(pageSize, "ConfigLookupGroup pageSize");
        PageInfo<ConfigLookupGroup> pageInfo = configLookupGroupService.findAll(pageNum, pageSize);
        PageResult<ConfigLookupGroup> pageResult = new PageResult<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getList());
        return ResponseResult.success(pageResult);

    }

    /**
     * 插入配置分组
     */
    @PostMapping("/insert")
    public ResponseResult<String> insert(@RequestBody ConfigLookupGroup configLookupGroup) {
        configLookupGroupService.insert(configLookupGroup);
        return ResponseResult.success();
    }

    /**
     * 更新配置分组
     */
    @PostMapping("/update")
    public ResponseResult<Object> update(@RequestBody ConfigLookupGroup configLookupGroup) {
        configLookupGroupService.update(configLookupGroup);
        return ResponseResult.success();
    }

    /**
     * 删除配置分组
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<Object> delete(@PathVariable String id) {
        configLookupGroupService.delete(id);
        return ResponseResult.success();
    }
}