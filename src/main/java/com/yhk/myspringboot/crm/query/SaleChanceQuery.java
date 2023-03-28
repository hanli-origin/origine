package com.yhk.myspringboot.crm.query;

import com.yhk.myspringboot.base.BaseQuery;

public class SaleChanceQuery extends BaseQuery {

    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 创建人
     */
    private String createMan;
    /**
     * 分配状态 0:未分配 1:已分配
     */
    private Integer state;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SaleChanceQuery{" +
                "customerName='" + customerName + '\'' +
                ", createMan='" + createMan + '\'' +
                ", state=" + state +
                '}';
    }
}
