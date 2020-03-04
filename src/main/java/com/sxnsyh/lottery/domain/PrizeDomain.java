package com.sxnsyh.lottery.domain;

import lombok.Data;

@Data
public class PrizeDomain {
    private int id;
    private String prizeName;
    private String prizeLevel;
    private Integer prizeCount;
    private Integer sentedPrize;
}
