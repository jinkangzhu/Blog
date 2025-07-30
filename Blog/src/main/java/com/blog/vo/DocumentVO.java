package com.blog.vo;

import lombok.Data;

@Data
public class DocumentVO {

    /**
     * 文档id
     */
    private String id;

    /**
     * 文档名称
     */
    private String docName;

    /**
     * 文档大小
     */
    private Long docSize;

    /**
     * 文档类型
     */
    private String docType;
}
