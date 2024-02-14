package com.cha104g1.freshtown_springboot.storeemp.model;

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


public class HibernateUtil_CompositeQuery_StoreEmp {
	
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<StoreEmpVO> root, String columnName, String value) {
	    Predicate predicate = null;

	    // 檢查傳入的值是否為空，如果為空則返回null，可以根據實際業務需求調整這部分邏輯
	    if (value == null || value.trim().isEmpty()) {
	        return null;
	    }

	    if ("sEmpId".equals(columnName) || "pushUp".equals(columnName)) {
	        // 用於Integer類型的欄位
	        predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
	    } else if ("sEmpName".equals(columnName) || "sEmpDeptno".equals(columnName) || "sEmpTitle".equals(columnName)) {
	        // 用於String類型的欄位，進行模糊匹配
	        predicate = builder.like(root.get(columnName), "%" + value + "%");
	    } else if ("sEmpState".equals(columnName)) {
	        // 一個Integer類型的條件
	        predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
	    }
	    // 加入更多的else if塊來處理其他類型的欄位

	    return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<StoreEmpVO> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<StoreEmpVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<StoreEmpVO> criteriaQuery = builder.createQuery(StoreEmpVO.class);
			// 【●創建 Root】
			Root<StoreEmpVO> root = criteriaQuery.from(StoreEmpVO.class);

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
			criteriaQuery.orderBy(builder.asc(root.get("sEmpId")));
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
