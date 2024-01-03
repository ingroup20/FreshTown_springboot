package com.cha104g1.freshtown_springboot.member.dto.dto;

import javax.validation.constraints.NotBlank;

public class CustomerRegisterRequest {

    @NotBlank
    private String customerEmail;
    @NotBlank
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
