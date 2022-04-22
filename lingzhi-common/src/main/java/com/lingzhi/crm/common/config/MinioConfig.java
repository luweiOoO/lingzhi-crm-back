package com.lingzhi.crm.common.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: LuWei
 * @Description minio配置类
 * @Date: Created in 14:11 2022/2/24
 * @Modified By:
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /** minio地址 */
    private String endpoint;

    /** minio端口 */
    private int port;

    /** minio账号 */
    private String accessKey;

    /** minio密码 */
    private String secretKey;

    /** 默认存储桶 */
    private String bucketName;

    /** 如果是true，则用的是https而不是http,默认值是true */
    private Boolean secure;

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(endpoint,port,accessKey,secretKey,secure);
    }
}
