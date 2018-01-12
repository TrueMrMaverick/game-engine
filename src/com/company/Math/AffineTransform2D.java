package com.company.Math;

import java.util.ArrayList;

import static java.lang.StrictMath.*;

/**
 * Created by Nikita on 24.12.2017.
 */
public abstract class AffineTransform2D {
    public static  Matrix translation(double x, double y){
        double[] T = {
                    1, 0, x,
                    0, 1, y,
                    0, 0, 1};
        return new Matrix(3, 3, T);
    }

    //тождественное АП;
    public static  Matrix Identity() {
        double[] T = {
                1, 0, 0,
                0, 1, 0,
                0, 0, 1 };
        return new Matrix(3, 3, T);
    }

    // поворот на угол t вокруг начала координат против часовой стрелки;
    public static  Matrix rotation(double t) {
        double[] T = {
                cos(t), -sin(t), 0,
                sin(t),  cos(t), 0,
                0, 0, 1 };
        return new Matrix(3, 3, T);
    }

    // Rotation(c, s) - поворот на угол, косинус и синус которого пропорциональны величинам c и s;
    // поворот на угол t вокруг начала координат против часовой стрелки;
    public static  Matrix rotation(double c, double s) {
        double[] T = {
                c, -s, 0,
                s, c, 0,
                0, 0, 1 };
        return new Matrix(3, 3, T);
    }


    // Scaling(kx, ky) - масштабирование;
    public static  Matrix scaling(double kx, double ky) {
        double[] T = {
                kx, 0, 0,
                0, ky, 0,
                0, 0, 1 };
        return new Matrix(3, 3, T);
    }
}
