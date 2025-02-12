package com.habitlegends.habitlegends.userprogress;

public class ProgressUpdateInfoDetails {

    private ProgressCategory updatedProgress;

    private Integer oldProgressValue;

    private Integer newProgressValue;

    private String name;

    public ProgressUpdateInfoDetails() {
    }

    public ProgressUpdateInfoDetails(ProgressCategory updatedProgress, Integer oldProgressValue,
            Integer newProgressValue, String name) {
        this.updatedProgress = updatedProgress;
        this.oldProgressValue = oldProgressValue;
        this.newProgressValue = newProgressValue;
        this.name = name;
    }

    public ProgressCategory getUpdatedProgress() {
        return updatedProgress;
    }

    public void setUpdatedProgress(ProgressCategory updatedProgress) {
        this.updatedProgress = updatedProgress;
    }

    public Integer getOldProgressValue() {
        return oldProgressValue;
    }

    public void setOldProgressValue(Integer oldProgressValue) {
        this.oldProgressValue = oldProgressValue;
    }

    public Integer getNewProgressValue() {
        return newProgressValue;
    }

    public void setNewProgressValue(Integer newProgressValue) {
        this.newProgressValue = newProgressValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}