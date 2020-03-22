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
@TableName("app")
@ApiModel(value = "AppDTO对象", description = "")
public class AppDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @TableField("APP_ID")
    private String appId;

    @ApiModelProperty(value = "商店名称")
    @TableField("APP_NAME")
    private String appName;

    @ApiModelProperty(value = "所属商户")
    @TableField("MERCHANT_ID")
    private Long merchantId;

    @ApiModelProperty(value = "应用公钥(RSAWithSHA256)")
    @TableField("PUBLIC_KEY")
    private String publicKey;

    @ApiModelProperty(value = "授权回调地址")
    @TableField("NOTIFY_URL")
    private String notifyUrl;


}
