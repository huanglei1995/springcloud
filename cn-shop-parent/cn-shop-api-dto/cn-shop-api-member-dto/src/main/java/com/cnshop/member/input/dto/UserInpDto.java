package com.cnshop.member.input.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

/**
 * create by hl on 2019/8/18 21:05
 * @descript: 用户输入dto
 */
@Data
@ApiOperation(value = "用户信息实体类")
public class UserInpDto {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户性别")
    private char sex;

    @ApiModelProperty(value = "用户年龄")
    private Long age;

    @ApiModelProperty(value = "账号是否可用1：正常0：冻结")
    private char is_avaliable;

    @ApiModelProperty(value = "用户头像")
    private String pic_img;

    private Date createTime;

    private Date updateTime;

    /**
     * 用户关联 QQ 开放ID
     */
    @ApiModelProperty(value = "用户关联 QQ 开放ID")
    private String qqOpenid;
    /**
     * 用户关联 微信 开放ID
     */
    @ApiModelProperty(value = "用户关联 微信 开放ID")
    private String wxOpenid;
}
