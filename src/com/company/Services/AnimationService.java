package com.company.Services;

import com.company.AnimationParsers.*;
import com.company.Animations.Animation;
import com.company.Enums.AnimationType;
import com.company.Math.Matrix;

import java.io.File;

import java.util.ArrayList;


/**
 * Created by Nikita on 26.12.2017.
 */
public class AnimationService extends BaseService{

    private ArrayList<AnimationParser> animationParsers = new ArrayList<>();

    public AnimationService(){}

    public Matrix animationLoader(File file) {
       return matrixFromFileLoader(file);
    }


    public ArrayList<Animation> modelAnimationsLoader(File animationDir) {
        ArrayList<Animation> animations = new ArrayList<>();

        for (File file:
                animationDir.listFiles()) {
            animations.add(new Animation(file));
        }

        return animations;
    }

    public void setAnimationParsers(Animation animation) {
        animationParsers.clear();
        Matrix matrix = animation.getMatrix();

        for (int i = 0; i < matrix.getColSize(); i++) {
            AnimationType animationType = AnimationType.intToAnimationType((int) matrix.getElement(i, 0));
            double param1 = matrix.getElement(i, 1);
            double param2 = matrix.getElement(i, 2);
            double time = (int) matrix.getElement(i, 3);

            switch (animationType){
                case MOVE:
                    animationParsers.add(new AnimationMove(param1, param2, time));
                    break;
                case SCALE:
                    animationParsers.add(new AnimationScale(param1, param2, time));
                    break;
                case FIXED_TURN:
                    animationParsers.add(new AnimationFixedTurn(param1, param2, time));
                    break;
                case FORWARD_TURN:
                    animationParsers.add(new AnimationForwardTurn(param1, param2, time));
                    break;
            }
        }
    }

    public boolean runAnimation(Matrix vertices){
        for (AnimationParser animationParser:
             animationParsers) {
            if (!animationParser.run(vertices)){
                return false;
            }
        }
        return true;
    }

    public boolean reverseAnimation(Matrix vertices) {
        for (AnimationParser animationParser:
                animationParsers) {
            if (!animationParser.reverse(vertices)){
                return false;
            }
        }
        return true;
    }
}

































