package com.sxnsyh.lottery.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "plan_info", schema = "lottry", catalog = "")
public class PlanInfoEntity {
    private int id;
    private int planId;
    private Integer planCount;
    private Integer sendedCount;
    private Integer quantityLimit;
    private Integer amountLimit;
    private Integer prizeId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "plan_id")
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "plan_count")
    public Integer getPlanCount() {
        return planCount;
    }

    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    @Basic
    @Column(name = "sended_count")
    public Integer getSendedCount() {
        return sendedCount;
    }

    public void setSendedCount(Integer sendedCount) {
        this.sendedCount = sendedCount;
    }

    @Basic
    @Column(name = "quantity_limit")
    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    @Basic
    @Column(name = "amount_limit")
    public Integer getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(Integer amountLimit) {
        this.amountLimit = amountLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanInfoEntity that = (PlanInfoEntity) o;
        return id == that.id &&
                planId == that.planId &&
                Objects.equals(planCount, that.planCount) &&
                Objects.equals(sendedCount, that.sendedCount) &&
                Objects.equals(quantityLimit, that.quantityLimit) &&
                Objects.equals(amountLimit, that.amountLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planId, planCount, sendedCount, quantityLimit, amountLimit);
    }

    @Basic
    @Column(name = "prize_id")
    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }
}
