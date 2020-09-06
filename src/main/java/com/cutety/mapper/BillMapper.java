package com.cutety.mapper;

import com.cutety.entity.Bill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> selectByUserId(Integer userId);
}