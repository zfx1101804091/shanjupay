package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/*
 * 功能描述: 
 *  TODO 定义MerchantConvert转换类，使用@Mapper注解快速实现对象转换
 *   MapStruct是一个代码生成器，它基于约定优于配置的方法大大简化了Java Bean对象之间的映射转换的实现。
 *   MapStruct 使用简单的方法即可完成对象之间的转换，它速度快、类型安全且易于理解
 * @Param: 
 * @Return: 
 * @Author: Administrator
 * @Date: 2020/3/23 0023 7:06
 */
@Mapper
public interface MerchantCovert {
    
    //1、获取MerchantCovert实例
    MerchantCovert INSTANCE = Mappers.getMapper(MerchantCovert.class);
    
    //2、vo转DTO
    MerchantDTO vo2dto(MerchantRegisterVO merchantRegisterVO);
    
    //2、DTO转VO
    MerchantRegisterVO dto2vo(MerchantDTO merchantDTO);
}
