package com.company.CompositeModels;

import com.company.Cameras.Camera2D;
import com.company.Models.Model2D;

import java.util.ArrayList;

/**
 * Created by Nikita on 12.01.2018.
 */
public interface BaseCompositeModel {

    void loadModel(String model);

    void loadAnimations(String model);

    void runAnimation(String animationName);

    void render(Camera2D camera2D);

    boolean checkName(String modelName);

    ArrayList<Model2D> getModelList();

    void setModelList(ArrayList<Model2D> modelList);

    void refresh();

    void toBasePosition();

}
