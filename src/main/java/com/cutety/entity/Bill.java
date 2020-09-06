package com.cutety.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Bill {
    private Integer id;

    private LocalDateTime billDate;

    private Integer typeId;

    private String remark;

    private Long consumptionAmount;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(Long consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}