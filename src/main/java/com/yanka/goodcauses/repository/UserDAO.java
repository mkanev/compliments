package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public interface UserDAO extends GenericEntityDAO<User, Long> {

    User findByUsername(String username);

}
