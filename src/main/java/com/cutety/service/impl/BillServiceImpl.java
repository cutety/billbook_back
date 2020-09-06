package com.cutety.service.impl;

import com.cutety.entity.Bill;
import com.cutety.mapper.BillMapper;
import com.cutety.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "billService")
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public int addBill(Bill bill) {
        return billMapper.insert(bill);
    }

    @Override
    public List<Bill> findBillByUserId(Integer userId) {
        return billMapper.selectByUserId(userId);
    }
}
