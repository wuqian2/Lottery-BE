package com.sxnsyh.lottery.domain;

import lombok.Data;

@Data
public class CustomerDomain {
    private int id;
    private String phoneNo;
    private String customerNo;
    private String customerName;
    private String certNo;
    private Integer transactionCount;
    private Double transactionAmount;
    private Integer remainingCount;
    private String flag;

}
