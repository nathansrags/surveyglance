package com.survey.glance.core.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.glance.core.web.dao.IUserDao;
import com.survey.glance.core.web.domain.User;

/**
 * @author Administrator
 *
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("userDaoImpl")
	private IUserDao dao;

	@Override
	public User findById(final Long id) {
		return dao.findById(id);
	}

	@Override
	public User findBySso(final String ssoId) {
		return dao.findBySSO(ssoId);
	}

	@Override
	public User createUser (final User user){
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		return dao.createUser(user);

	}
 
    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateUser(final User user) {
        /*User entity = dao.findById((Long.parseLong(user.getId());
        if(entity!=null){
            entity.setSsoId(user.getSsoId());
            if(!user.getPassword().equals(entity.getPassword())){
                //entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
        }*/
    }
 
     
    public void deleteUserBySSO(final String sso) {
        dao.deleteBySSO(sso);
    }
 
    public boolean isUserSSOUnique(final Integer id,final String sso) {
        User user = findBySso(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public User find(User user) {
		return dao.find(user);
	}

}
