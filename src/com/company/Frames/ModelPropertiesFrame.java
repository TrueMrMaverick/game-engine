package com.company.Frames;

import com.company.Math.Matrix;
import com.company.Math.Vector;
import com.company.Models.Model2D;
import com.company.Scenes.AnimationCreatorScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Nikita on 07.01.2018.
 */
public class ModelPropertiesFrame extends JFrame {

    private AnimationCreatorScene animationCreatorScene;
    private JPanel mainPanel;
    public ArrayList<JLabel> coordinatesLabelsList;
    private ArrayList<Model2D> model2DList;

    public ModelPropertiesFrame(AnimationCreatorScene animationCreatorScene){
        super("Model Properties");
        this.animationCreatorScene = animationCreatorScene;
        this.model2DList = animationCreatorScene.getModelList().get(0).getModelList();
        coordinatesLabelsList = new ArrayList<>();

        mainPanel = initMainPanel();

        this.refresh();

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    private JPanel initMainPanel() {

        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.setLayout(new GridLayout(0, 5));
        return jPanel;

    }

    public void refresh(){
        mainPanel.removeAll();
        for (Model2D model2D:
             model2DList) {
            Matrix vertices = model2D.vertices;
            Matrix edges = model2D.edges;

            for (int i = 0; i < vertices.getRowSize(); i++) {
                mainPanel.add(new Label(model2D.modelName));
                mainPanel.add(new Label("X: " + vertices.getElement(0, i) + "Y: " + vertices.getElement(1, i)));
                String pointEdges = "Current: " + (i + 1) + "; Edges: ";
                int currPoint = i + 1;
                ArrayList<Double> edgesList = new ArrayList<>();
                for (int j = 0; j < edges.getColSize(); j++) {
                    if (edges.getElement(j, 0) == currPoint){
                        pointEdges += edges.getElement(j, 1) + ", ";
                        edgesList.add(edges.getElement(j, 1));
                    } else  if (edges.getElement(j, 1) == currPoint){
                        pointEdges += edges.getElement(j, 0) + ", ";
                        edgesList.add(edges.getElement(j, 0));
                    }
                }
                mainPanel.add(new Label(pointEdges));
                mainPanel.add(initNewCoordButton(model2D, i));
                mainPanel.add(initNewEdgesButton(model2D, edgesList, i + 1));
            }

        }
    }

    private JButton initNewEdgesButton(Model2D model2D, ArrayList<Double> edgesList, int pointNumber) {
        ModelPropertiesFrame self = this;
        JButton button = new JButton("New edges");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame("Setting new coordinates");
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new GridLayout(0, 2));

                for (Double edge:
                     edgesList) {
                    Label label = new Label("Edge to " +  (int) Math.round(edge));
                    jPanel.add(label);
                    JButton changeEdge = new JButton("Change");
                    changeEdge.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            initChangeEdgeFrame(model2D, edge);
                        }
                    });
                    jPanel.add(changeEdge);
                }

                JButton newEdge = new JButton("Add edge");
                newEdge.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        initChangeEdgeFrame(model2D, 0.);
                    }
                });
                jPanel.add(newEdge);

                jFrame.add(jPanel);
                jFrame.pack();
                jFrame.setVisible(true);
            }

            private void initChangeEdgeFrame(Model2D model2D, Double edge) {
                JFrame jFrame = new JFrame("Set edge");
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new GridLayout(0, 2));
                JTextField setEdgeField = new JTextField();
                setEdgeField.setSize(30,10);
                jPanel.add(setEdgeField);

                JButton setButton = new JButton("Set");
                setButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(setEdgeField.getText() != null){
                            double[] vec = {pointNumber, Double.parseDouble(setEdgeField.getText())};
                            for (int i = 0; i < model2D.edges.getColSize(); i++) {
                                if (model2D.edges.getElement(i, 0) == pointNumber && model2D.edges.getElement(i, 1) == edge) {
                                    model2D.edges.removeRow(i);
                                } else if (model2D.edges.getElement(i, 1) == pointNumber && model2D.edges.getElement(i, 0) == edge) {
                                    model2D.edges.removeRow(i);
                                }
                            }
                            model2D.edges.addRow(new Vector(vec));
                        }
                        jFrame.dispose();
                        self.refresh();
                        self.revalidate();
                        self.repaint();
                    }
                });
                jPanel.add(setButton);

                jFrame.add(jPanel);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });

        return button;
    }

    private JButton initNewCoordButton(Model2D model2D, int pointNumber) {
        ModelPropertiesFrame self = this;
        JButton button = new JButton("New coordinates");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame("Setting new coordinates");
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new GridLayout(0,3));
                JTextField xField = new JTextField();
                xField.setSize(30, 10);
                jPanel.add(xField);
                JTextField yField = new JTextField();
                yField.setSize(30, 10);
                jPanel.add(yField);

                JButton setButton = new JButton("Set");
                setButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(xField.getText() != null){
                            double value = Double.parseDouble(xField.getText());
                            model2D.vertices.setElement(0, pointNumber, value);
                        }
                        if(yField.getText() != null){
                            double value = Double.parseDouble(yField.getText());
                            model2D.vertices.setElement(1, pointNumber, value);
                        }
                        self.refresh();
                        jFrame.dispose();
                        self.revalidate();
                        self.repaint();
                    }
                });
                jPanel.add(setButton);

                jFrame.add(jPanel);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });

        return button;
    }
}
