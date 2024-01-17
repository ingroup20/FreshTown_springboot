package com.jasonlin.freshtown_springboot.dao.Impl;

import com.jasonlin.freshtown_springboot.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
