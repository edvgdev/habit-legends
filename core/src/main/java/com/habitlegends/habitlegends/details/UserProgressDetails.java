package com.habitlegends.habitlegends.details;

public class UserProgressDetails {
    private Long userId;
    private Integer level;
    private Integer exp;
    private String rank;
    private Integer minExpToNextRank;
    private Integer expRequiredForNextLevel;

    public UserProgressDetails() {
    }

    public UserProgressDetails(Long userId, Integer level, Integer exp, String rank, Integer minExpToNextRank,
            Integer expRequiredForNextLevel) {
        this.userId = userId;
        this.level = level;
        this.exp = exp;
        this.rank = rank;
        this.minExpToNextRank = minExpToNextRank;
        this.expRequiredForNextLevel = expRequiredForNextLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getMinExpToNextRank() {
        return minExpToNextRank;
    }

    public void setMinExpToNextRank(Integer minExpToNextRank) {
        this.minExpToNextRank = minExpToNextRank;
    }

    public Integer getExpRequiredForNextLevel() {
        return expRequiredForNextLevel;
    }

    public void setExpRequiredForNextLevel(Integer expRequiredForNextLevel) {
        this.expRequiredForNextLevel = expRequiredForNextLevel;
    }

}
