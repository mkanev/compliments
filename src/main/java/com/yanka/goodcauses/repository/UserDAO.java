package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public interface UserDAO extends PersonDAO<User> {

    User findByUsername(String username);

}
