package com.survey.glance.core.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.survey.glance.core.web.domain.User;
import com.survey.glance.core.web.repository.UserRepository;

/**
 * @author Administrator
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	public User findById(final Long id) {
		return userRepository.findOne(id);
	}

	public User findBySSO(final String sso) {
		System.out.println("User Dao :" + sso);
		List<User> users = userRepository.findAll();
		System.out.println(users.get(0).getSsoId());
		User user = userRepository.findAll().get(0);
		return user;
	}

}
