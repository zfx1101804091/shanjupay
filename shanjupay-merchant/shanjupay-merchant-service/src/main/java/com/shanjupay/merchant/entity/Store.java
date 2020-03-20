package com.shanjupay.merchant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zheng_fx
 * @since 2020-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 门店名称
     */
    @TableField("STORE_NAME")
    private String storeName;

    /**
     * 门店编号
     */
    @TableField("STORE_NUMBER")
    private Long storeNumber;

    /**
     * 所属商户
     */
    @TableField("MERCHANT_ID")
    private Long merchantId;

    /**
     * 父门店
     */
    @TableField("PARENT_ID")
    private Long parentId;

    /**
     * 0表示禁用，1表示启用
     */
    @TableField("STORE_STATUS")
    private Boolean storeStatus;

    /**
     * 门店地址
     */
    @TableField("STORE_ADDRESS")
    private String storeAddress;


}
