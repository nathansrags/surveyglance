package com.survey.glance.core.web.service;

import java.util.List;

import com.survey.glance.core.web.domain.UserProfile;

/**
 * @author Administrator
 *
 */
public interface IUserProfileService {
	
	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
}
