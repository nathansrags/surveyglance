package com.survey.glance.core.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.survey.glance.core.web.domain.User;
import com.survey.glance.core.web.predicate.UserPredicate;
import com.survey.glance.core.web.repository.UserRepository;

/**
 * @author Administrator
 *
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements IUserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(final Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findBySSO(final String ssoid) {
		System.out.println("User Dao :" + ssoid);
		User user = userRepository.findOne(UserPredicate.forSsoId(ssoid));
		return user;
	}

	@Override
	public User createUser(final User user) {
		return userRepository.save(user);

	}

	@Override
	public void deleteBySSO(String sso) {
		//userRepository.delete(arg0);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User find(final User user) {
		return userRepository.findOne(UserPredicate.forUser(user));
	}

}
