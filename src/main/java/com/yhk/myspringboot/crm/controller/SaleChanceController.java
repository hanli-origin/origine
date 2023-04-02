package com.yhk.myspringboot.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.SaleChance;
import com.yhk.myspringboot.crm.query.SaleChanceQuery;
import com.yhk.myspringboot.crm.service.ISaleChanceService;
import com.yhk.myspringboot.crm.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping("/addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, HttpServletRequest request) {
        Optional.ofNullable(id).ifPresent(s -> {
            SaleChance saleChance = iSaleChanceService.getById(s);
            request.setAttribute("saleChance", saleChance);
        });
        return "/saleChance/add_update";
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultInfo<SaleChance> save(SaleChance saleChance, HttpServletRequest request) {
        if (!Optional.ofNullable(saleChance).isPresent()) {
            return fail("参数不能为空", 500);
        }
        String userName = CookieUtil.getCookieValue(request, "userName");
        saleChance.setCreateMan(userName);
        iSaleChanceService.addSaleChance(saleChance);
        return success("营销机会添加成功");
    }


    @PostMapping("/update")
    @ResponseBody
    public ResultInfo<SaleChance> update(SaleChance saleChance) {
        if (!Optional.ofNullable(saleChance).isPresent()) {
            return fail("参数不能为空", 500);
        }
        iSaleChanceService.updateById(saleChance);
        return success("营销机会更新成功");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo<SaleChance> delete(Integer[] ids) {
        if (!Optional.ofNullable(ids).isPresent()) {
            return fail("参数错误", 500);
        }
        iSaleChanceService.deleteSaleChance(Arrays.asList(ids));
        return success();

    }
}
