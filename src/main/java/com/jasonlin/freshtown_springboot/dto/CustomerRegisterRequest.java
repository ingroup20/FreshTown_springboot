package com.jasonlin.freshtown_springboot.dto;

import jakarta.annotation.Nonnull;
import org.springframework.lang.NonNull;

public class CustomerRegisterRequest {
    @NonNull
    private String customerEmail;
    @NonNull
    private String customerPw;
    @NonNull
    private String customerMob;
    private String mobChecked;
    private String customerNic;
    private String customerAddress;
    private String customerState;

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
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

    public String getMobChecked() {
        return mobChecked;
    }

    public void setMobChecked(String mobChecked) {
        this.mobChecked = mobChecked;
    }

    public String getCustomerMob() {
        return customerMob;
    }

    public void setCustomerMob(String customerMob) {
        this.customerMob = customerMob;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPw() {
        return customerPw;
    }

    public void setCustomerPw(String customerPw) {
        this.customerPw = customerPw;
    }
}
