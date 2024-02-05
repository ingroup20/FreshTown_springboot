/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.cha104g1.freshtown_springboot.picking.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
//import hibernate.util.HibernateUtil;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


public class HibernateUtil_CompositeQuery_Picking {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<PickingVO> root, String columnName, String value) {

		Predicate predicate = null;

		if ("pickingNo".equals(columnName)|| "pickingQuantity".equals(columnName)
				|| "pickingStatus".equals(columnName) || "pickingClass".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		else if ("pickingUnit".equals(columnName) || "marks".equals(columnName))// 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		else if ("pickingDate".equals(columnName)) // 用於date
			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
		else if ("itemNumber".equals(columnName)) {
			MaterialVO materialVO = new MaterialVO();
			materialVO.setItemNumber(Integer.valueOf(value));
			predicate = builder.equal(root.get("materialVO"), materialVO);
//			Integer itemNumber = Integer.valueOf(value);
//		    predicate = builder.equal(root.get("materialVO").get("itemNumber"), itemNumber);
		}else if("storeId".equals(columnName)){
			StoresVO storesVO= new StoresVO();
			storesVO.setStoreId(Integer.valueOf(value));
			predicate = builder.equal(root.get("storesVO"), storesVO);
		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<PickingVO> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<PickingVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<PickingVO> criteriaQuery = builder.createQuery(PickingVO.class);
			// 【●創建 Root】
			Root<PickingVO> root = criteriaQuery.from(PickingVO.class);

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
			criteriaQuery.orderBy(builder.asc(root.get("pickingNo")));
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
