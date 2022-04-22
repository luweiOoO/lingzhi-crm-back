package com.lingzhi.crm.common.utils.file;

import com.lingzhi.crm.common.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Author: LuWei
 * @Description minio文件工具类
 * @Date: Created in 14:18 2022/2/24
 * @Modified By:
 */
@Component
public class MinioUtils {

    private static final Logger log = LoggerFactory.getLogger(MinioUtils.class);

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;

    /**
     * 从minio下载文件
     * @param response
     * @param fileName
     */
    public void download(HttpServletResponse response,String fileName){
        InputStream in = null;
        try {
            ObjectStat stat = minioClient.statObject(minioConfig.getBucketName(), fileName);
            response.setContentType(stat.contentType());
//            response.setContentType("application/octet-stream; charset=UTF-8");
            in = minioClient.getObject(minioConfig.getBucketName(), fileName);
            IOUtils.copy(in,response.getOutputStream());
        }catch (Exception e){
            log.error("从minio下载文件报错:{}",e.getMessage());
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    log.info("关闭文件流报错:{}",e.getMessage());
                }
            }
        }
    }
}
