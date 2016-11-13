package com.spring.training7.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.training7.entity.TblUser;

@Repository
public class UserRepositoryImpl  implements UserRepository{
	@PersistenceContext
	private EntityManager em;
	
	public List<TblUser> findAll() {
		String jql = "select o from TblUser o"; 
		TypedQuery<TblUser> query = em.createQuery(jql, TblUser.class);
		return query.getResultList();
	}
	
	public TblUser findById(int id) {
		return em.find(TblUser.class, id);
	}

	
	@Transactional
	public TblUser insert(TblUser user) {
		em.persist(user);
		return user;
	}
	
	@Transactional
	public TblUser update(TblUser user) {
		return em.merge(user);
	}
	
	@Transactional
	public void delete(int id) {
		TblUser user = findById(id);
		em.remove(user);
	}
}
