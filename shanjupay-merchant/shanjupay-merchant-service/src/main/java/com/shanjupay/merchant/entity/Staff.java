package com.shanjupay.merchant.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

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
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 商户ID
     */
    @TableField("MERCHANT_ID")
    private Long merchantId;

    /**
     * 姓名
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * 职位
     */
    @TableField("POSITION")
    private String position;

    /**
     * 用户名(关联统一用户)
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 手机号(关联统一用户)
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 员工所属门店
     */
    @TableField("STORE_ID")
    private Long storeId;

    /**
     * 最后一次登录时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 0表示禁用，1表示启用
     */
    @TableField("STAFF_STATUS")
    private Boolean staffStatus;


}