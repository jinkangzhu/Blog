package com.blog.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.blog.config.ThreadExecutorConfiguration;
import com.blog.entity.ConfigLookup;
import com.blog.entity.Document;
import com.blog.service.ConfigLookupService;
import com.blog.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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

    @Autowired
    @Qualifier("suifengThreadExecutor")
    private ThreadPoolExecutor threadPoolExecutor;

    @PostMapping("/doc")
    public ResponseEntity<List<Document>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        ConfigLookup configLookup = configLookupService.queryLookupItem("upload_config", "path");
        String uploadDir = configLookup.getConfigValue();
        createFile(uploadDir);
        String fileName = file.getOriginalFilename();
        String extendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!extendName.endsWith(".zip")) {
            // 处理单个文件
            String docId = String.valueOf(snowflakeGenerator.next());
            File uploadedFile = new File(uploadDir + docId + extendName);
            file.transferTo(uploadedFile);
            Document document = new Document();
            document.setUpdateInfo();
            document.setId(docId);
            document.setDocName(fileName);
            document.setDocSize(file.getSize());
            document.setDocType(extendName.replace(".", ""));
            documentService.insert(document);
            return ResponseEntity.ok(Collections.singletonList(document));
        }
        // 处理zip类型压缩文件，对于其他压缩暂时不管了
        List<Document> documents = handlerZip(file, uploadDir);

        return ResponseEntity.ok(documents);
    }

    private List<Document> handlerZip(MultipartFile file, String uploadDir) throws IOException {
        ConfigLookup zipConfigLookup = configLookupService.queryLookupItem("upload_config", "zip_path");
        String zipDir = zipConfigLookup.getConfigValue();
        createFile(zipDir);
        // 创建临时文件
        File uploadedFile = new File(zipDir + file.getOriginalFilename());
        file.transferTo(uploadedFile);
        ZipFile zipFile = new ZipFile(uploadedFile, Charset.forName("GBK"));
        Enumeration<? extends ZipEntry> dirEntries = zipFile.entries();
        List<Document> documents = new ArrayList<>();
        try {
            while (dirEntries.hasMoreElements()) {
                ZipEntry entry = dirEntries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                String realName = entry.getName();
                if (entry.getName().contains("/")){
                    realName = entry.getName().substring(entry.getName().lastIndexOf("/")+1);
                }
                String entryExtendName = realName.substring(realName.lastIndexOf("."));
                String docId = String.valueOf(snowflakeGenerator.next());
                File outputDir = new File(uploadDir + docId + entryExtendName);
                Document document = new Document();
                document.setUpdateInfo();
                document.setId(docId);
                document.setDocName(realName);
                document.setDocSize(entry.getSize());
                document.setDocType(realName.replace(".", ""));
                documents.add(document);
                threadPoolExecutor.submit(() -> {
                    try {
                        zipEntryConvertFile(zipFile,entry,outputDir);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } finally {
            zipFile.close();
            FileUtil.del(uploadedFile);
        }
        documentService.batchInsert(documents);
        return documents;
    }

    private void createFile(String uploadDir) {
        File zipDir = new File(uploadDir);
        if (!zipDir.exists()){
            zipDir.mkdirs();
        }
    }

    public void zipEntryConvertFile(ZipFile zipFile, ZipEntry entry, File outputFile) throws IOException {
        // 确保父目录存在
        FileUtil.mkParentDirs(outputFile);

        // 使用 Hutool 复制流
        try (InputStream in = zipFile.getInputStream(entry);
             OutputStream out = FileUtil.getOutputStream(outputFile)) {
            IoUtil.copy(in, out);
        }
    }
}
