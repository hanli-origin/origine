package com.yhk.myspringboot.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.CusDevPlan;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.service.ICusDevPlanService;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private ISaleChanceService iSaleChanceService;
    @Autowired
    private ICusDevPlanService iCusDevPlanService;

    @RequestMapping("/index")
    public String index() {

        return "/cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultInfo<List<CusDevPlan>> list(Integer sid) {
        QueryWrapper<CusDevPlan> wrapper = new QueryWrapper<>();
        wrapper.eq("sale_chance_id", sid);
        List<CusDevPlan> list = iCusDevPlanService.list(wrapper);
        ResultInfo<List<CusDevPlan>> success = success("查询成功", list);
        success.setCode(0);
        return success;
    }

    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(String sid, HttpServletRequest request) {
        SaleChance saleChance = iSaleChanceService.getById(sid);
        request.setAttribute("saleChance", saleChance);
        return "/cusDevPlan/cus_dev_plan_data";
    }


    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo addCusDevPlan(CusDevPlan cusDevPlan) {
        if (!Optional.ofNullable(cusDevPlan).isPresent()) {
            return fail("添加失败", 500);
        }
        iCusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("添加成功");
    }

    @RequestMapping("/addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid, Integer id, HttpServletRequest request) {
        request.setAttribute("sid", sid);
        if (id != null) {
            CusDevPlan cusDevPlan = iCusDevPlanService.getById(id);
            request.setAttribute("cusDevPlan", cusDevPlan);
        }
        return "/cusDevPlan/add_update";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(CusDevPlan cusDevPlan) {
        if (!Optional.ofNullable(cusDevPlan).isPresent()) {
            return fail("参数错误", 500);
        }
        iCusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("更新成功");
    }
}
