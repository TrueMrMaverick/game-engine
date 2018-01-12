package com.company.AnimationParsers;

import com.company.Math.AffineTransform2D;
import com.company.Math.Matrix;

import static com.company.AnimationParsers.AnimationMove.move;

/**
 * Created by Nikita on 11.01.2018.
 */
public class AnimationFixedTurn implements AnimationParser {

    private double param1;
    private double param2;
    private double time;
    private double stepParam1;


    public AnimationFixedTurn() {
    }

    public AnimationFixedTurn(double param1, double param2, double time) {
        this.param1 = param1;
        this.param2 = param2;
        this.time = time;

        if(time == 0){
            stepParam1 = param1;
        } else {
            stepParam1 = param1 / time;
        }
    }

    @Override
    public boolean run(Matrix vertices) {
        if (time > 0) {
            vertices = turn(vertices, stepParam1, (int) param2);
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

    public static Matrix turn(Matrix matrix, double t, int point){
        double pointX = matrix.getElement(0, point);
        double pointY = matrix.getElement(1, point);
        Matrix result = matrix.clone();
        result = move(result, -pointX, -pointY);
        //result.print("До поворота: ");
        result = AffineTransform2D.rotation(Math.toRadians(t)).mult(result);
        //result.print("После поворота: ");
        result = move(result, pointX, pointY);
        return result;
    }
}
