package com.blog.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.blog.dto.LoginResponse;
import com.blog.entity.ConfigLookup;
import com.blog.entity.Document;
import com.blog.service.ConfigLookupService;
import com.blog.service.DocumentService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传API
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private SnowflakeGenerator snowflakeGenerator;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ConfigLookupService configLookupService;

    @PostMapping("/doc")
    public ResponseEntity<Document> upload(@RequestParam("file") MultipartFile file) throws IOException {
        ConfigLookup configLookup = configLookupService.queryLookupItem("upload_config", "path");
        String uploadDir = configLookup.getConfigValue();
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String extendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String docId = String.valueOf(snowflakeGenerator.next());
        File uploadedFile = new File(uploadDir + docId+extendName);
        file.transferTo(uploadedFile);
        Document document = new Document();
        document.setUpdateInfo();
        document.setId(docId);
        document.setDocName(fileName);
        document.setDocSize(file.getSize());
        document.setDocType(extendName.replace(".",""));
        documentService.insert(document);
        return ResponseEntity.ok(document);
    }
}
