package com.company.Panels;

import com.company.Frames.ModelDrawer;
import com.company.Models.Model2D;
import com.company.Scenes.Scene2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nikita on 24.12.2017.
 */
public class ModelPanel2D extends JPanel{
    private Scene2D scene2D = new Scene2D(this, -30, 30, -15, 30);

    private boolean mousePressed = false;

    private boolean isLineDrawing = false;


    public ModelDrawer model2DFrame;


    public ModelPanel2D(ModelDrawer jFrame) {
        setLayout(new BorderLayout());
        model2DFrame = jFrame;
        ModelPanel2D self = this;
        setBorder(BorderFactory.createLineBorder(Color.black));
        self.setVisible(true);
        initMouseListeners();
        initKeyListeners();
    }

    public ModelPanel2D(String model, ModelDrawer jFrame) {
        setLayout(new BorderLayout());
        setFocusable(true);
        model2DFrame = jFrame;
        ModelPanel2D self = this;
        setBorder(BorderFactory.createLineBorder(Color.black));
        self.setVisible(true);
        initMouseListeners();
        initKeyListeners();


        String modelStoragePath = modelPath();
        File modelStorage = new File(modelStoragePath);

        File windMillModelsStorage = new File(modelStoragePath + model);

        File[] listFiles = windMillModelsStorage.listFiles();

        ArrayList<Model2D> modelList = new ArrayList<>();

        for (int i = 0; i < listFiles.length; i++){
            String modelName = listFiles[i].getName();
            File[] modelProperties = new File(listFiles[i].getAbsolutePath()).listFiles();
            Model2D model2D = new Model2D(listFiles[i].getAbsolutePath() + "\\", modelName, modelProperties);
            modelList.add(model2D);
        }

        for (Model2D model2D:
             modelList) {
            scene2D.addModel(model2D);
        }
       


        repaint();
    }

    private void initMouseListeners(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                requestFocusInWindow();
                if(e.getButton() == 1){
                    if (model2DFrame.mainMenu.isNewLineDrawing){
                        if(isLineDrawing){
                            scene2D.addPoint(e.getX(), e.getY());
                        } else {
                            scene2D.newLine(e.getX(), e.getY());
                            isLineDrawing = true;
                        }
                    } else {
                        scene2D.startDragging(e.getX(), e.getY());
                    }
                } else {
                    if(isLineDrawing){
                        isLineDrawing = false;
                        scene2D.endLineDrawing();
                    }
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                scene2D.stopDragging();
            }


        });

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                scene2D.onScroll(e.getX(), e.getY(), e.getWheelRotation());
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                scene2D.drag(e.getX(),e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e){
                String x = String.format("%.2f", scene2D.screenToWorldX(e.getX()));
                String y = String.format("%.2f", scene2D.screenToWorldY(e.getY()));
                if (model2DFrame.mainMenu.toolsMenu.drawToolFrame != null){
                    model2DFrame.mainMenu.toolsMenu.drawToolFrame.coordinatesLable.setText("X: " + x + "; Y: " + y + " ;");
                }
                if(isLineDrawing){
                    scene2D.dynamicLine(e.getX(), e.getY());
                }
            }

        });
    }

    private void initKeyListeners(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ESCAPE:
                        model2DFrame.mainMenu.isNewLineDrawing = false;
                        if(isLineDrawing){
                            isLineDrawing = false;
                            scene2D.endLineDrawing();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }


    public static String modelPath(){
        String path = ModelPanel2D.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.split("out")[0];
        path += "\\src\\com\\company\\Resources\\Models\\";
        return path;
    }


    private void switchMousePressed(){
        if(mousePressed){
            mousePressed = false;
        } else {
            mousePressed = true;
        }
    }



    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor( isFocusOwner() ? Color.RED : Color.ORANGE );
//        g.fillRect(0, 0, getWidth(), getHeight() );
//        g.setColor( Color.BLACK );
        scene2D.setResolution(this);
        scene2D.render(g);
    }
}
