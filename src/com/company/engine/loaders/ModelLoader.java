package com.company.engine.loaders;

import com.company.engine.core.math.Matrix;
import com.company.engine.models.CompositeModel2D;
import com.company.engine.models.Model2D;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelLoader extends BaseLoader {

    private String modelPath;

    public ModelLoader(String modelPath) {
        this.modelPath = modelPath;
    }

    public CompositeModel2D load() {

        File modelStorage = new File(modelPath);

        File[] modelFileList = modelStorage.listFiles();

        Map<String, Model2D> model2DMap = new HashMap<>();

        for (File modelFile:
                modelFileList) {
            Model2D model2D = new Model2D();
            model2D.setName(modelFile.getName());
            model2D.setVertices(new Matrix(super.load(new File(modelFile.getAbsolutePath() + "Vertices.txt"))));
            model2D.setEdges(new Matrix(super.load(new File(modelFile.getAbsolutePath() + "Edges.txt"))));

            model2DMap.put(model2D.getName(), model2D);
        }

        CompositeModel2D compositeModel2D = new CompositeModel2D();

        compositeModel2D.setName(modelStorage.getName());
        compositeModel2D.setModel2DMap(model2DMap);

        return compositeModel2D;
    }

    public void save(File file, ArrayList<Model2D> model2DArrayList){
        if (!file.exists()){
            if(!file.mkdir()){
                System.out.println("Ошибка сохранения модели");
            }
        }
        for (Model2D model2D:
                model2DArrayList) {
            String path = file.getAbsolutePath() + "//" + model2D.getName();
            File dir = new File(path);
            if(dir.mkdir()){
                Matrix vertices = model2D.getVertices();
                Matrix edges = model2D.getEdges();

                File verticesFile = new File(path, "Vertices.txt");
                File edgesFile = new File(path, "Edges.txt");



                write(verticesFile, vertices.toFileWrite());
                write(edgesFile, edges.toFileWrite());

            } else {
                System.out.println("Ошибка сохранения модели");
            }
        }
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
