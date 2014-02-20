package net.dontdrinkandroot.example.angularrestspringsecurity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/context.xml"})
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testDefaultPasswords() {

        System.out.println(this.passwordEncoder.encode("user"));
        System.out.println(this.passwordEncoder.encode("admin"));

        Assert.assertEquals(
            "452a9d86930c44a856332578395a1cd7429ab3aa5a70fd35fb4b96cf58bdf865db11df56685629b1e09f2ec6e812a9916b2b5fadbe68c16031260f116c946eae",
            this.passwordEncoder.encode("user"));
        Assert.assertEquals(
            "f5cbc3b5d2f2faf4015014e6df64f638c95cf1f0c948013d173bbe98ad41e9f9b7a8268f32f76c642327e89b1f523cb61a99aa4d3e353d78fd059338a7f86aae",
            this.passwordEncoder.encode("admin"));

        Assert.assertTrue(this.passwordEncoder.matches(
            "user",
            "452a9d86930c44a856332578395a1cd7429ab3aa5a70fd35fb4b96cf58bdf865db11df56685629b1e09f2ec6e812a9916b2b5fadbe68c16031260f116c946eae"));
        Assert.assertTrue(this.passwordEncoder.matches(
            "admin",
            "f5cbc3b5d2f2faf4015014e6df64f638c95cf1f0c948013d173bbe98ad41e9f9b7a8268f32f76c642327e89b1f523cb61a99aa4d3e353d78fd059338a7f86aae"));
    }

}
