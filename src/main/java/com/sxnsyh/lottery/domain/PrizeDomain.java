package com.sxnsyh.lottery.domain;

import lombok.Data;

import java.util.List;

@Data
public class PrizeDomain {
    /**
     * id
     */
    private int id;
    /**
     * 礼品名字
     */
    private String prizeName;
    /**
     * 礼品等级
     */
    private String prizeLevel;
    /**
     * 礼品数量
     */
    private Integer prizeCount;
    /**
     * 以送出数量
     */
    private Integer sentedPrize;

}
