package com.survey.glance.core.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.survey.glance.core.web.domain.UserProfile;
import com.survey.glance.core.web.predicate.UserProfilePredicate;
import com.survey.glance.core.web.repository.UserProfileRepository;

@Component("userProfileDao")
public class UserProfileDaoImpl implements IUserProfileDao {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public List<UserProfile> findAll() {
		return userProfileRepository.findAll();
	}

	@Override
	public UserProfile findByType(String type) {
		return userProfileRepository.findOne(UserProfilePredicate.forUserProfileType(type));
	}

	@Override
	public UserProfile findById(int id) {
		return userProfileRepository.findOne(UserProfilePredicate.forUserProfileId(id));
	}

}
