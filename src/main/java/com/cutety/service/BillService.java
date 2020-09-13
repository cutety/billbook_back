package com.cutety.service;

import com.cutety.entity.Bill;
import com.cutety.entity.BillType;

import java.util.List;
import java.util.Map;

public interface BillService {
    int addBill(Bill bill);
    List<Bill> findBillByUserId(Integer userId);
    List<BillType> getBillType();
    List<Object> findBillByCategoryIdAndUserId(Map<String,Integer> params);
    int updateBill(Bill bill);
}
