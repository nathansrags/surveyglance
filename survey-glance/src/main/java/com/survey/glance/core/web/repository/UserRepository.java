package com.survey.glance.core.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.survey.glance.core.web.domain.User;

public interface UserRepository extends JpaRepository<User, Long>,
		QueryDslPredicateExecutor<User> {

}
