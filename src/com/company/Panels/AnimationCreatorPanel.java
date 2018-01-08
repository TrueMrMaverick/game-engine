package com.company.Panels;

import com.company.Frames.ModelDrawer;
import com.company.Frames.ModelPropertiesFrame;
import com.company.Models.Model2D;
import com.company.Scenes.AnimationCreatorScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nikita on 06.01.2018.
 */
public class AnimationCreatorPanel extends ModelPanel2D {

    public ModelPropertiesFrame propertiesFrame;

    private AnimationCreatorScene animationCreatorScene = new AnimationCreatorScene(this, -30, 30, -15, 30);



    public AnimationCreatorPanel(ModelDrawer jFrame){
        setLayout(new BorderLayout());
        model2DFrame = jFrame;
        ModelPanel2D self = this;
        setBorder(BorderFactory.createLineBorder(Color.black));
        self.setVisible(true);
        animationCreatorScene.hasAxes(true);
        initMouseListeners();

    }

    public AnimationCreatorPanel(ModelDrawer jFrame, String model){
        this(jFrame);

        String modelStoragePath = modelPath();

        File modelsStorage = new File(modelStoragePath + model);

        File[] listFiles = modelsStorage.listFiles();

        ArrayList<Model2D> modelList = new ArrayList<>();

        for (int i = 0; i < listFiles.length; i++){
            String modelName = listFiles[i].getName();
            File[] modelProperties = new File(listFiles[i].getAbsolutePath()).listFiles();
            Model2D model2D = new Model2D(listFiles[i].getAbsolutePath() + "\\", modelName, modelProperties);
            modelList.add(model2D);
        }

        for (Model2D model2D:
                modelList) {
            animationCreatorScene.addModel(model2D);
        }


    }

    private void initMouseListeners(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                animationCreatorScene.startDragging(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                animationCreatorScene.stopDragging();
            }
        });

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                animationCreatorScene.onScroll(e.getX(), e.getY(), e.getWheelRotation());
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                animationCreatorScene.drag(e.getX(),e.getY());
                repaint();
            }

        });

    }

    public ModelPropertiesFrame initPropertiesFrame(){
        propertiesFrame = new ModelPropertiesFrame(animationCreatorScene);
        return propertiesFrame;
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor( isFocusOwner() ? Color.RED : Color.ORANGE );
//        g.fillRect(0, 0, getWidth(), getHeight() );
//        g.setColor( Color.BLACK );
        animationCreatorScene.setResolution(this);
        animationCreatorScene.render((Graphics2D) g);
    }

}
