package com.shanjupay.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.MerchantMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zheng_fx
 * @since 2020-03-20
 */
@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    
    //通过Id查询商户信息
    @Override
    public MerchantDTO queryMerchantById(Long id) {
        Merchant merchant = merchantMapper.selectById(id);
        MerchantDTO merchantDTO = new MerchantDTO();
        BeanUtils.copyProperties(merchant,merchantDTO);
        return merchantDTO;
    }

    @Transactional
    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        //设置审核状态0‐未申请,1‐已申请待审核,2‐审核通过,3‐审核拒绝
        merchant.setAuditStatus("0");
        //设置手机号 
        merchant.setMobile(merchantDTO.getMobile());
        merchant.setUsername(merchantDTO.getUsername());
        //TODO 设置其他属性
        
        //保存商户 
        merchantMapper.insert(merchant);
        //将新增商户ID返回
        merchantDTO.setId(merchant.getId());
        return merchantDTO;
    }

    @Override
    public MerchantDTO queryMerchantByPhone(String mobile) {
        LambdaQueryWrapper<Merchant> wrapper = new LambdaQueryWrapper<Merchant>().eq(Merchant::getMobile, mobile);
        Merchant merchant = merchantMapper.selectOne(wrapper);
        MerchantDTO merchantDTO = new MerchantDTO();
        if(null!=merchant){
            BeanUtils.copyProperties(merchant,merchantDTO);
            return merchantDTO;
        }
        return null;
    }


}
