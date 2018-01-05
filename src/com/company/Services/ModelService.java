package com.company.Services;

import com.company.Math.Matrix;
import com.company.Math.Vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 26.12.2017.
 */
public class ModelService {
    public ModelService() {
    }

    public Matrix fileMatrixInit(String filePath){
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

        ArrayList<Integer> intBuffer = new ArrayList<>();
        Matrix matrix = new Matrix();
        for (int i = 0; i < stringList.size(); i++){
            String subString = stringList.get(i);
            String temp = new String();
            for(int j = 0; j < subString.length(); j++){
                if(subString.charAt(j) != ' '){
                    temp += subString.charAt(j);
                } else {
                    intBuffer.add(Integer.parseInt(temp));
                    temp = "";
                }
            }
            if(temp != ""){
                intBuffer.add(Integer.parseInt(temp));
            }

            if (i == 0){
                int row = intBuffer.get(0);
                int col = intBuffer.get(1);

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
}
