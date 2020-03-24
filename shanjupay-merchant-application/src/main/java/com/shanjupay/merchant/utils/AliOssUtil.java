package com.shanjupay.merchant.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/23 0023 23:04
 */
@Slf4j
public class AliOssUtil {


    /**
     * TODO 简单上传
     */
    public static void upload2AliOss(
            String endpoint
            , String accessKeyId
            , String accessKeySecret
            , String bucket
            , byte[] bytes
            , String fileName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucket, "gmall/images/shanjupay/" + fileName, new ByteArrayInputStream(bytes));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 流式下载
     */
    public static void download(
              String endpoint
            , String accessKeyId
            , String accessKeySecret
            , String bucket
            , String objectName) throws IOException {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucket, objectName);

        // 读取文件内容。
        System.out.println("Object content:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("\n" + line);
        }
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        reader.close();

        // 关闭OSSClient。
        ossClient.shutdown();

    }


    public static void downloadlocal(String fileUrl,String fileName) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(fileUrl);
            //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
            inputStream = url.openStream();
            File file = new File("home\\file\\"+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);

            int byteCount = 0;
            //1M逐个读取
            byte[] bytes = new byte[1024 * 1024];
            while ((byteCount = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, byteCount);
            }


        } catch (IOException e) {
            e.getStackTrace();
        } finally {

            inputStream.close();
            outputStream.close();
        }
    }
    
}
