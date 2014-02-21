package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface UserManager extends PersonManager<User>, UserDetailsService {

}
