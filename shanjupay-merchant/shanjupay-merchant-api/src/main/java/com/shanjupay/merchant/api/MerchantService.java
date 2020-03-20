package com.shanjupay.merchant.api;

import com.shanjupay.merchant.api.dto.MerchantDTO;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2020/3/20 0020 21:53
 */
public interface MerchantService {
    /*
     * 功能描述: DTO类型的对象作为service层传输的对象
     *  TODO 根据ID查询商户详情
     * @Param: [id]
     * @Return: com.shanjupay.merchant.api.dto.MerchantDTO
     * @Author: Administrator
     * @Date: 2020/3/20 0020 21:59
     */
    public MerchantDTO queryMerchantById(Long id);
}
