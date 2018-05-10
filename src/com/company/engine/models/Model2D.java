package com.company.engine.models;

import com.company.engine.core.math.Matrix;

public class Model2D {

    private String name;
    private Matrix vertices;
    private Matrix edges;

    public Matrix getVertices() {
        return vertices;
    }

    public void setVertices(Matrix vertices) {
        this.vertices = vertices;
    }

    public Matrix getEdges() {
        return edges;
    }

    public void setEdges(Matrix edges) {
        this.edges = edges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
