package com.survey.glance.core.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.glance.core.web.dao.UserDao;
import com.survey.glance.core.web.domain.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(final Long id) {
		return dao.findById(id);
	}

	public User findBySso(final String sso) {
		return dao.findBySSO(sso);
	}

}
