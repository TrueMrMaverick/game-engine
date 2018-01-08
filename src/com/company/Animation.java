package com.company;

import com.company.Enums.AnimationType;
import com.company.Services.AnimationService;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Nikita on 06.01.2018.
 */
public class Animation {

    private String animationName;
    private String modelName;
    private AnimationType animationType;
    private AnimationService animationService = new AnimationService();

    public Animation(){}


    public void load(File file){

    }
}
