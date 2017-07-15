package com.survey.glance.core.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.glance.core.web.dao.IUserProfileDao;
import com.survey.glance.core.web.domain.UserProfile;

@Service("userProfileServiceImpl")
@Transactional
public class UserProfileServiceImpl implements IUserProfileService{
     
    @Autowired
    IUserProfileDao dao;
     
    public UserProfile findById(int id) {
        return dao.findById(id);
    }
 
    public UserProfile findByType(String type){
        return dao.findByType(type);
    }
 
    public List<UserProfile> findAll() {
        return dao.findAll();
    }
}