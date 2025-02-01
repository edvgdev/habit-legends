package com.habitlegends.habitlegends.details;

public class UserStatDetails {
    private Long userId;
    private String statName;
    private Integer currentPoints;
    private String iconUrl;

    public UserStatDetails() {
    }

    public UserStatDetails(Long userId, String statName, Integer currentPoints, String iconUrl) {
        this.userId = userId;
        this.statName = statName;
        this.currentPoints = currentPoints;
        this.iconUrl = iconUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
