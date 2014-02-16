package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.User;
import com.yanka.goodcauses.repository.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class UserManagerImpl extends GenericEntityManagerImpl<User> implements UserManager {

    private UserDAO userDao;

    @Autowired
    public UserManagerImpl(UserDAO userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
