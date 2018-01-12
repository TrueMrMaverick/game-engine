package com.company.Scenes;

import com.company.Animations.Animation;
import com.company.Figures.Square;
import com.company.Frames.ModelPropertiesFrame;
import com.company.Math.Matrix;
import com.company.Models.Model2D;
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
        this.g = g;
        render();
    }
    @Override
    public void render(){
        if(isAxes) {
            axes();
        }

        ArrayList<Model2D> model2DList = modelList.get(0).getModelList();

        for (int index = 0; index < model2DList.size(); index++) {

            Matrix vertices = new Matrix(model2DList.get(index).vertices);
            Matrix edges = new Matrix(model2DList.get(index).edges);
            model2DList.get(index).refreshSquares(this);

            // Проверяем, чтобы в низу каждого столбца была 1
            for(int i = 0; i < vertices.getRowSize(); i++){
                if(vertices.getElement(2, i) != 1){
                    for (int j = 0; j < vertices.getColSize(); j++){
                        vertices.setElement(j, i, vertices.getElement(j, i) / vertices.getElement(2, i));
                    }
                }
            }


            double startX, startY;
            double endX, endY;



            for (int i = 0; i < edges.getColSize(); i++){


                startX = getX(i, 0, vertices, edges);
                startY = getY(i, 0, vertices, edges);
                endX = getX(i, 1, vertices, edges);
                endY = getY(i, 1, vertices, edges);
                moveTo(startX, startY);
                lineTo(endX, endY);
            }


            for (Square square:
                    model2DList.get(index).squares) {
                square.render(this);
            }


        }

    }



    public void hasAxes(boolean hasAxes){
        isAxes = hasAxes;
    }
}
