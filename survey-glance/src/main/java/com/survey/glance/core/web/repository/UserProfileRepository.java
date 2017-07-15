package com.survey.glance.core.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.survey.glance.core.web.domain.UserProfile;

/**
 * @author Administrator
 *
 */
public interface UserProfileRepository extends
		JpaRepository<UserProfile, Long>,
		QueryDslPredicateExecutor<UserProfile> {

}
