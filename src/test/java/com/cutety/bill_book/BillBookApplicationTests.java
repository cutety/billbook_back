package com.cutety.bill_book;

import com.cutety.entity.Bill;
import com.cutety.service.BillService;
import com.cutety.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BillBookApplicationTests {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private BillService billService;
    @Test
    void contextLoads() {
    }

    @Test
    void categoriesTest() {
        System.out.println(categoriesService.getCategories());
    }
    @Test
    void findBillByCategoryIdAndUserId() {
        Map<String,Integer> map = new HashMap<>();
        map.put("categoryId",1);
        map.put("userId",1);
        System.out.println(billService.findBillByCategoryIdAndUserId(map));
    }
    @Test
    void updateBillTest() {
        Bill bill  = new Bill();
        bill.setId(4);
        bill.setRemark("3瓶鸟虫脏");
        BigDecimal d = new BigDecimal(25);
        bill.setConsumptionAmount(d);
        int i = billService.updateBill(bill);
        System.out.println(i);
    }
}
