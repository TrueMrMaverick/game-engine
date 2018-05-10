package com.company.engine.loaders;

import com.company.engine.core.math.Matrix;
import com.company.engine.core.math.Vector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый лоадер из файлов.
 */
public abstract class BaseLoader {

    /**
     * Базовый метод выгрузки матриц из файлов.
     * @param file - файл.
     * @return матрица.
     */
    protected Matrix load(File file){
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
            if(!temp.equals("")){
                intBuffer.add(Double.parseDouble(temp));
            }

            List<Double> doubleBuffer = new ArrayList<>();
            for (int idx = 0; idx < intBuffer.size(); idx++){
                doubleBuffer.add((double) intBuffer.get(idx));
            }
            Vector line = new Vector(doubleBuffer);
            matrix.addRow(line);

            intBuffer.clear();
        }

        return matrix;
    }

    /**
     * Базовый метод записи строки в файл.
     * @param file - файл.
     * @param data - строка.
     */
    protected void write(File file, String data){
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
