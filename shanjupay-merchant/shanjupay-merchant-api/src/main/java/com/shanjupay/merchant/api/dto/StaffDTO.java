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
import java.util.Date;

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
@TableName("staff")
@ApiModel(value = "StaffDTO对象", description = "")
public class StaffDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "商户ID")
    @TableField("MERCHANT_ID")
    private Long merchantId;

    @ApiModelProperty(value = "姓名")
    @TableField("FULL_NAME")
    private String fullName;

    @ApiModelProperty(value = "职位")
    @TableField("POSITION")
    private String position;

    @ApiModelProperty(value = "用户名(关联统一用户)")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty(value = "手机号(关联统一用户)")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "员工所属门店")
    @TableField("STORE_ID")
    private Long storeId;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @ApiModelProperty(value = "0表示禁用，1表示启用")
    @TableField("STAFF_STATUS")
    private Boolean staffStatus;


}
