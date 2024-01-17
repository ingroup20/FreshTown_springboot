package com.jasonlin.freshtown_springboot.model;

import java.util.Date;

public class Customer {
    private Integer customerId;
    private String customerPw;
    private Integer customerMob;
    private Integer mobChecked;
    private String customerEmail;
    private String customerNic;
    private String customerAddress;
    private String customerState;
    private Date createdDate;
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

    public Integer getCustomerMob() {
        return customerMob;
    }

    public void setCustomerMob(Integer customerMob) {
        this.customerMob = customerMob;
    }

    public Integer getMobChecked() {
        return mobChecked;
    }

    public void setMobChecked(Integer mobChecked) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
