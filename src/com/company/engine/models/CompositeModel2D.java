package com.company.engine.models;

import java.util.Map;

public class CompositeModel2D {

    private String Name;
    private Map<String, Model2D> model2DMap;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map<String, Model2D> getModel2DMap() {
        return model2DMap;
    }

    public void setModel2DMap(Map<String, Model2D> model2DMap) {
        this.model2DMap = model2DMap;
    }
}
