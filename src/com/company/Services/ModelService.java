package com.company.Services;

import com.company.Math.Matrix;
import com.company.Math.Vector;
import com.company.Models.Model2D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 26.12.2017.
 */
public class ModelService extends BaseService{
    public ModelService() {
    }

    public Matrix modelLoader(String filePath){
        File file = new File(filePath);

        return matrixFromFileLoader(file);
    }

    public void modelSaver(File file, ArrayList<Model2D> model2DArrayList){
        if (!file.exists()){
            if(!file.mkdir()){
                System.out.println("Ошибка сохранения модели");
            }
        }
        for (Model2D model2D:
             model2DArrayList) {
            String path = file.getAbsolutePath() + "//" + model2D.modelName;
            File dir = new File(path);
            if(dir.mkdir()){
                Matrix vertices = model2D.vertices;
                Matrix edges = model2D.edges;

                File verticesFile = new File(path, "Vertices.txt");
                File edgesFile = new File(path, "Edges.txt");



                writeInFile(verticesFile, vertices.toFileWrite());
                writeInFile(edgesFile, edges.toFileWrite());

            } else {
                System.out.println("Ошибка сохранения модели");
            }
        }
    }

    private void writeInFile(File file, String data){
        try {
            if(file.createNewFile()){
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file);
                    fr.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double getX(int index, int type,  Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(0, (int) pointNumber - 1);
    }

    public double getY(int index, int type, Matrix vertices, Matrix edges){
        double pointNumber = edges.getElement(index, type);
        return vertices.getElement(1, (int) pointNumber - 1);
    }
}
