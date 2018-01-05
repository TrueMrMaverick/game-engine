package com.company.Cameras;

import javafx.scene.shape.MoveTo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikita on 06.11.2017.
 */
public class Camera2D {

    protected JPanel jPanel;
    protected Graphics g;
    protected double L, R, B, T;
    protected int W, H;
    private double posX, posY;
    private int prevScreenX, prevScreenY;
    private boolean isDragging;
    private int prevDragScreenX, prevDragScreenY;



    public int worldToScreenX(double X){
        double val = ((X - L) * W) / (R - L);
        return (int) Math.round(val);
    }

    public int worldToScreenY(double Y){
        double val = ((T - Y) * H) / (T - B);
        return (int) Math.round(val);
    }

    public double screenToWorldX (int X) {
        double notW = 1.0 / (double)W;
        double val = L + ((R - L) * ((double) X + 0.5)) * notW;
        return val;
    }

    public double screenToWorldY (int Y) {
        double notH = 1.0 / (double)H;
        double val = T - ((T - B) * ((double) Y + 0.5)) * notH;
        return val;
    }

    public Camera2D(){

    }

    public Camera2D(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public Camera2D(JPanel jPanel, double l, double r, double b, double t) {
        this.jPanel = jPanel;
        L = l;
        R = r;
        B = b;
        T = t;
    }

    public void clear(){
        Rectangle rectangle = new Rectangle();
        rectangle = jPanel.getBounds(rectangle);

        Graphics g = jPanel.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        jPanel.repaint();
    }

    public void setResolution(JPanel jPanel){

        this.jPanel = jPanel;

        Rectangle rectangle = new Rectangle();
        rectangle = jPanel.getBounds(rectangle);

        W = rectangle.width;
        H = rectangle.height;

        double newB = (B + T) / 2 - ((R - L) / 2) * ((double)H / W);
        double newT = (B + T) / 2 + ((R - L) / 2) * ((double)H / W);
        B = newB;
        T = newT;
        double newL = (L + R) / 2 - ((T - B) / 2) * ((double)W / H);
        double newR = (L + R) / 2 + ((T - B) / 2) * ((double)W / H);
        L = newL;
        R = newR;
    }

    public void moveTo(double X, double Y){
        posX = X;
        posY = Y;
    }

    public void move(int X, int Y){
        L += X;
        R += X;
        T -= Y;
        B -= Y;
    }

    public void lineTo(double X, double Y){
        int screenX = worldToScreenX(X);
        int screenY = worldToScreenY(Y);

        prevScreenX = worldToScreenX(posX);
        prevScreenY= worldToScreenY(posY);

        //moveTo(prevScreenX, prevScreenY);

        g.drawLine(prevScreenX, prevScreenY, screenX, screenY);

        moveTo(X, Y);

        jPanel.repaint();
    }

    public void axes() {
        // Отрисовка координатных осей
        moveTo(L, 0);
        lineTo(R, 0);
        moveTo(0, B);
        lineTo(0, T);
    }

    public void startDragging(int X, int Y) {
        isDragging = true;
        prevDragScreenX = X;
        prevDragScreenY = Y;
    }

    public void drag(int X, int Y){
        double newL = L - ((R - L) * (X - prevDragScreenX)) / W;
        double newR = R - ((R - L) * (X - prevDragScreenX)) / W;
        L = newL;
        R = newR;
        double newB = B + ((T - B) * (Y - prevDragScreenY)) / H;
        double newT = T + ((T - B) * (Y - prevDragScreenY)) / H;
        B = newB;
        T = newT;
        startDragging(X, Y);
    }

    public void stopDragging() {
        isDragging = false;
    }

    public void onScroll(int screenX, int screenY, int isUp){

        double deltaSize = 1.1;
        double X = screenToWorldX(screenX);
        double Y = screenToWorldY(screenY);

        // если мы удаляемся
        if (isUp < 0){
            double newB = Y - (Y - B) / deltaSize;
            double newT = Y + (T - Y) / deltaSize;
            B = newB;
            T = newT;
            double newL = X - (X - L) / deltaSize;
            double newR = X + (R - X) / deltaSize;
            L = newL;
            R = newR;

        }
        // если приближаемся
        else {
            double newB = Y - deltaSize * (Y - B);
            double newT = Y + deltaSize * (T - Y);
            B = newB;
            T = newT;
            double newL = X - deltaSize * (X - L);
            double newR = X + deltaSize * (R - X);
            L = newL;
            R = newR;
        }


    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public int getW() {
        return W;
    }

    public void setW(int w) {
        W = w;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getPrevScreenX() {
        return prevScreenX;
    }

    public void setPrevScreenX(int prevScreenX) {
        this.prevScreenX = prevScreenX;
    }

    public int getPrevScreenY() {
        return prevScreenY;
    }

    public void setPrevScreenY(int prevScreenY) {
        this.prevScreenY = prevScreenY;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }
}
