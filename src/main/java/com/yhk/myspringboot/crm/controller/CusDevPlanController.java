package com.yhk.myspringboot.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cus_dev_plan")
public class CusDevPlanController {


    @RequestMapping("/index")
    public String index() {

        return "/cusDevPlan/cus_dev_plan";
    }
}
