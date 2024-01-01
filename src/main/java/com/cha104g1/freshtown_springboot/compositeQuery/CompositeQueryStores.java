/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.cha104g1.freshtown_springboot.compositeQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

//import hibernate.util.HibernateUtil;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面


public class CompositeQueryStores {

	public static Predicate getCompositeQueryStores(CriteriaBuilder builder, Root<StoresVO> root, String columnName, String value) {

		Predicate predicate = null;

		if ("storeName".equals(columnName) || "storeAddress".equals(columnName) || "ownerName".equals(columnName)
				|| "ownerId".equals(columnName)|| "ownerEmail".equals(columnName)) // 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");	
		else if ("storeState".equals(columnName) || "storeLv".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<StoresVO> getAllC(Map<String, String[]> map, Session session) {

		Transaction tx = session.beginTransaction();
		List<StoresVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<StoresVO> criteriaQuery = builder.createQuery(StoresVO.class);
			// 【●創建 Root】
			Root<StoresVO> root = criteriaQuery.from(StoresVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(getCompositeQueryStores(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("storeId")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
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
