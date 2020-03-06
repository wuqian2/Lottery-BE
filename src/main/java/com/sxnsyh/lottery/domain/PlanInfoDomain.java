package com.sxnsyh.lottery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor

public class PlanInfoDomain {
    private int id;

    /**
     * 礼物编号
     */
    private int prizeId;

    /**
     * 礼物名
     */
    private String prizeName;
    /**
     * 礼品等级
     */
    private String prizeLevel;

    /**
     * 计划数量
     */
    private Integer planCount;

    /**
     * 以送出数量
     */
    private Integer sendedCount;
    /**
     * 交易笔数限制
     */
    private Integer quantityLimit;
    /**
     * 交易金额限制
     */
    private Integer amountLimit;
}
