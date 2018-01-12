package com.company.AnimationParsers;

import com.company.Math.Matrix;

import static com.company.AnimationParsers.AnimationFixedTurn.turn;

/**
 * Created by Nikita on 11.01.2018.
 */
public class AnimationForwardTurn implements AnimationParser {

    private double param1;
    private double param2;
    private double time;
    private double firstTime;
    private double stepParam1;


    public AnimationForwardTurn() {
    }

    public AnimationForwardTurn(double param1, double param2, double time) {
        this.param1 = param1;
        this.param2 = param2;
        this.time = time;
        firstTime = time;


        if(time == 0){
            stepParam1 = param1;
        } else {
            stepParam1 = param1 / time;
        }
    }

    @Override
    public boolean run(Matrix vertices) {
        if (time > 0) {
            //vertices.print("До анимации");
            vertices = forwardTurn(vertices, stepParam1, (int) param2);
            //vertices.print("После анимации");
            time--;
            return true;
        } else {
            return false;
        }
    }

    public boolean reverse(Matrix vertices) {
        if (time <= firstTime) {
            vertices = turn(vertices, -stepParam1, (int) param2);
            time++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public double getTime() {
        return time;
    }

    public static Matrix forwardTurn(Matrix matrix, double t, int point){
        Matrix result = new Matrix();
        for (int i = point; i < matrix.getRowSize(); i++) {
            result.addCol(matrix.getCol(i));
        }

        result = turn(result, t, 0);

        for (int i = point; i < matrix.getRowSize(); i++) {
            matrix.setCol(i, result.getCol(i - point));
        }

        return matrix;
    }
}
