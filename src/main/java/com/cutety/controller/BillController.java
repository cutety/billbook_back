package com.cutety.controller;

import com.cutety.entity.Bill;
import com.cutety.entity.BillType;
import com.cutety.entity.Categories;
import com.cutety.entity.Response;
import com.cutety.mapper.BillMapper;
import com.cutety.service.BillService;
import com.cutety.service.CategoriesService;
import com.cutety.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

@Controller
@RequestMapping(value = "/bill")
public class BillController {
    public static Logger log = LoggerFactory.getLogger(BillController.class);
    private final BillService billService;
    private final RedisUtil redisUtil;
    @Autowired
    private CategoriesService categoriesService;

    public BillController(BillService billService, RedisUtil redisUtil) {
        this.billService = billService;
        this.redisUtil = redisUtil;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Response addBill(@RequestBody Bill bill) {
        Response response = new Response();
        LocalDateTime now = LocalDateTime.now();
        bill.setBillDate(now);
        if(billService.addBill(bill) == 1) {
            response.setMsg("添加成功");
            response.setStatus("200");
        } else {
            response.setMsg("添加失败");
            response.setStatus("400");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/findBillByUserId")
    public List<Bill> findBillById(Integer userId) {
        return billService.findBillByUserId(userId);
    }

    @GetMapping("/billType")
    @ResponseBody
    public List<BillType> getBillType() {
        return billService.getBillType();
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<Categories> getCategories() {
        String categories = redisUtil.get("categories");
        if(categories == null) {
            //从数据库里查
            List<Categories> categories_list = categoriesService.getCategories();
            categories = JSON.toJSONString(categories_list);
            //放到redis
            redisUtil.set("categories",categories);
            return categories_list;
        } else {
            return JSON.parseArray(categories, Categories.class);
        }
    }

    @PostMapping("/charges")
    @ResponseBody
    public List<Object> getCharges(@RequestParam Map<String,Integer> params) {
        return billService.findBillByCategoryIdAndUserId(params);
    }

    @PostMapping("/updateBill")
    @ResponseBody
    public Response updateBill(@RequestBody Bill bill) {
        Response response = new Response();
        System.out.println(bill);
        int res = billService.updateBill(bill);

        if(res == 1) {
            response.setMsg("success");
            response.setStatus("200");
            return response;
        } else {
            response.setMsg("fail...");
            response.setStatus("400");
            return response;
        }
    }

}
