package com.company.Services.EditorServices;

import com.company.Cameras.Camera2D;
import com.company.CompositeModels.BaseCompositeModel;
import com.company.Math.Matrix;
import com.company.Math.Vector;
import com.company.Models.Model2D;

import java.util.ArrayList;

/**
 * Created by Nikita on 12.01.2018.
 */
public class BaseEditorService {

    public void newLine(int x, int y, BaseCompositeModel model, Camera2D camera2D) {
        ArrayList<Model2D> model2DList = model.getModelList();

        Model2D model2D = new Model2D();
        model2D.modelName = "Line " + model2DList.size();
        Matrix vertices = model2D.vertices;
        Matrix edges = model2D.edges;
        double [] vec = {camera2D.screenToWorldX(x), camera2D.screenToWorldY(y), 1};
        vertices.addCol(new Vector(vec));
        vertices.addCol(new Vector(vec));
        vec = new double[]{1, 2};
        edges.addRow(new Vector(vec));
        model2DList.add(model2D);

        model.setModelList(model2DList);
    }


    public void dynamicLine(int x, int y, BaseCompositeModel model, Camera2D camera2D) {
        ArrayList<Model2D> model2DList = model.getModelList();

        Model2D model2D = model2DList.get(model2DList.size() - 1);
        Matrix vertices = model2D.vertices;
        Matrix edges = model2D.edges;
        Vector lastCol = vertices.getLastCol();
        lastCol.set(0, camera2D.screenToWorldX(x));
        lastCol.set(1, camera2D.screenToWorldY(y));
        vertices.setCol(vertices.getRowSize() - 1, lastCol);

        model.setModelList(model2DList);

    }

    public void addPoint(int x, int y, BaseCompositeModel model, Camera2D camera2D) {
        ArrayList<Model2D> model2DList = model.getModelList();

        Model2D model2D = model2DList.get(model2DList.size() - 1);
        Matrix vertices = model2D.vertices;
        Matrix edges = model2D.edges;
        double [] vec = {camera2D.screenToWorldX(x), camera2D.screenToWorldY(y), 1};
        vertices.addCol(new Vector(vec));
        double prevPoint = edges.getElement(edges.getColSize() - 1, 1);
        vec = new double[]{prevPoint, prevPoint + 1};
        edges.addRow(new Vector(vec));

        model.setModelList(model2DList);
    }

    public void endLineDrawing(BaseCompositeModel model) {
        ArrayList<Model2D> model2DList = model.getModelList();

        Model2D model2D = model2DList.get(model2DList.size() - 1);
        Matrix vertices = model2D.vertices;
        Matrix edges = model2D.edges;
        vertices.removeCol(vertices.getRowSize() - 1);
        edges.removeRow(edges.getColSize() - 1);

        model.setModelList(model2DList);
    }
}
