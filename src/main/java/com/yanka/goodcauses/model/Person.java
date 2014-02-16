package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@MappedSuperclass
public class Person extends GenericEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(nullable = false)
    private String email;
    @Column(name = "cell_phone")
    private String cellPhone;

    public Person() {
    }

    public Person(String firstName, String lastName, String patronymic, Date birthday, String email, String cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return (StringUtils.isBlank(lastName) ? "" : lastName + " ")
               + (StringUtils.isBlank(firstName) ? "" : firstName + " ")
               + (StringUtils.isBlank(patronymic) ? "" : patronymic + " ");
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                   .add("lastName", lastName)
                   .add("firstName", firstName)
                   .add("patronymic", patronymic)
                   .add("email", email)
                   .toString() + super.toString();
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }

}
