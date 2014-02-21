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

        for (int i = 0; i < 15; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setTitle("This is example content " + i);
            newsEntry.setContent(
                "Phasellus euismod fermentum urna non vehicula. Ut massa nulla, tincidunt sed accumsan nec, imperdiet non tellus. Praesent non elit vehicula, malesuada eros ut, "
                + "pulvinar urna. Morbi condimentum volutpat elit et tincidunt. In cursus purus sed dapibus condimentum. Vestibulum consequat velit non arcu tincidunt, dignissim "
                + "pharetra eros sodales. Nulla sodales molestie congue. Pellentesque luctus elit eget arcu bibendum, a fringilla diam tempor. Fusce id interdum orci, sed ultrices "
                + "mauris. Morbi ultricies enim ac enim fermentum pretium. Quisque tristique nisl tortor, vel dignissim augue dictum vel.");
            newsEntryDao.saveEntity(newsEntry);
        }
    }

}
