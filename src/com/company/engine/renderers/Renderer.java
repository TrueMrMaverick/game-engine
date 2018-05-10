package com.company.engine.renderers;

import com.company.engine.core.cameras.Camera2D;
import com.company.engine.core.math.Matrix;
import com.company.engine.models.Model2D;
import com.company.engine.services.Model2DService;

import java.util.ArrayList;

public class Renderer {

    Model2DService model2DService;

    public void render(Camera2D camera2D, ArrayList<Model2D> model2DList){
        for (int index = 0; index < model2DList.size(); index++) {

            Matrix vertices = new Matrix(model2DList.get(index).getVertices());
            Matrix edges = new Matrix(model2DList.get(index).getEdges());


            // Проверяем, чтобы в низу каждого столбца была 1
            for(int i = 0; i < vertices.getRowSize(); i++){
                if(vertices.getElement(2, i) != 1){
                    for (int j = 0; j < vertices.getColSize(); j++){
                        vertices.setElement(j, i, vertices.getElement(j, i) / vertices.getElement(2, i));
                    }
                }
            }

            for (int i = 0; i < edges.getColSize(); i++){
                double startX, startY;
                double endX, endY;

                startX = model2DService.getX(i, 0, vertices, edges);
                startY = model2DService.getY(i, 0, vertices, edges);
                endX = model2DService.getX(i, 1, vertices, edges);
                endY = model2DService.getY(i, 1, vertices, edges);

                camera2D.moveTo(startX, startY);
                camera2D.lineTo(endX, endY);
            }
        }
    }
}
