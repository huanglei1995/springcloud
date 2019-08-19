package com.cnshop.member.mapper.entity;

import lombok.Data;

import java.util.Date;

/**
 * create by hl on 2019/8/19 23:27
 *
 * @descript
 */
@Data
public class BaseDo {

    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     *
     */
    private Date updateTime;
    /**
     * id
     */
    private Long id;

    /**
     * 是否可用 0可用 1不可用
     */
    private Long isAvailability;

}
