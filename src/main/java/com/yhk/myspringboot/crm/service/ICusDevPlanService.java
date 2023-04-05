package com.yhk.myspringboot.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhk.myspringboot.crm.entity.CusDevPlan;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhk
 * @since 2023-04-04
 */
public interface ICusDevPlanService extends IService<CusDevPlan> {

    void saveCusDevPlan(CusDevPlan cusDevPlan);

    void updateCusDevPlan(CusDevPlan cusDevPlan);
}
