package com.company.Models;

import com.company.DTO.AnimationDTO;
import com.company.Frames.ModelPropertiesFrame;
import com.company.Math.Matrix;
import com.company.Services.AnimationService;
import com.company.Services.ModelService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 24.12.2017.
 */
public class Model2D {
    public String modelName;
    public Matrix vertices;
    public Matrix edges;
    private String path;
    private boolean isAnimated;


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

        for (File file:
             modelProperties) {
            if(file.getName().equals("Animation.txt")){
                isAnimated = true;
                animation(file);
            }
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

    public void animation(File animationFile){

        ArrayList<AnimationDTO> animationList = animationService.getModelAnimations(animationFile);

        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (AnimationDTO animation:
                        animationList) {
                    List<String> params = animation.getParams();
                    if (animation.getType().equals("move")){
                        vertices = animationService.move(vertices, Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)));
                    } else if (animation.getType().equals("turn")){
                        if(params.size() == 2){
                            vertices = animationService.turn(vertices, Double.parseDouble(params.get(0)), Integer.parseInt(params.get(1)));
                        } else {
                            vertices = animationService.turn(vertices, Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)), Integer.parseInt(params.get(2)));
                        }
                    } else if (animation.getType().equals("scale")){
                        vertices = animationService.scale(vertices, Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)));
                    } if (animation.getType().equals("reflect")){
                        vertices = animationService.reflect(vertices);
                    }
                }
            }
        });
        timer.start();
    }

}
