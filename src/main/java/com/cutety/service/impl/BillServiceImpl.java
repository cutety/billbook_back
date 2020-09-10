package com.cutety.service.impl;

import com.cutety.entity.Bill;
import com.cutety.entity.BillType;
import com.cutety.mapper.BillMapper;
import com.cutety.mapper.BillTypeMapper;
import com.cutety.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "billService")
public class BillServiceImpl implements BillService {
    private final BillMapper billMapper;
    private final BillTypeMapper billTypeMapper;

    public BillServiceImpl(BillMapper billMapper, BillTypeMapper billTypeMapper) {
        this.billMapper = billMapper;
        this.billTypeMapper = billTypeMapper;
    }

    @Override
    public int addBill(Bill bill) {
        return billMapper.insert(bill);
    }

    @Override
    public List<Bill> findBillByUserId(Integer userId) {
        return billMapper.selectByUserId(userId);
    }

    @Override
    public List<BillType> getBillType() {
        return billTypeMapper.getBillType();
    }

    @Override
    public List<Object> findBillByCategoryIdAndUserId(Map<String,Integer> params) {
        return billMapper.findBillByCategoryIdAndUserId(params);
    }
}
