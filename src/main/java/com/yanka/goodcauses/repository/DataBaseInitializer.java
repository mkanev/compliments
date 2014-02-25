package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Media;
import com.yanka.goodcauses.model.MediaDataType;
import com.yanka.goodcauses.model.MediaType;
import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.model.OrganizationType;
import com.yanka.goodcauses.model.User;
import com.yanka.goodcauses.service.NewsEntryManager;
import com.yanka.goodcauses.service.OrganizationManager;
import com.yanka.goodcauses.service.UserManager;

import org.apache.commons.lang3.StringUtils;
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
    private NewsEntryManager newsEntryManager;
    @Autowired
    private OrganizationManager organizationManager;
    @Autowired
    private UserManager userManager;
    private PasswordEncoder passwordEncoder;
    private final String[] colors = new String[]{"1abc9c", "3498db", "9b59b6", "e67e22", "f1c40f", "2ecc71"};

    protected DataBaseInitializer() {
    }

    public DataBaseInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase() {
        User userUser = new User("User", "User", "User", Calendar.getInstance().getTime(), "example@email.me", "+18005554935", "user", passwordEncoder.encode("user"));
        userUser.addRole("user");
        userManager.save(userUser);

        User adminUser = new User("Admin", "Admin", "Admin", Calendar.getInstance().getTime(), "example@email.me", "+18005554935", "admin", passwordEncoder.encode("admin"));
        adminUser.addRole("user");
        adminUser.addRole("admin");
        userManager.save(adminUser);

        for (int i = 0; i < 125; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setName("This is example content " + i);
            newsEntry.setDeleted(i % 5 == 0);
            newsEntry.setContent(
                "Phasellus euismod fermentum urna non vehicula. Ut massa nulla, tincidunt sed accumsan nec, imperdiet non tellus. Praesent non elit vehicula, malesuada eros ut, "
                + "pulvinar urna. Morbi condimentum volutpat elit et tincidunt. In cursus purus sed dapibus condimentum. Vestibulum consequat velit non arcu tincidunt, dignissim "
                + "pharetra eros sodales. Nulla sodales molestie congue. Pellentesque luctus elit eget arcu bibendum, a fringilla diam tempor. Fusce id interdum orci, sed ultrices "
                + "mauris. Morbi ultricies enim ac enim fermentum pretium. Quisque tristique nisl tortor, vel dignissim augue dictum vel.");
            for (int j = 0; j < i % 3 + 1; j++) {
                newsEntry.addMedia(buildSampleMedia(j, 1500, 900, null));
            }
            newsEntryManager.save(newsEntry);
        }

        for (int i = 0; i < 15; i++) {
            Organization org = new Organization();
            org.setName("Sample organization " + i);
            org.setDeleted(i % 5 == 0);
            org.setDescription(
                "Phasellus euismod fermentum urna non vehicula. Ut massa nulla, tincidunt sed accumsan nec, imperdiet non tellus. Praesent non elit vehicula, malesuada eros ut, "
                + "pulvinar urna. Morbi condimentum volutpat elit et tincidunt. In cursus purus sed dapibus condimentum. Vestibulum consequat velit non arcu tincidunt, dignissim ");
            org.setEmail("org" + i + "@gmail.com");
            org.setOrganizationType(i % 2 == 0 ? OrganizationType.FUND : OrganizationType.PARTNER);
            org.setLogo(buildSampleMedia(i, 180, 90, "Some logo!"));
            org.addMedia(buildSampleMedia(i, 1800, 900, null));
            organizationManager.save(org);
        }
    }

    private Media buildSampleMedia(int idx, int width, int height, String text) {
        Media media = new Media();
        media.setName("Sample image " + idx);
        media.setMediaType(MediaType.LINKED);
        media.setMediaDataType(MediaDataType.IMAGE);
        String textSuffix = StringUtils.isBlank(text) ? "" : "&text=" + text;
        textSuffix = StringUtils.replace(textSuffix, " ", "+");
        media.setUrl("http://placehold.it/" + width + "x" + height + "/" + colors[idx % colors.length] + textSuffix);
        return media;
    }

}
