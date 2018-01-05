package com.company.Services;

import com.company.DTO.AnimationDTO;
import com.company.Math.AffineTransform2D;
import com.company.Math.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/**
 * Created by Nikita on 26.12.2017.
 */
public class AnimationService {

    private AffineTransform2D affineTransform2D = new AffineTransform2D();


    public AnimationService(){}

    public ArrayList<AnimationDTO> getModelAnimations(File file){

        ArrayList<String> stringList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader out = new BufferedReader(fileReader);

            try {
                String stringBuffer;
                while ((stringBuffer = out.readLine()) != null){
                    stringList.add(stringBuffer);
                }
            } finally {
                out.close();
            }

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<AnimationDTO> animationList = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        String buffer = "";
        for (int i = 0; i < stringList.size(); i++) {
            String subString = stringList.get(i);
            for(int j = 0; j < subString.length(); j++){
                if(subString.charAt(j) != ' '){
                    buffer += subString.charAt(j);
                } else {
                    temp.add(buffer);
                    buffer = "";
                }
            }

            temp.add(buffer);

            AnimationDTO animationDTO = new AnimationDTO();
            for (int j = 0; j < temp.size(); j++){
                if(j == 0){
                    animationDTO.setType(temp.get(j));
                } else {
                    animationDTO.getParams().add(temp.get(j));
                }
            }

            animationList.add(animationDTO);
            temp.clear();
        }

        return animationList;
    }

    public Matrix move(Matrix matrix, double x, double y){
        return affineTransform2D.translation(x, y).mult(matrix);
    }

    public Matrix turn(Matrix matrix, double t, int point){
        double pointX = matrix.getElement(0, point - 1);
        double pointY = matrix.getElement(1, point - 1);
        Matrix result = matrix.clone();
        result = move(result, -pointX, -pointY);
        //result.print("До поворота: ");
        result = affineTransform2D.rotation(t).mult(result);
        //result.print("После поворота: ");
        result = move(result, pointX, pointY);
        return result;
    }
    public Matrix turn(Matrix matrix, double cos, double sin, int point){
        double pointX = matrix.getElement(0, point - 1);
        double pointY = matrix.getElement(1, point - 1);
        Matrix result = matrix.clone();
        result = move(result, -pointX, -pointY);
        //result.print("До поворота: ");
        result = affineTransform2D.rotation(cos, sin).mult(result);
        //result.print("После поворота: ");
        result = move(result, pointX, pointY);
        return result;
    }

    public Matrix scale(Matrix matrix, double kx, double ky){
        return affineTransform2D.scaling(kx,ky).mult(matrix);
    }

    public Matrix reflect(Matrix matrix){
        double pointX = matrix.getElement(0, 0);
        double pointY = matrix.getElement(1, 0);
        Matrix result = matrix.clone();
        result = move(result, -pointX, -pointY);
        result = affineTransform2D.rotation(cos(PI), sin(PI)).mult(result);
        result = move(result, -pointX, -pointY);
        return result;
    }
}

































