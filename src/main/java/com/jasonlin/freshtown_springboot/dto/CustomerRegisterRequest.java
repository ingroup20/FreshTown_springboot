package com.jasonlin.freshtown_springboot.dto;

import jakarta.annotation.Nonnull;

public class CustomerRegisterRequest {
    @Nonnull
    private String customerEmail;
    @Nonnull
    private String customerPw;

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
