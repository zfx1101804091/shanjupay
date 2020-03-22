package com.shanjupay.merchant.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zheng_fx
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("store")
@ApiModel(value = "StoreDTO对象", description = "")
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "门店名称")
    @TableField("STORE_NAME")
    private String storeName;

    @ApiModelProperty(value = "门店编号")
    @TableField("STORE_NUMBER")
    private Long storeNumber;

    @ApiModelProperty(value = "所属商户")
    @TableField("MERCHANT_ID")
    private Long merchantId;

    @ApiModelProperty(value = "父门店")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "0表示禁用，1表示启用")
    @TableField("STORE_STATUS")
    private Boolean storeStatus;

    @ApiModelProperty(value = "门店地址")
    @TableField("STORE_ADDRESS")
    private String storeAddress;


}
