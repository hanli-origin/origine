package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.mapper.SaleChanceMapper;
import com.yhk.myspringboot.crm.query.SaleChanceQuery;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yhk
 * @since 2023-03-26
 */
@Service
public class SaleChanceServiceImpl extends ServiceImpl<SaleChanceMapper, SaleChance> implements ISaleChanceService {

    @Override
    public Page<SaleChance> getSaleChanceByCondition(SaleChanceQuery query) {

        QueryWrapper<SaleChance> queryWrapper = new QueryWrapper<>();
        // 在QueryWrapper中设置条件
        queryWrapper.like("customer_name", query.getCustomerName())
                .and(w -> w.eq("create_man", query.getCreateMan()))
                .and(w -> w.eq("state", query.getState()))
                .and(w -> w.eq("is_valid", 1));
        // 构建分页对象
        Page<SaleChance> page = new Page<>(query.getPage(), query.getLimit());
        baseMapper.selectPage(page, queryWrapper);
        return page;
    }
}
