package com.lingzhi.crm.web.controller.common;

import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.utils.file.MinioUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LuWei
 * @Description minio相关接口
 * @Date: Created in 14:45 2022/2/24
 * @Modified By:
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

    private static final Logger log = LoggerFactory.getLogger(MinioController.class);

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response,@RequestParam("fileName") String filePath){
        minioUtils.download(response,filePath);
    }
}
