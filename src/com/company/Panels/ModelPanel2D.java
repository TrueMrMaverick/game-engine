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

    protected boolean leftMouseButtonPressed = false;
    protected boolean rightMouseButtonPressed = false;


    private boolean isLineDrawing = false;


    public ModelDrawer model2DFrame;


    public ModelPanel2D(){
    }

    public ModelPanel2D(ModelDrawer jFrame) {
        setLayout(new BorderLayout());
        model2DFrame = jFrame;
        ModelPanel2D self = this;
        setBorder(BorderFactory.createLineBorder(Color.black));
        self.setVisible(true);
        scene2D.hasAxes(true);
        initMouseListeners();
        initKeyListeners();
    }

    public ModelPanel2D(ModelDrawer jFrame, String model) {
        this(jFrame);


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

                switch (e.getButton()){
                    case 1:
                        leftMouseButtonPressed = true;
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
                        break;
                    case 3:
                        rightMouseButtonPressed = true;
                        if(isLineDrawing){
                            isLineDrawing = false;
                            scene2D.endLineDrawing();
                        }
                        scene2D.startDragging(e.getX(), e.getY());
                        break;
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch (e.getButton()){
                    case 1:
                        leftMouseButtonPressed = false;
                        break;
                    case 3:
                        rightMouseButtonPressed = false;
                        break;
                }
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
                if (model2DFrame.mainMenu.isNewLineDrawing) {
                    if(rightMouseButtonPressed){
                        scene2D.drag(e.getX(),e.getY());
                    }
                } else {
                    scene2D.drag(e.getX(),e.getY());
                }

            }

            @Override
            public void mouseMoved(MouseEvent e){
                String x = String.format("%.2f", scene2D.screenToWorldX(e.getX()));
                String y = String.format("%.2f", scene2D.screenToWorldY(e.getY()));
                if (model2DFrame.mainMenu.drawingDrawingToolsMenu.drawToolFrame != null){
                    model2DFrame.mainMenu.drawingDrawingToolsMenu.drawToolFrame.coordinatesLabel.setText("X: " + x + "; Y: " + y + " ;");
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


    protected static String modelPath(){
        String path = ModelPanel2D.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.split("out")[0];
        path += "\\src\\com\\company\\Resources\\Models\\";
        return path;
    }


    public ArrayList<Model2D> getModel(){
        return scene2D.getModelList();
    }

    public void setLineDrawing(boolean lineDrawing) {
        isLineDrawing = lineDrawing;
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
        scene2D.render((Graphics2D) g);
    }
}
