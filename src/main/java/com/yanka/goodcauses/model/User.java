package com.yanka.goodcauses.model;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;


@Entity
public class User extends Person implements UserDetails {

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    @Pattern(regexp = "[a-zA-Z0-9._-]+")
    private String username;
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled = true;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, Date birthday, String email, String cellPhone, String username, String password) {
        super(firstName, lastName,  birthday, email, cellPhone);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    @JsonView(JsonViews.Admin.class)
    public String getPatronymic() {
        return super.getPatronymic();
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public String getCellPhone() {
        return super.getCellPhone();
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public Date getBirthday() {
        return super.getBirthday();
    }

    @Override
    @JsonView(JsonViews.Preview.class)
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = this.getRoles();
        if (roles == null) {
            return Collections.emptyList();
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public boolean isAccountNonExpired() {
        return !expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public boolean isAccountNonLocked() {
        return !locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @Override
    @JsonView(JsonViews.Extended.class)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
