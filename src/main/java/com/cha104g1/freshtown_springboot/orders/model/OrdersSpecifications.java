package com.cha104g1.freshtown_springboot.orders.model;

import org.springframework.data.jpa.domain.Specification;

public class OrdersSpecifications {

    public static Specification<OrdersVO> getPredicateForAnyDB(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if ("empno".equals(columnName)) // 用于Integer
                return criteriaBuilder.equal(root.get(columnName), Integer.valueOf(value));
            else if ("sal".equals(columnName) || "comm".equals(columnName)) // 用于Double
                return criteriaBuilder.equal(root.get(columnName), Double.valueOf(value));
            else if ("ename".equals(columnName) || "job".equals(columnName)) // 用于varchar
                return criteriaBuilder.like(root.get(columnName), "%" + value + "%");
            else if ("hiredate".equals(columnName)) // 用于date
                return criteriaBuilder.equal(root.get(columnName), java.sql.Date.valueOf(value));
     
            return null;
        };
    }
}