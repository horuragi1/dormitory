package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	private final EntityManager em;
	
	public Optional<User> findByUsername(String username) {
		
		String jpql = "SELECT u FROM User u WHERE u.username =:username";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("username", username);
		
		return query.getResultList().stream().findFirst();
		
	}
	
	public Long save(User user) {
		
		if(user.getId() == null) {
			em.persist(user);
		}
		
		return user.getId();
	}

}
