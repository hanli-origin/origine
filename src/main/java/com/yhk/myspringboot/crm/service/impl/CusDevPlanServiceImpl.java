package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.crm.entity.CusDevPlan;
import com.yhk.myspringboot.crm.mapper.CusDevPlanMapper;
import com.yhk.myspringboot.crm.service.ICusDevPlanService;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import com.yhk.myspringboot.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yhk
 * @since 2023-04-04
 */
@Service
public class CusDevPlanServiceImpl extends ServiceImpl<CusDevPlanMapper, CusDevPlan> implements ICusDevPlanService {

    @Autowired
    private ISaleChanceService iSaleChanceService;

    @Override
    public void saveCusDevPlan(CusDevPlan cusDevPlan) {
        checkParams(cusDevPlan);
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(LocalDateTime.now());
        cusDevPlan.setUpdateDate(LocalDateTime.now());
        this.save(cusDevPlan);
    }

    private void checkParams(CusDevPlan cusDevPlan) {
        Integer sid = cusDevPlan.getSaleChanceId();
        AssertUtil.isTrue(sid == null || iSaleChanceService.getById(sid) == null, "数据异常");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "计划项内容不能为空");
        AssertUtil.isTrue(cusDevPlan.getPlanDate() == null, "计划日期不能为空");
    }
}
