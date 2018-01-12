package com.company.AnimationParsers;

import com.company.Math.Matrix;

import javax.swing.*;


/**
 * Created by Nikita on 11.01.2018.
 */
public interface AnimationParser {

    boolean run(Matrix vertices);

    boolean reverse(Matrix vertices);

    void stop();

    double getTime();



}
