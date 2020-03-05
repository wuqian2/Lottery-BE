package com.sxnsyh.lottery.domain;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class PlanDomain {
    /**
     * id
     */
    private int id;
    /**
     * 起始日期
     */
    private Date beginDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 基础概率
     */
    private Double baseProbability;

    private List<PlanInfoDomain> planInfoList;
}
