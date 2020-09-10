package com.cutety.mapper;

import com.cutety.entity.BillType;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BillTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BillType record);

    int insertSelective(BillType record);

    BillType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BillType record);

    int updateByPrimaryKey(BillType record);
    List<BillType> getBillType();
}