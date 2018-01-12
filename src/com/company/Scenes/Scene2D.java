package com.company.Scenes;

import com.company.Cameras.Camera2D;
import com.company.CompositeModels.BaseCompositeModel;
import com.company.CompositeModels.Model;
import com.company.CompositeModels.Person;
import com.company.Math.Matrix;
import com.company.Math.Vector;
import com.company.Models.Model2D;
import com.company.Panels.ModelPanel2D;
import com.company.Services.EditorServices.BaseEditorService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nikita on 06.11.2017.
 */
public class Scene2D extends Camera2D {

    private boolean isAxes = false;

    public String functionType;

    ArrayList<BaseCompositeModel> modelList = new ArrayList<>();

    private BaseEditorService baseEditorService = new BaseEditorService();




    public Scene2D() {
    }

    public Scene2D(ModelPanel2D jPanel) {
        super(jPanel);
    }

    public Scene2D(ModelPanel2D jPanel, double l, double r, double b, double t) {
        super(jPanel, l, r, b, t);
    }


    public void render(Graphics2D g){
        this.g = g;
        render();
    }

    public void render(){
        if(isAxes) {
            axes();
        }

        for (BaseCompositeModel model:
             modelList) {
            model.render(this);
        }
    }

    protected double getX(int index, int type,  Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(0, (int) pointNumber - 1);
    }

    protected double getY(int index, int type, Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(1, (int) pointNumber - 1);
    }

    public void addModel(String model){
        Person person = new Person();
        person.loadModel(model);
        modelList.add(person);
    }


    public ArrayList<BaseCompositeModel> getModelList(){
        return modelList;
    }

    public void newLine(int x, int y) {
        if(modelList.size() == 0){
            modelList.add(new Model());
        }
        baseEditorService.newLine(x, y, modelList.get(0), this);
    }

    public void dynamicLine(int x, int y) {
        if(modelList.size() == 0){
            modelList.add(new Model());
        }
        baseEditorService.dynamicLine(x, y, modelList.get(0), this);
    }

    public void addPoint(int x, int y) {
        if(modelList.size() == 0){
            modelList.add(new Model());
        }
        baseEditorService.addPoint(x, y, modelList.get(0), this);
    }

    public void endLineDrawing() {
        baseEditorService.endLineDrawing(modelList.get(0));
    }

    public void hasAxes(boolean hasAxes){
        isAxes = hasAxes;
    }

    public void startAnimation(String modelName, String animationName){
        for (BaseCompositeModel model2D:
             modelList) {
            if(model2D.checkName(modelName)){
                model2D.runAnimation(animationName);
            }
        }
    }

    public void endAnimation(String modelName){
        for (BaseCompositeModel model2D:
                modelList) {
            if(model2D.checkName(modelName)) {
                model2D.toBasePosition();
                model2D.runAnimation("BasePose");
            }
        }
    }

    public void refreshModels() {
        for (BaseCompositeModel model:
             modelList) {
            model.refresh();
        }
    }

    public void toBasePosition(String modelName) {
        for (BaseCompositeModel model2D:
                modelList) {
            if(model2D.checkName(modelName)) {
                model2D.toBasePosition();
            }
        }
    }
}
