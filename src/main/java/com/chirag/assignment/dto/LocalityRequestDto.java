package com.chirag.assignment.dto;


// Data Transfer Object (DTO)
public class LocalityRequestDto {
    private String localityName;
    private Long cityId;

    public LocalityRequestDto() {
    }

    public LocalityRequestDto(String localityName, Long cityId) {
        this.localityName = localityName;
        this.cityId = cityId;
    }

    public String getLocalityName() {
        return this.localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "{" +
            " localityName='" + getLocalityName() + "'" +
            ", cityId='" + getCityId() + "'" +
            "}";
    }

}
