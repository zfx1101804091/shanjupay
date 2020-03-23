package com.shanjupay.merchant.service.impl;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.merchant.service.FileService;
import com.shanjupay.merchant.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.BatchUpdateException;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/23 0023 23:51
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${ali.oss.endpoint}")
    private String endpoint;

    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${ali.oss.bucketName}")
    private String bucketName; 
    
    @Value("${ali.oss.url}")
    private String url;
    
    @Override
    public String upload(byte[] bytes, String fileName) throws BatchUpdateException {

        try {
            AliOssUtil.upload2AliOss(endpoint,accessKeyId,accessKeySecret,bucketName,bytes,fileName);
        } catch (Exception e){
            e.getStackTrace();
            throw new BusinessException(CommonErrorCode.E_100106);
        }
        //返回文件名称 
        return url+fileName;
    }

    @Override
    public String download(String fileName) throws IOException {
//            AliOssUtil.download(endpoint,accessKeyId,accessKeySecret,bucketName,fileName);
        AliOssUtil.downloadlocal(fileName);
        return "success";
    }
}
