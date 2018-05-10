package com.company.engine.services;

import com.company.engine.core.math.Matrix;

public abstract class Model2DService {

    public double getX(int index, int type, Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(0, (int) pointNumber - 1);
    }

    public double getY(int index, int type, Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(1, (int) pointNumber - 1);
    }

}
