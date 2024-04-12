package com.ruoyi.system.domain;

import java.util.Date;

public class PersonRecord {
    private Integer id;
    private String firstName;
    // private LastName lastName;
    private Date birthDate;
    private Boolean employed;
    private String occupation;
    private Integer addressId;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "PersonRecord{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", employed=" + employed +
                ", occupation='" + occupation + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}