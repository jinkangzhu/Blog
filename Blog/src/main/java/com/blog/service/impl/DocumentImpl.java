package com.blog.service.impl;


import com.blog.entity.Document;
import com.blog.exception.PageResult;
import com.blog.mapper.DocumentMapper;
import com.blog.service.DocumentService;
import com.blog.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentImpl implements DocumentService {
    @Autowired
    private  DocumentMapper documentMapper;

    @Override
    @Transactional
    public int insert(Document document) {
        // 业务逻辑示例：文档名称不能为空
        if (document.getDocName() == null || document.getDocName().trim().isEmpty()) {
            throw new IllegalArgumentException("文档名称不能为空");
        }
        return documentMapper.insert(document);
    }

    @Override
    @Transactional
    public int deleteById(String id) {
        return documentMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int updateById(Document document) {
        // 业务逻辑：更新时不能清空文档名称
        if (document.getDocName() != null && document.getDocName().trim().isEmpty()) {
            throw new IllegalArgumentException("文档名称不能为空");
        }
        return documentMapper.updateById(document);
    }

    @Override
    public Document selectById(String id) {
        return documentMapper.selectById(id);
    }

    @Override
    public List<Document> selectAll() {
        return documentMapper.selectAll();
    }

    @Override
    public List<Document> selectByCondition(Document condition) {
        return documentMapper.selectByCondition(condition);
    }

    @Override
    public PageResult<Document> selectPage(PageVO pageVO) {
        return documentMapper.selectPage(pageVO);
    }

    @Override
    public PageResult<Document> selectPageByCondition(Document condition,PageVO pageVO) {
        return documentMapper.selectPageByCondition(condition,pageVO);
    }


}
