package com.gq.controller;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.gq.uitl.AliOSSUtils;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /*文件上传*/
    /*本地存储*/
    //@PostMapping("/upload")
    public Result upload1(String username, Integer age , MultipartFile image) throws Exception {
        log.info("文件上传：{}，{}，{}",username,age,image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复） --使用uuid（通用唯一识别码）--1809408c-a196-491c-a241-d57e5d21f648
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);//截取到文件尾缀名
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        //将文件存储在服务器的磁盘目录中 E:\资料\day11-SpringBootWeb案例\资料\03. 文件上传
        image.transferTo(new File("E:\\资料\\day11-SpringBootWeb案例\\资料\\03. 文件上传\\"+newFileName));

        return Result.success();
    }

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload2(MultipartFile image) throws IOException {
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        //将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }
}
