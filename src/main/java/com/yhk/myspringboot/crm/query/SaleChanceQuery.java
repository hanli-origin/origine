package com.yhk.myspringboot.crm.query;

import com.yhk.myspringboot.base.BaseQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    private Integer devResult;
    private Integer assignMan;

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

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public Integer getAssignMan() {
        return assignMan;
    }

    public void setAssignMan(Integer assignMan) {
        this.assignMan = assignMan;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customerName", customerName)
                .append("createMan", createMan)
                .append("state", state)
                .append("devResult", devResult)
                .append("assignMan", assignMan)
                .toString();
    }
}
