package com.company.Figures;

import com.company.Cameras.Camera2D;
import com.company.Math.Matrix;

import java.awt.*;

/**
 * Created by Nikita on 05.11.2017.
 */
public class Square extends BaseFigure {

    public Square() {}

    public Square(Camera2D camera2D, double xPos, double yPos, int width, int height) {
        super(camera2D, xPos, yPos, width, height);
    }

    public Square(Camera2D camera2D, double xPos, double yPos, double width) {
        super(camera2D, xPos, yPos, width);
    }

    @Override
    public boolean innerPoint(int xScreen, int yScreen) {
        double x = screenToWorldX(xScreen);
        double y = screenToWorldY(yScreen);
        if(x - posX <= width && x - posX >= 0 && y - posY <= height && y - posY >= 0) {
            return true;
        } else return false;
    }

    public void render(Camera2D camera2D){

        refreshCamera(camera2D);

        moveTo(posX, posY);
        lineTo(posX + width, posY);
        lineTo(posX, posY + width);
        lineTo(posX - width, posY);
        lineTo(posX, posY - width);
    }

    public void refresh(double xPos, double yPos){
        this.posX = xPos;
        this.posY = yPos;
    }



}