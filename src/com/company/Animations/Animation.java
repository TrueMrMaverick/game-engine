package com.company.Animations;

import com.company.Math.Matrix;
import com.company.Services.AnimationService;

import java.io.File;

/**
 * Created by Nikita on 25.12.2017.
 */
public class Animation {

    private String name;
    private Matrix matrix;

    private AnimationService animationService = new AnimationService();

    public Animation(){
        matrix = new Matrix();
        name = "";
    }

    public Animation(File file) {
        name = file.getName().split("\\.")[0];
        matrix = animationService.animationLoader(file);
    }

    public Animation(String animationPath){
        this(new File(animationPath));
        name = new File(animationPath).getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}

