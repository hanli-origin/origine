package com.yhk.myspringboot.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.query.SaleChanceQuery;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhk
 * @since 2023-03-26
 */
public interface ISaleChanceService extends IService<SaleChance> {

    Page<SaleChance> getSaleChanceByCondition(SaleChanceQuery query);
}
