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
public class Person extends Model {

    @Override
    public void loadAnimations(String model) {

    }

    @Override
    public void runAnimation(String animationName) {
        for (Model2D model2D:
                model2DList) {
            model2D.animation(animationName);
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
}
