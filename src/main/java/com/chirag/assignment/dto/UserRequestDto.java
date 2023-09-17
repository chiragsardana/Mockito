package com.chirag.assignment.dto;


// Data Transfer Object (DTO)
public class UserRequestDto {
    private String name;
    private String email;
    private Long cityId;
    private Long localityId;

    public UserRequestDto() {
    }

    public UserRequestDto(String name, String email, Long cityId, Long localityId) {
        this.name = name;
        this.email = email;
        this.cityId = cityId;
        this.localityId = localityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getLocalityId() {
        return this.localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", cityId='" + getCityId() + "'" +
            ", localityId='" + getLocalityId() + "'" +
            "}";
    }

}
