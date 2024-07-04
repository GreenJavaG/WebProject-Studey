package com.gq.uitl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    /*方法一：以配置文件+@Value("${数据全名称}")方式注入值*/
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    /*@Value("${aliyun.oss.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    String accessKeySecret;

    // 填写Bucket名称，例如examplebucket。
    @Value("${aliyun.oss.bucketName}")
    String bucketName;*/

    /*方法二：@ConfigurationProperties方式注入数据*/
    @Autowired
    private AliOSSProperties aliOSSProperties;
    /*
    * 实现上传图片到OSS
    * */
    public String upload(MultipartFile file) throws IOException {
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        //获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //构造唯一的文件名（不能重复） --使用uuid（通用唯一识别码）--1809408c-a196-491c-a241-d57e5d21f648
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);//截取到文件尾缀名
        String fileName = UUID.randomUUID().toString() + extname;
        //上传文件到OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, "图片/"+fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/" +"图片/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
}
