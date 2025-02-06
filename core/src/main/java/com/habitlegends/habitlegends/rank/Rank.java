package com.habitlegends.habitlegends.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`rank`")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer minExp;

    public Rank() {
    }

    public Rank(Integer id, String name, Integer minExp) {
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
