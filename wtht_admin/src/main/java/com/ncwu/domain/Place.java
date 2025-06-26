package com.ncwu.domain;

/**
 * ClassName: Place
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:38
 * @Version 1.0
 */
public class Place {
    private int placeId;
    private String name;
    private String site;
    private String facility;
    private String description;
    private String status;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", facility='" + facility + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
