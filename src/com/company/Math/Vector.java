package com.company.Math;

import jdk.internal.instrumentation.TypeMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 23.12.2017.
 */
public class Vector {

    private int size;
    private List<Double> vector;


    public Vector(){
        this.size = 0;
        this.vector = new ArrayList<>();
    }

    public Vector(int size){
        this.size = size;
        this.vector = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            vector.add(0.);
        }
    }

    public Vector(double[] vector){
        this.size = vector.length;
        this.vector = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            this.vector.add(vector[i]);
        }
    }

    public Vector(Vector vector){
        this.size = vector.size;
        this.vector = new ArrayList<>(vector.vector);
    }

    public Vector(List<Double> vector) {
        this.size = vector.size();
        this.vector = new ArrayList<>(vector);
    }

//    public Vector(List<Integer> vector) {
//        this.size = vector.size();
//        List<Double> temp = new ArrayList<>();
//        for (int i = 0; i < vector.size(); i++){
//            temp.add((double) vector.get(i));
//        }
//        this.vector = new ArrayList<>(temp);
//    }


    public double get(int i){
        return vector.get(i);
    }

    public void set(int i, double element){
        vector.set(i, element);
    }

    public Vector summ(Vector vector){


        if(size == vector.size){
            Vector result = new Vector(size);
            for (int i = 0; i < size; i++) {
                result.set(i, this.vector.get(i) + vector.vector.get(i));
            }
        }
        return this;
    }

    public Vector sub(Vector vector){
        if(size == vector.size){
            Vector result = new Vector(size);
            for (int i = 0; i < size; i++) {
                result.set(i, this.vector.get(i) - vector.vector.get(i));
            }
        }
        return this;
    }

    public double scolarMult(Vector vector){
        if (size != vector.size){
            return 0.;
        }

        double result = 0;

        for (int i = 0; i < size; i++) {
            result += this.vector.get(i) * vector.vector.get(i);
        }

        return result;
    }

    public void random(int min, int max){
        for (int i = 0; i < size; i++) {
            double random = min + (int) (Math.random() * max);
            this.set(i, random);
        }
    }

    public Vector vectorProduct3D(Vector vec){
        Vector result = new Vector();
        result.add(vector.get(2) * vec.get(3) - vector.get(3) * vec.get(2));
        result.add(vector.get(3) * vec.get(1) - vector.get(1) * vec.get(3));
        result.add(vector.get(1) * vec.get(2) - vector.get(2) * vec.get(1));
        return  result;
    }

    public void add(double value){
        size++;
        vector.add(value);
    }

    public double norm() {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += Math.pow(vector.get(i), 2);
        }
        return  Math.sqrt(result);
    }

    public Vector constMult(double value){
        Vector result = this.clone();
        for (double element:
             result.vector) {
            element *= value;
        }
        return result;
    }

    public Vector clone(){
        Vector clone = new Vector();
        clone.size = size;
        clone.vector = new ArrayList<>(vector);
        return clone;
    }

    public void remove(int i){
        vector.remove(i);
        size--;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Double> getVector() {
        return vector;
    }

    public void setVector(List<Double> vector) {
        this.vector = vector;
    }
}
