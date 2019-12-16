package com.SmartTech.hrapp.Model;

public class VacationsModel {
    private String id,name,countPosition,ratio,lastDate;

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountPosition() {
        return countPosition;
    }

    public void setCountPosition(String countPosition) {
        this.countPosition = countPosition;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
