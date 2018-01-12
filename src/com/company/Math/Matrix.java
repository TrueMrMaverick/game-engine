package com.company.Math;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 23.12.2017.
 */
public class Matrix {
    private int rowSize;
    private int colSize;
    private List<Vector> cells;

    public Matrix() {
        rowSize = 0;
        colSize = 0;
        cells = new ArrayList<>();
    }

    public Matrix(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.cells = new ArrayList<>();

        for (int i = 0; i < colSize; i++) {
            cells.add(new Vector(rowSize));
        }
    }

    public Matrix(int rowSize, int colSize, List<Double> list) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.cells = new ArrayList<>();

        if (list.size() == rowSize + colSize){
            int k = 0;
            for (int i = 0; i < colSize; i++) {
                cells.add(new Vector(rowSize));
                for (int j = 0; j < rowSize; j++){
                    cells.get(i).set(j, list.get(k));
                    k++;
                }
            }
        } else {
            cells = new ArrayList<>();
        }
    }

    public Matrix(int rowSize, int colSize, double[] list) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.cells = new ArrayList<>();

        if (list.length == rowSize * colSize){
            int k = 0;
            for (int i = 0; i < colSize; i++) {
                cells.add(new Vector(rowSize));
                for (int j = 0; j < rowSize; j++){
                    cells.get(i).set(j, list[k]);
                    k++;
                }
            }
        } else {
            cells = new ArrayList<>();
        }
    }

    public Matrix(Matrix matrix){
        rowSize = matrix.rowSize;
        colSize = matrix.colSize;
        cells = new ArrayList<>();

        for (int i = 0; i < colSize; i++){
            cells.add(matrix.getRow(i));
        }
    }

    public void setCol(int index, Vector vector) {
        for (int i = 0; i < colSize; i++){
            cells.get(i).set(index, vector.get(i));
        }
    }

    public Vector getCol(int index){

        if(index > rowSize){
            return null;
        }

        Vector result = new Vector(colSize);

        for(int i = 0; i < colSize; i++){
            result.set(i, cells.get(i).get(index));
        }

        return result;
    }

    public void addCol(Vector vector){
        rowSize++;
        if(colSize == 0){
            colSize = vector.getSize();
            for (int i = 0; i < vector.getSize(); i++){
                List<Double> temp = new ArrayList<>();
                temp.add(vector.get(i));
                cells.add(new Vector(temp));
                temp.clear();
            }
        } else {
            for (int i = 0; i < colSize; i++) {
                cells.get(i).add(vector.get(i));
            }
        }

    }

    public void addRow(Vector vector){
        colSize++;
        if(rowSize == 0){
            rowSize = vector.getSize();
            cells.add(vector);
        } else {
            if(rowSize == vector.getSize()){
                cells.add(vector);
            }
        }
    }



    public void setRow(int index, Vector vector){
        cells.set(index, vector);
    }

    public Vector getRow(int index){

        if(index > colSize){
            return null;
        }

        Vector result = new Vector(rowSize);

        for(int i = 0; i < rowSize; i++){
            result.set(i, cells.get(index).get(i));
        }

        return result;
    }



    public double getElement(int i, int j){

        if(i > colSize){
            return 0.;
        }

        if(j > rowSize){
            return 0.;
        }

        return cells.get(i).get(j);
    }

    public void setElement(int i, int j, double element){
        cells.get(i).set(j, element);
    }

    public Matrix clone(){
        Matrix clone = new Matrix(rowSize, colSize);
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                clone.setElement(i, j, this.getElement(i,j));
            }
        }
        return clone;
    }

    public Matrix summ(Matrix M){
        Matrix result = this.clone();
        if (rowSize == M.rowSize && colSize == M.colSize){
            for (int i = 0; i < colSize; i++)
                for (int j = 0; j < rowSize; j++){
                    result.setElement(i, j,this.getElement(i,j) + M.getElement(i,j));
                }
        }

        return result;
    }

    public Matrix sub(Matrix M){
        Matrix result = this.clone();
        if (rowSize == M.rowSize && colSize == M.colSize){
            for (int i = 0; i < colSize; i++)
                for (int j = 0; j < rowSize; j++){
                    result.setElement(i, j,this.getElement(i,j) - M.getElement(i,j));
                }
        }

        return result;
    }

    public Matrix mult(Matrix matrix){

        if(rowSize == matrix.colSize){
            Matrix result = new Matrix(matrix.rowSize, colSize);
            double value;
            for (int i = 0; i < colSize; i++){
                for (int j = 0; j < matrix.rowSize; j++) {
                    value = result.getElement(i, j);
                    for (int k = 0; k < rowSize; k++){
                        value += this.getElement(i, k) * matrix.getElement(k, j);
                    }
                    result.setElement(i, j, value);
                }
            }
            //result.print("Mult log:");
            return result;
        }

        System.out.print("Mult log: colSize != matrix.rowSize");
        return null;
    }

    public void print(){

        System.out.println("Matrix: ");

        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                System.out.print(this.getElement(i, j) + " ");
            }
            System.out.println();
        }

    }

    public void print(String msg){

        System.out.println(msg);

        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                System.out.print(this.getElement(i, j) + " ");
            }
            System.out.println();
        }

    }

    public Vector getLastCol(){
        Vector vector = new Vector();
        for (int i = 0; i < colSize; i++) {
            vector.add(this.getElement(i, rowSize - 1));
        }
        return vector;
    }


    public String toString(){
        StringBuilder matrixString = new StringBuilder();

        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                matrixString.append(this.getElement(i, j));
                if (j < rowSize - 1){
                    matrixString.append(" ");
                }
            }
            matrixString.append("\n");
        }

        return matrixString.toString();
    }

    public String toFileWrite(){
        StringBuilder matrixString = new StringBuilder();
        matrixString.append(rowSize + " " + colSize + "\n");
        matrixString.append(this.toString());
        return matrixString.toString();
    }

    public  Vector getLastRow(){
        return cells.get(colSize - 1);
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public List<Vector> getCells() {
        return cells;
    }

    public void setCells(List<Vector> cells) {
        this.cells = cells;
    }

    public void removeCol(int i) {
        for (int j = 0; j < colSize; j++) {
            cells.get(j).remove(i);
        }
        rowSize--;
    }

    public void removeRow(int i){
        cells.remove(i);
        colSize--;
    }
}
