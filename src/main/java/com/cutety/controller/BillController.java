package com.cutety.controller;

import com.cutety.entity.Bill;
import com.cutety.mapper.BillMapper;
import com.cutety.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @ResponseBody
    @RequestMapping("/add")
    public int addBill(Bill bill) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        bill.setBillDate(now);
        return billService.addBill(bill);
    }

    @ResponseBody
    @RequestMapping("/findBillByUserId")
    public List<Bill> findBillById(Integer userId) {
        return billService.findBillByUserId(userId);
    }
}
