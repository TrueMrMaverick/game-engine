package com.company.Scenes;

import com.company.Animation;
import com.company.Frames.ModelPropertiesFrame;
import com.company.Panels.ModelPanel2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nikita on 06.01.2018.
 */
public class AnimationCreatorScene extends Scene2D {

    private ArrayList<Animation> animationArrayList = new ArrayList<>();
    private boolean isAxes = false;


    public AnimationCreatorScene(ModelPanel2D jPanel, double l, double r, double b, double t){
        super(jPanel, l, r, b, t);
    }

    @Override
    public void render(Graphics2D g){
        super.render(g);
    }
    @Override
    public void render(){
        if(isAxes) {
            axes();
        }
        super.render();
    }

    public void hasAxes(boolean hasAxes){
        isAxes = hasAxes;
    }
}
