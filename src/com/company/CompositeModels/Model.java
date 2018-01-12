package com.company.CompositeModels;

import com.company.Cameras.Camera2D;
import com.company.Math.Matrix;
import com.company.Models.Model2D;
import com.company.Services.ModelService;

import java.io.File;
import java.util.ArrayList;

import static com.company.Panels.ModelPanel2D.modelPath;

/**
 * Created by Nikita on 12.01.2018.
 */
public class Model implements BaseCompositeModel {

    protected String name;
    protected ArrayList<Model2D> model2DList = new ArrayList<>();
    protected ModelService modelService = new ModelService();


    @Override
    public void loadModel(String model) {
        this.name = model;

        String modelStoragePath = modelPath();

        File modelsStorage = new File(modelStoragePath + model);

        File[] listFiles = modelsStorage.listFiles();

        for (int i = 0; i < listFiles.length; i++){
            String modelName = listFiles[i].getName();
            File[] modelProperties = new File(listFiles[i].getAbsolutePath()).listFiles();
            model2DList.add(new Model2D(listFiles[i].getAbsolutePath() + "\\", modelName, modelProperties));
        }
    }

    @Override
    public void loadAnimations(String model) {

    }

    @Override
    public void runAnimation(String animationName) {

    }

    @Override
    public void render(Camera2D camera2D) {
        for (int index = 0; index < model2DList.size(); index++) {

            Matrix vertices = new Matrix(model2DList.get(index).vertices);
            Matrix edges = new Matrix(model2DList.get(index).edges);


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

                startX = modelService.getX(i, 0, vertices, edges);
                startY = modelService.getY(i, 0, vertices, edges);
                endX = modelService.getX(i, 1, vertices, edges);
                endY = modelService.getY(i, 1, vertices, edges);

                camera2D.moveTo(startX, startY);
                camera2D.lineTo(endX, endY);
            }
        }
    }

    @Override
    public boolean checkName(String modelName) {
        if(name.equals(modelName)){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Model2D> getModelList() {
        return  model2DList;
    }

    @Override
    public void setModelList(ArrayList<Model2D> modelList) {
        this.model2DList = modelList;
    }

    @Override
    public void refresh() {
        loadAnimations(name);
    }

    @Override
    public void toBasePosition() {
        for (Model2D model:
             model2DList) {
            //model.stopTimer();
            model.toBaseVertices();
        }
    }
}
