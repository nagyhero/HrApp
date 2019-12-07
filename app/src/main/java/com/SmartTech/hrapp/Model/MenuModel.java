package com.SmartTech.hrapp.Model;

import java.util.ArrayList;

public class MenuModel {

    private String title;
    private int icon;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private ArrayList<MenuModel> listInside=new ArrayList<>();

    public ArrayList<MenuModel> getListInside() {
        return listInside;
    }

    public void setListInside(ArrayList<MenuModel> listInside) {
        this.listInside = listInside;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
