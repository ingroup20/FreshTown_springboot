package com.cha104g1.freshtown_springboot.suporder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cha104g1.freshtown_springboot.supplier.model.SupVO;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;

public class HibernateUtil_CompositeQuery_SupOrder {
	
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<SupOrderVO> root, String columnName, String value) {

		Predicate predicate = null;
		
		if ("id".equals(columnName)||"pushUp".equals(columnName)||"amount".equals(columnName)||"unitPrice".equals(columnName)) {
			predicate = builder.equal(root.get(columnName), Integer.parseInt(value));
		}
		else if ("supId".equals(columnName)||"marks".equals(columnName)) {
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		}
		else if ("purDate".equals(columnName)||"preDate".equals(columnName)||"deliDate".equals(columnName)) {
			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
		}
		else if ("oStatus".equals(columnName)) {
		    predicate = builder.equal(root.get(columnName), Integer.parseInt(value));
		}
		else if ("purNo".equals(columnName)) {
			MaterialVO materialVO = new MaterialVO();
			materialVO.setItemNumber(Integer.parseInt(value));
			predicate = builder.equal(root.get("materialVO"), materialVO);
	    }
		else if ("supId".equals(columnName)) {
			SupVO supVO = new SupVO();
			supVO.setSupId(Integer.parseInt(value));
			predicate = builder.equal(root.get("supVO"), supVO);
		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<SupOrderVO> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<SupOrderVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<SupOrderVO> criteriaQuery = builder.createQuery(SupOrderVO.class);
			// 【●創建 Root】
			Root<SupOrderVO> root = criteriaQuery.from(SupOrderVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("id")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
			System.out.println("Generated SQL Query: " + query.unwrap(org.hibernate.query.Query.class).getQueryString());
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}