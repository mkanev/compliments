package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;


/**
 * Initialize the database with some test entries.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInitializer {

    @Autowired
    private NewsEntryDAO newsEntryDao;
    @Autowired
    private UserDAO userDao;
    private PasswordEncoder passwordEncoder;

    protected DataBaseInitializer() {
    }

    public DataBaseInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase() {
        User userUser = new User("User", "User", "User", Calendar.getInstance().getTime(), "example@email.me", "+18005554935", "user", passwordEncoder.encode("user"));
        userUser.addRole("user");
        userDao.saveEntity(userUser);

        User adminUser = new User("Admin", "Admin", "Admin", Calendar.getInstance().getTime(), "example@email.me", "+18005554935", "admin", passwordEncoder.encode("admin"));
        adminUser.addRole("user");
        adminUser.addRole("admin");
        userDao.saveEntity(adminUser);

        for (int i = 0; i < 5; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setContent("This is example content " + i);
            newsEntryDao.saveEntity(newsEntry);
        }
    }

}
