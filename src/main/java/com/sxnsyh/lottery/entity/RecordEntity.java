package com.sxnsyh.lottery.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "record", schema = "lottry", catalog = "")
public class RecordEntity {
    private int id;
    private int customerId;
    private String customerName;
    private String phone;
    private int prizeId;
    private String prizeName;
    private Date winningDate;
    private String certNo;

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
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "prize_id")
    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
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
    @Column(name = "winning_date")
    public Date getWinningDate() {
        return winningDate;
    }

    public void setWinningDate(Date winningDate) {
        this.winningDate = winningDate;
    }

    @Basic
    @Column(name = "cert_no")
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordEntity that = (RecordEntity) o;
        return id == that.id &&
                customerId == that.customerId &&
                prizeId == that.prizeId &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(prizeName, that.prizeName) &&
                Objects.equals(winningDate, that.winningDate) &&
                Objects.equals(certNo, that.certNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, customerName, phone, prizeId, prizeName, winningDate, certNo);
    }
}
