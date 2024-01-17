package com.jasonlin.freshtown_springboot.rowmapper;

import com.jasonlin.freshtown_springboot.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customerId"));
        customer.setCustomerPw(rs.getString("customerPw"));
        customer.setCustomerMob(rs.getString("customerMob"));
        customer.setMobChecked(rs.getInt("mobChecked"));
        customer.setCustomerEmail(rs.getString("customerEmail"));
        customer.setCustomerNic(rs.getString("customerNic"));
        customer.setCustomerAddress(rs.getString("customerAddress"));
        customer.setCustomerState(rs.getString("customerState"));

        return customer;
    }
}
