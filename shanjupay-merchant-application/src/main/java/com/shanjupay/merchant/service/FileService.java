package com.shanjupay.merchant.service;

import java.io.IOException;
import java.sql.BatchUpdateException;

public interface FileService {

    /*
     * 功能描述: TODO 上传文件
     *  bytes 文件字节  fileName 文件名称
     * @Param: [bytes, fileName]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/3/23 0023 22:57
     */
    public String upload(byte[] bytes,String fileName) throws BatchUpdateException;
    
    public String download(String fileName) throws IOException;
}
