package com.cha104g1.freshtown_springboot.member.rowmapper;

import com.cha104g1.freshtown_springboot.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {

        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerEmail(rs.getString("email"));
        customer.setCustomerPw(rs.getString("password"));
        customer.setCreateDate(rs.getTimestamp("created_date"));
        customer.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return customer;
    }
}
