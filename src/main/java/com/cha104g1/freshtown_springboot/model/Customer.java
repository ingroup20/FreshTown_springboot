package com.cha104g1.freshtown_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oracle.sql.CHAR;

import java.util.Date;

public class Customer {
    private Integer customerId;

    //@JsonIgnore註解表示回傳給前端的值會被隱藏 保護消費者密碼
    @JsonIgnore
    private  String customerPw;
    private CHAR customerMob;
    private CHAR mobChecked;
    private String customerEmail;
    private String customerNic;
    private String customerAddress;
    private String customerState;
    private Date createDate;
    private Date lastModifiedDate;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPw() {
        return customerPw;
    }

    public void setCustomerPw(String customerPw) {
        this.customerPw = customerPw;
    }

    public CHAR getCustomerMob() {
        return customerMob;
    }

    public void setCustomerMob(CHAR customerMob) {
        this.customerMob = customerMob;
    }

    public CHAR getMobChecked() {
        return mobChecked;
    }

    public void setMobChecked(CHAR mobChecked) {
        this.mobChecked = mobChecked;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
