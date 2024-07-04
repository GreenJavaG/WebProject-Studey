package com.gq.uitl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//加上该注解后，spring可以根据名称直接向下面属性注入值
public class AliOSSProperties {
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
}
