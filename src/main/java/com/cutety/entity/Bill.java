package com.cutety.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer id;

    private LocalDateTime billDate;

    private Integer typeId;

    private String remark;

    private BigDecimal consumptionAmount;

    private Integer userId;

    private Integer categoryId;

    private BillType billType;
}