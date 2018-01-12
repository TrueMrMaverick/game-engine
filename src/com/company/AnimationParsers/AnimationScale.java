package com.company.AnimationParsers;

import com.company.Math.AffineTransform2D;
import com.company.Math.Matrix;


/**
 * Created by Nikita on 11.01.2018.
 */
public class AnimationScale implements AnimationParser {

    private double param1;
    private double param2;
    private double time;
    private double stepParam1;
    private double stepParam2;


    public AnimationScale(){}

    public AnimationScale(double param1, double param2, double time) {
        this.param1 = param1;
        this.param2 = param2;
        this.time = time;

        if (time == 0) {
            stepParam1 = param1;
            stepParam2 = param2;
        } else {
            stepParam1 = param1 / time;
            stepParam2 = param2 / time;
        }
    }

    @Override
    public boolean run(Matrix vertices) {
        if(time > 0){
            vertices = scale(vertices, stepParam1, stepParam2);
            time--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean reverse(Matrix vertices) {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public double getTime() {
        return time;
    }

    public Matrix scale(Matrix matrix, double kx, double ky){
        return AffineTransform2D.scaling(kx,ky).mult(matrix);
    }

}
