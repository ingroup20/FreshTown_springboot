package com.cha104g1.freshtown_springboot.orders.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.Predicate;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository OrdersRepository;

    public List<OrdersVO> getAllC(Map<String, String[]> map) {
        List<OrdersVO> list;
        try {
            List<Specification<OrdersVO>> specifications = new ArrayList<>();
            Set<String> keys = map.keySet();
            int count = 0;
            for (String key : keys) {
                String value = map.get(key)[0];
                if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                    count++;
                    specifications.add(OrdersSpecifications.getPredicateForAnyDB(key, value.trim()));
                    System.out.println("有送出查詢資料的欄位數count = " + count);
                }
            }
            System.out.println("specifications.size()=" + specifications.size());

            // 构建动态查询条件
            Specification<OrdersVO> finalSpecification = (root, query, criteriaBuilder) -> {
                Predicate[] predicates = specifications.stream()
                        .map(specification -> specification.toPredicate(root, query, criteriaBuilder))
                        .toArray(Predicate[]::new);
                return criteriaBuilder.and(predicates);
            };

            // 使用 Spring Data JPA 的接口方法进行查询
            list = OrdersRepository.findAll(finalSpecification);
        } catch (RuntimeException ex) {
            throw ex;
        }
        return list;
    }
}