package com.yhk.myspringboot.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.query.SaleChanceQuery;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yhk
 * @since 2023-03-26
 */
@Controller
@RequestMapping("/sale_chance")
public class SaleChanceController extends BaseController {

    @Autowired
    ISaleChanceService iSaleChanceService;

    @GetMapping("/list")
    @ResponseBody
    public ResultInfo<List<SaleChance>> saleChanceList(SaleChanceQuery query) {
        System.out.println(query.toString());
        Page<SaleChance> scpage = iSaleChanceService.getSaleChanceByCondition(query);
        ResultInfo<List<SaleChance>> result = success("query saleChanceList success", scpage.getRecords());
        result.setCount(scpage.getTotal());
        result.setCode(0);
        return result;
    }

    @RequestMapping("/index")
    public String index() {

        return "/saleChance/sale_chance";
    }
}
