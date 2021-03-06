package com.shanjupay.merchant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanjupay.common.util.CommonResult;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.convert.MerchantCovert;
import com.shanjupay.merchant.service.FileService;
import com.shanjupay.merchant.service.SmsService;
import com.shanjupay.merchant.vo.MerchantRegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.asm.Advice;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.UUID;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/20 0020 22:10
 */
@Api(value = "商户平台‐商户相关", tags = "商户平台‐商户相关", description = "商户平台‐商户相关")
@RestController
public class MerchantController {

    @Reference
    private MerchantService merchantService;

    @Autowired
    private SmsService smsService;

    @Autowired
    StringRedisTemplate redisTemplate;
    
    @Autowired
    FileService fileService;

    @ApiOperation("根据id查询商户")
    @GetMapping("/merchants/{id}")
    public MerchantDTO queryMerchantById(@PathVariable("id") Long id) {
        return merchantService.queryMerchantById(id);
    }

    /*
        paramType = "query" 表示 ?phone=***  即问号带参的形式传递
     */
    @ApiOperation("获取手机验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "query")
    @GetMapping("/sms")
    public String getSMSCode(@RequestParam("phone") String phone) {
        return smsService.sendMsg(phone);
    }

    /*
        @ApiImplicitParam
            name:参数的名字
            value: 参数的解释：注册信息
            dataType: 数据类型
            paramType：表单的POST提交，参数在请求体（body）即：参数将来使用body的方式传
            
     */
    @ApiOperation("注册商户")
    @ApiImplicitParam(name = "merchantRegister", value = "注册信息", required = true, dataType = "MerchantRegisterVO", paramType = "body")
    @PostMapping("/merchants/register")
    public CommonResult registerMerchant(@RequestBody MerchantRegisterVO merchantRegister) {

        //先去数据库查该手机号是否已经注册过了
        MerchantDTO merchant = merchantService.queryMerchantByPhone(merchantRegister.getMobile());

        if (null == merchant) {
            //手机号未注册
            //说明验证码获取成功
            //短信接口注册成功，会将验证码存入redis key：sms:phone:code,所以这里我们直接从redis中获取code取校验
            String redisKey = "msg:" + merchantRegister.getMobile() + ":code";

            JSONObject redisValue = JSON.parseObject(redisTemplate.opsForValue().get(redisKey));
            String code = JSONObject.toJSONString(redisValue.get("code"));

            if (code.equals(merchantRegister.getVerifiyCode())) {
                //说明输入的验证码和手机验证码一致，开始注册业务
//                MerchantDTO merchantDTO = new MerchantDTO();
                //使用MapStruct进行类型转换，这里也可以用spring的BeanUtils.copyProperties()方法，此处为扩展
                // MapStruct 使用简单的方法即可完成对象之间的转换，它速度快、类型安全且易于理解。
                /*merchantDTO.setUsername(merchantRegister.getUsername());
                merchantDTO.setMobile(merchantRegister.getMobile());
                merchantDTO.setPassword(merchantRegister.getPassword());*/
                MerchantDTO merchantDTO = MerchantCovert.INSTANCE.vo2dto(merchantRegister);
                merchantService.createMerchant(merchantDTO);
                return new CommonResult().ok(200, "注册成功", merchantRegister);
            }

            return new CommonResult().error(405, "验证码校验失败，请输入正确的验证码");
        }
        return new CommonResult().error(405, "手机号已被注册,请更换手机号");
    }

    /**
     * TODO 使用阿里云的oss做文件存储服务
     *
     * @param file
     * @return
     */
    @ApiOperation("证件上传")
    @PostMapping("/upload")
    public String upload(@ApiParam(value = "上传的证件", required = true) @RequestParam("file") MultipartFile file) throws IOException, BatchUpdateException {
        //原始文件名称 
        String originalFilename = file.getOriginalFilename();
        //文件后缀 
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") - 1);
        //文件名称 
        String fileName = UUID.randomUUID().toString() + suffix;
        //上传文件，返回文件下载url
        String fileurl = fileService.upload(file.getBytes(), fileName);
        return fileurl;
    }


    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void download(
            @ApiParam(value = "oss文件url地址",required = true)
            @RequestParam("fileName")  String fileName) throws IOException{
        
      fileService.download(fileName);
    }
}
