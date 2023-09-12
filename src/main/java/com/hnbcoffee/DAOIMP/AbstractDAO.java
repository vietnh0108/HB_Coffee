//package com.hnbcoffee.DAOIMP;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.StoredProcedureQuery;
//import javax.persistence.TypedQuery;
//
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//
//import com.hnbcoffee.Utils.*;
//
//public class AbstractDAO<T> {
//	public static final EntityManagerFactoryBuilder entityManager = JpaUtils.getEntityManager();
//	@SuppressWarnings("deprecation")
//	@Override
//	protected void finalize()throws Throwable{
//		entityManager.close();
//		super.finalize();
//	}
//	
//	public T findById(Class<T> clazz, Integer id) {
//		return entityManager.find(clazz, id);
//	}
//	
//	public List<T> findAll(Class<T> clazz,boolean existIsActive){
//		String entityName = clazz.getSimpleName();
//		StringBuilder sql = new StringBuilder();
//		sql.append("Select o FROM ").append(entityName).append(" o");
//		if(existIsActive = true) {
//			sql.append(" WHERE Active = 1");
//		}
//		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
//		
//		return query.getResultList();
//	}
//	
//	public List<T> findAll(Class<T> clazz,boolean existIsActive,int pageNumber,int pageSize){
//		String entityName = clazz.getSimpleName();
//		StringBuilder sql = new StringBuilder();
//		sql.append("Select o FROM ").append(entityName).append(" o");
//		if(existIsActive = true) {
//			sql.append(" WHERE Active = 1");
//		}
//		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
//		query.setFirstResult((pageNumber-1)*pageSize);
//		query.setMaxResults(pageSize);
//		return query.getResultList();
//	}
//	
//	public T findOne(Class<T> clazz, String sql, Object... params) {
//		TypedQuery<T> query = entityManager.createQuery(sql,clazz);
//		for(int i = 0; i < params.length;i++) {
//			query.setParameter(i, params[i]);
//		}
//		List<T> rs = query.getResultList();
//		if(rs.isEmpty()) {
//			return null;			
//		}
//		return rs.get(0);
//	}
//	
//	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
//		TypedQuery<T> query = entityManager.createQuery(sql,clazz);
//		for(int i = 0; i < params.length;i++) {
//			query.setParameter(i, params[i]);
//		}
//		return query.getResultList();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
//		Query query = entityManager.createNativeQuery(sql);
//		for(int i = 0; i < params.length;i++) {
//			query.setParameter(i, params[i]);
//		}
//		return query.getResultList();
//	}
//	
//	public List<Object[]> findManyByNativeQueryNoParams(Class<T> clazz, String sql) {
//		Query query = entityManager.createNativeQuery(sql,clazz);
//
//		return query.getResultList();
//	}
//	
//	public T create(T entity) {
//		try {
//			entityManager.getTransaction().begin();
//			entityManager.persist(entity);
//			entityManager.getTransaction().commit();
//			System.out.println("Create success");
//			return entity;
//		} catch (Exception e) {
//			entityManager.getTransaction().rollback();
//			System.out.println("Canot insert entity " + entity.getClass().getSimpleName() + "to DataBase" + e);
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public T update(T entity) {
//		try {
//			entityManager.getTransaction().begin();
//			entityManager.merge(entity);
//			entityManager.getTransaction().commit();
//			System.out.println("Create success");
//			return entity;
//		} catch (Exception e) {
//			entityManager.getTransaction().rollback();
//			System.out.println("Canot update entity " + entity.getClass().getSimpleName() + "to DataBase" + e);
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public T delete(T entity) {
//		try {
//			entityManager.getTransaction().begin();
//			entityManager.remove(entity);
//			entityManager.getTransaction().commit();
//			System.out.println("Create success");
//			return entity;
//		} catch (Exception e) {
//			entityManager.getTransaction().rollback();
//			System.out.println("Canot DELETE entity " + entity.getClass().getSimpleName() + "to DataBase" + e);
//			throw new RuntimeException(e);
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<T> callStored(String nameStored, Map< String , Object > params){
//		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(nameStored);
//		params.forEach((key,value) -> query.setParameter(key, value));
//		
//		return (List<T>) query.getResultList();
//	}
//	
//}
