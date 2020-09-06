package com.cutety.service;

import com.cutety.entity.Bill;

import java.util.List;

public interface BillService {
    int addBill(Bill bill);
    List<Bill> findBillByUserId(Integer userId);
}
