package com.company.Models;

import com.company.Cameras.Camera2D;
import com.company.Animations.Animation;
import com.company.Figures.Square;
import com.company.Math.Matrix;
import com.company.Services.AnimationService;
import com.company.Services.ModelService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nikita on 24.12.2017.
 */
public class Model2D {
    public String modelName;
    public Matrix vertices;
    public Matrix baseVertices;
    public Matrix edges;
    private String path;
    private boolean isAnimated;
    private Timer timer;
    public ArrayList<Square> squares = new ArrayList<>();
    public ArrayList<Animation> modelAnimations = new ArrayList<>();
    private boolean animationFlag = false;



    private AnimationService animationService = new AnimationService();
    private ModelService modelService = new ModelService();


    public Model2D(){
        modelName = "";
        path = "";
        vertices = new Matrix();
        edges = new Matrix();
        isAnimated = false;
    }


    public Model2D(String path, String modelName, File[] modelProperties){
        this.path = path;
        this.modelName = modelName;
        vertices = new Matrix(modelService.modelLoader(path + "Vertices.txt"));
        edges = new Matrix(modelService.modelLoader(path + "Edges.txt"));
        baseVertices = vertices.clone();
        File animationDir = new File(path + "Animations");
        if(animationDir.exists()){
            modelAnimations = animationService.modelAnimationsLoader(animationDir);
        }
    }



    public void animation(ActionListener actionListener){
        Timer timer = new Timer(0, actionListener);
        timer.start();
    }

    public void animation(int delay, ActionListener actionListener){
        Timer timer = new Timer(delay, actionListener);
        timer.start();
    }

    public void animation(String animationName){
        int i = 0;
        for (; i < modelAnimations.size(); i++) {
            if(modelAnimations.get(i).getName().equals(animationName)){
                break;
            }
        }
        if (!animationFlag && i != modelAnimations.size()) {
            Animation animation = modelAnimations.get(i);
            animationService.setAnimationParsers(animation);

            timer = new Timer(0, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!animationService.runAnimation(vertices)){
                        timer.stop();
                        setAnimationFlag();
                    }
                }
            });
            timer.start();
            animationFlag = true;
        }

    }

    private void setAnimationFlag() {
        if(animationFlag){
            animationFlag = false;
        } else {
            animationFlag = true;
        }
    }

    public void refreshSquares(Camera2D camera2D) {
        double squareWidth = 1;
        if(squares.size() == 0){
            for(int i = 0; i < vertices.getRowSize(); i++){
                Square square = new Square(camera2D, vertices.getElement(0, i) - squareWidth/2 , vertices.getElement(1, i) - squareWidth/2, squareWidth);
                squares.add(square);
            }
        } else {
            for(int i = 0; i < vertices.getRowSize(); i++){
                squares.get(i).refresh(vertices.getElement(0, i) - squareWidth/2 , vertices.getElement(1, i) - squareWidth/2);
            }
        }

    }

    public void toBaseVertices(){
        vertices = baseVertices.clone();
        return;
    }

    public void stopTimer(){
        if(timer != null){
            timer.stop();
        }
    }

}
