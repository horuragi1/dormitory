package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventRepository {
	
	private final EntityManager em;
	
	@Transactional
	public Long save(Event event) { // 이벤트를 저장하는 함수
		
		Long id = event.getId();
		
		if(id == null) {
			em.persist(event);
		}
		
		return id;
		
	}
	
	public List<Event> findEventsByUserId(Long userId){
		
		String jpql = "SELECT e FROM Event e where e.user.id = :userId";
		
		TypedQuery<Event> query = em.createQuery(jpql, Event.class);
		query.setParameter("userId", userId);
		
		return query.getResultList();
		
	}
	
	public Page<Event> findEventsByUserId(Long userId, Pageable pageable) {
	    String jpql = "SELECT e FROM Event e WHERE e.user.id = :userId";
	    TypedQuery<Event> query = em.createQuery(jpql, Event.class);
	    query.setParameter("userId", userId);
	    
	    // 전체 데이터 수 계산을 위한 카운트 쿼리
	    String countJpql = "SELECT COUNT(e) FROM Event e WHERE e.user.id = :userId";
	    TypedQuery<Long> countQuery = em.createQuery(countJpql, Long.class);
	    countQuery.setParameter("userId", userId);
	    Long totalCount = countQuery.getSingleResult();

	    // 페이징 적용
	    query.setFirstResult((int) pageable.getOffset());
	    query.setMaxResults(pageable.getPageSize());

	    // 결과 반환
	    List<Event> events = query.getResultList();
	    return new PageImpl<>(events, pageable, totalCount);
	}
	
	@Transactional
	public Long deleteEventsByEventId(Long eventId) {
		Event event = em.find(Event.class, eventId); // 엔티티 조회
	    if (event != null) {
	        em.remove(event); // 엔티티 삭제
	    }
		 
		 
		 System.out.println("delete query executed!!!");
	    
	    return eventId;
	}



}
