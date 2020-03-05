package com.sxnsyh.lottery.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "plan", schema = "lottry", catalog = "")
public class PlanEntity {
    private int id;
    private Date beginDate;
    private Date endDate;
    private Double baseProbability;

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
    @Column(name = "begin_date")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "base_probability")
    public Double getBaseProbability() {
        return baseProbability;
    }

    public void setBaseProbability(Double baseProbability) {
        this.baseProbability = baseProbability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEntity that = (PlanEntity) o;
        return id == that.id &&
                Objects.equals(beginDate, that.beginDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(baseProbability, that.baseProbability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginDate, endDate, baseProbability);
    }
}
