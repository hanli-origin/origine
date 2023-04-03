package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.enums.DevResult;
import com.yhk.myspringboot.crm.enums.StateStatus;
import com.yhk.myspringboot.crm.mapper.SaleChanceMapper;
import com.yhk.myspringboot.crm.query.SaleChanceQuery;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import com.yhk.myspringboot.crm.utils.AssertUtil;
import com.yhk.myspringboot.crm.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        // 在QueryWrapper中设置条件
        LambdaQueryWrapper<SaleChance> lambdaWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(query.getCustomerName())) {
            lambdaWrapper.like(SaleChance::getCustomerName, query.getCustomerName());
        }
        if (StringUtils.isNotBlank(query.getCreateMan())) {
            lambdaWrapper.eq(SaleChance::getCreateMan, query.getCreateMan());
        }
        Optional.ofNullable(query.getState()).ifPresent(state -> lambdaWrapper.eq(SaleChance::getState, state));
        // 构建分页对象
        Page<SaleChance> page = new Page<>(query.getPage(), query.getLimit());
        baseMapper.selectPage(page, lambdaWrapper);
        return page;
    }

    public void addSaleChance(SaleChance saleChance) {
        saleChance.setIsValid(1);
        saleChance.setCreateDate(LocalDateTime.now());
        if (StringUtils.isBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
        } else {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(LocalDateTime.now());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }

        checkParam(saleChance);
        AssertUtil.isTrue(!save(saleChance), "添加失败");
    }

    @Override
    public void updateSaleChance(SaleChance saleChance) {
        boolean present = Optional.ofNullable(saleChance.getId()).isPresent();
        AssertUtil.isTrue(!present, "要更新的记录不存在");
        boolean chance = Optional.ofNullable(getById(saleChance.getId())).isPresent();
        AssertUtil.isTrue(!chance, "要更新的记录不存在");
        // 参数校验
        checkParam(saleChance);
        AssertUtil.isTrue(!updateById(saleChance), "更新失败");
    }

    @Override
    public void deleteSaleChance(List<Integer> ids) {
        AssertUtil.isTrue(!Optional.ofNullable(ids).isPresent() || ids.size() == 0, "请选择待删除记录!");
        AssertUtil.isTrue(!removeByIds(ids), "记录删除失败!");
    }

    /**
     * 参数校验
     *
     * @param saleChance
     */
    private void checkParam(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()), "客户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()), "联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()), "联系人不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(saleChance.getLinkPhone()), "手机号码错误");
    }
}
