package com.yhk.myspringboot.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yhk
 * @since 2023-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sale_chance")
public class SaleChance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机会来源
     */
    private String chanceSource;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 成功几率
     */
    private Integer successPossibility;

    /**
     * 概要
     */
    private String overview;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 手机号
     */
    private String linkPhone;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createMan;

    /**
     * 分配人
     */
    private String assignMan;

    /**
     * 分配时间
     */
    private LocalDateTime assignTime;

    /**
     * 分配状态
     */
    private Integer state;

    /**
     * 开发结果
     */
    private Integer devResult;

    /**
     * 有效状态
     */
    private Integer isValid;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;


}
