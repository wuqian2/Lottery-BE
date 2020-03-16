package com.sxnsyh.lottery.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "lottry", catalog = "")
public class CustomerEntity {
    private int id;
    @ExcelProperty(index = 0)
    private String phoneNo;
    @ExcelProperty(index = 1)
    private String customerNo;
    @ExcelProperty(index = 2)
    private String customerName;
    @ExcelProperty(index = 3)
    private String certNo;
    @ExcelProperty(index = 4)
    private Integer transactionCount;
    @ExcelProperty(index = 5)
    private Double transactionAmount;
    private Integer remainingCount;
    private String flag;
    private Double probability;
    private String staffFlag;

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
    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Column(name = "customer_no")
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
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
    @Column(name = "cert_no")
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    @Basic
    @Column(name = "transaction_count")
    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    @Basic
    @Column(name = "transaction_amount")
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Basic
    @Column(name = "remaining_count")
    public Integer getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(Integer remainingCount) {
        this.remainingCount = remainingCount;
    }

    @Basic
    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return id == that.id &&
                Objects.equals(phoneNo, that.phoneNo) &&
                Objects.equals(customerNo, that.customerNo) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(certNo, that.certNo) &&
                Objects.equals(transactionCount, that.transactionCount) &&
                Objects.equals(transactionAmount, that.transactionAmount) &&
                Objects.equals(remainingCount, that.remainingCount) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNo, customerNo, customerName, certNo, transactionCount, transactionAmount, remainingCount, flag);
    }

    @Basic
    @Column(name = "probability")
    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Basic
    @Column(name = "staff_flag")
    public String getStaffFlag() {
        return staffFlag;
    }

    public void setStaffFlag(String staffFlag) {
        this.staffFlag = staffFlag;
    }
}
