package com.habitlegends.habitlegends.dto;

public class RankDTO {

    private Integer id;
    private String name;
    private Integer minExp;

    public RankDTO() {
    }

    public RankDTO(Integer id, String name, Integer minExp) {
        this.id = id;
        this.name = name;
        this.minExp = minExp;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMinExp() {
        return minExp;
    }
    public void setMinExp(Integer minExp) {
        this.minExp = minExp;
    }

    
}
