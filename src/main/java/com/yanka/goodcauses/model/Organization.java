package com.yanka.goodcauses.model;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
public class Organization extends ContainingMediaEntity {

    public static final String FIELD_ORGANIZATION_TYPE = "organizationType";
    @Lob
    @Column(length = Integer.MAX_VALUE)
    private String description;
    private String phone;
    private String email;
    private String site;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Address> addresses = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Contact> contacts = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Media logo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @JsonIgnore
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getMainAddress() {
        if (CollectionUtils.isEmpty(addresses)) {
            return null;
        }
        for (Address address : addresses) {
            if (AddressType.MAIN.equals(address.getAddressType())) {
                return address;
            }
        }
        return addresses.iterator().next();
    }

    @JsonIgnore
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    public Media getLogo() {
        return logo;
    }

    public void setLogo(Media logo) {
        this.logo = logo;
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }
}
