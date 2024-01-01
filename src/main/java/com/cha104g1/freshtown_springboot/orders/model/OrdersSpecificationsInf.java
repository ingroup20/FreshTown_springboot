package com.cha104g1.freshtown_springboot.orders.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public interface OrdersSpecificationsInf {
		
		static Specification<OrdersVO> yourOrdersVOQuery(final String key, final String value) {
			
			
			Specification<OrdersVO> specification = new Specification<OrdersVO>() {
				
			    @Override
			    public Predicate toPredicate(Root<OrdersVO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			        // 根据 key 和 value 构建不同的查询条件
			        // 返回 Predicate
			        return criteriaBuilder.equal(root.get(key), value);
			    }
			};
			return specification;
			
		}
		// 可以定義更多的動態查詢方法
}
