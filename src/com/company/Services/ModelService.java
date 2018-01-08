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
public class ModelService {
    public ModelService() {
    }

    public Matrix modelLoader(String filePath){
        File file = new File(filePath);
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

        ArrayList<Double> intBuffer = new ArrayList<>();
        Matrix matrix = new Matrix();
        for (int i = 0; i < stringList.size(); i++){
            String subString = stringList.get(i);
            String temp = new String();
            for(int j = 0; j < subString.length(); j++){
                if(subString.charAt(j) != ' '){
                    temp += subString.charAt(j);
                } else {
                    intBuffer.add(Double.parseDouble(temp));
                    temp = "";
                }
            }
            if(temp != ""){
                intBuffer.add(Double.parseDouble(temp));
            }

            if (i == 0){
                int row = (int) Math.round(intBuffer.get(0));
                int col = (int) Math.round(intBuffer.get(1));

                matrix = new Matrix(row, col);
            } else {
                List<Double> doubleBuffer = new ArrayList<>();
                for (int idx = 0; idx < intBuffer.size(); idx++){
                    doubleBuffer.add((double) intBuffer.get(idx));
                }
                Vector line = new Vector(doubleBuffer);
                matrix.setRow(i - 1, line);
            }
            intBuffer.clear();
        }

        return matrix;
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
}
