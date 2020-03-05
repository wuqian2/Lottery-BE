package com.sxnsyh.lottery.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "prize", schema = "lottry")
public class PrizeEntity {
    private int id;
    private String prizeName;
    private String prizeLevel;
    private Integer prizeCount;
    private Integer sentedPrize;

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
    @Column(name = "prize_name")
    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    @Basic
    @Column(name = "prize_level")
    public String getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    @Basic
    @Column(name = "prize_count")
    public Integer getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(Integer prizeCount) {
        this.prizeCount = prizeCount;
    }

    @Basic
    @Column(name = "sented_prize")
    public Integer getSentedPrize() {
        return sentedPrize;
    }

    public void setSentedPrize(Integer sentedPrize) {
        this.sentedPrize = sentedPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeEntity that = (PrizeEntity) o;
        return id == that.id &&
                Objects.equals(prizeName, that.prizeName) &&
                Objects.equals(prizeLevel, that.prizeLevel) &&
                Objects.equals(prizeCount, that.prizeCount) &&
                Objects.equals(sentedPrize, that.sentedPrize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prizeName, prizeLevel, prizeCount, sentedPrize);
    }
}
