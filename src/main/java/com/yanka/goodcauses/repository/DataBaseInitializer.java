package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Compliment;
import com.yanka.goodcauses.model.User;
import com.yanka.goodcauses.service.ComplimentManager;
import com.yanka.goodcauses.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;


/**
 * Initialize the database with some test entries.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInitializer {

    @Autowired
    private UserManager userManager;
    @Autowired
    private ComplimentManager complimentManager;
    private PasswordEncoder passwordEncoder;

    protected DataBaseInitializer() {
    }

    public DataBaseInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase() {
        User adminUser = new User("Executive", "Chief", Calendar.getInstance().getTime(), "example@email.me", "+18005554935", "admin", passwordEncoder.encode("admin"));
        adminUser.addRole("user");
        adminUser.addRole("admin");
        adminUser = userManager.save(adminUser);
        initBlogRecords(adminUser);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void initBlogRecords(User adminUser) {
        for (int i = 0; i < 25; i++) {
            Compliment newsEntry = new Compliment();
            newsEntry.setContent("Phasellus euismod fermentum urna non vehicula " + i);
            complimentManager.save(newsEntry);
        }
    }

}
