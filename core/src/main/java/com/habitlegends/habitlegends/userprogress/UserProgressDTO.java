package com.habitlegends.habitlegends.userprogress;

public class UserProgressDTO {

    private Long id;
    private Long userId;
    private Integer level;
    private Integer exp;
    private Integer rankId;

    public UserProgressDTO() {
    }

    public UserProgressDTO(Long id, Long userId, Integer level, Integer exp, Integer rankId) {
        this.id = id;
        this.userId = userId;
        this.level = level;
        this.exp = exp;
        this.rankId = rankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

}
