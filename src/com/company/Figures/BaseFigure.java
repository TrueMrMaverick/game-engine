package com.company.Figures;

import com.company.Cameras.Camera2D;
import com.company.Math.Matrix;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikita on 22.10.2017.
 */
public abstract class BaseFigure extends Camera2D{
    double width;
    double height;
    private int prevX;
    private int prevY;
    private boolean isDragging;


    public BaseFigure() {}

    public BaseFigure(JPanel jPanel) {
        super(jPanel);
    }

    public BaseFigure(JPanel jPanel, double l, double r, double b, double t) {
        super(jPanel, l, r, b, t);
    }

    public BaseFigure(JPanel jPanel, double l, double r, double b, double t, double posX, double posY, double width, double height, Graphics2D g) {
        super(jPanel, l, r, b, t);
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.g = g;
    }

    public BaseFigure(Camera2D camera2D, double posX, double posY, double width, double height){
        super(camera2D);
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    public BaseFigure(Camera2D camera2D, double posX, double posY, double width) {
        super(camera2D);
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = width;
    }

    abstract boolean innerPoint(int x, int y);

    @Override
    public boolean startDragging(int x, int y){
        if(innerPoint(x, y)){
            isDragging = super.startDragging(x, y);
            return isDragging;
        }
        return false;
    }

    public void dragFigure(int x, int y){
        if(isDragging){
            posX += screenToWorldX(x - getPrevDragScreenX());
            posY += screenToWorldY(y - getPrevDragScreenY());
            startDragging(x, y);
        }
    }

    public void stopDragging(){
        isDragging = false;
    }

    public void move(double x, double y){
        posX = x;
        posY = y;
    }

    public void refreshCamera(Camera2D camera2D){
        jPanel = camera2D.getjPanel();
        L = camera2D.getL();
        R = camera2D.getR();
        B = camera2D.getB();
        T = camera2D.getT();
        g = camera2D.getG();
        W = camera2D.getW();
        H = camera2D.getH();
    }

    public void setX(int xPos) {
        this.posX = xPos;
    }

    public double getX() {
        return posX;
    }

    public void setY(int yPos) {
        this.posY = yPos;
    }

    public double getY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

}