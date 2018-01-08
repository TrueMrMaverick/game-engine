package com.company.Menu;

import com.company.Frames.ModelDrawer;
import com.company.Models.Model2D;
import com.company.Panels.AnimationCreatorPanel;
import com.company.Panels.ModelPanel2D;
import com.company.Services.ModelService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nikita on 06.11.2017.
 */
public class MainMenu extends JMenuBar {

    public MainMenu(ModelDrawer parentFrame) {
        createMainMenu(parentFrame);
    }
    private ArrayList<ModelPanel2D> modelPanels2DStack = new ArrayList<>();
    private ArrayList<AnimationCreatorPanel> animationCreatorPanelsStack = new ArrayList<>();
    private ModelPanel2D currPanel;
    public DrawingToolsMenu drawingDrawingToolsMenu;
    public AnimationToolsMenu animationToolsMenu;
    public ModelDrawer parentFrame;
    public boolean isNewLineDrawing = false;
    private ModelService modelService = new ModelService();
    private File prevDirInFileChooser = null;




    public void createMainMenu(ModelDrawer jFrame){
        MainMenu self = this;
        this.parentFrame = jFrame;
        JMenu mainMenu = new JMenu("Меню");

        JMenuItem newDrawingPanel = initNewDrawingPanel();
        mainMenu.add(newDrawingPanel);

        JMenuItem newAnimationPanel = initAnimationPanel();
        mainMenu.add(newAnimationPanel);

        JMenuItem saveModelButton = initSaveModel();
        mainMenu.add(saveModelButton);

        JMenuItem loadModelButton = initModelLoader();
        mainMenu.add(loadModelButton);





        this.add(mainMenu);

        this.setVisible(true);
    }

    private JMenuItem initAnimationPanel() {
        MainMenu self = this;
        JMenuItem jMenuItem = new JMenuItem("New Animation");



        JFileChooser fileChooser = new JFileChooser();
        if(prevDirInFileChooser != null){
            fileChooser.setCurrentDirectory(prevDirInFileChooser);
        } else {
            fileChooser.setCurrentDirectory(new File("D:\\Programs\\Java\\UNIVERAS_LABAS\\game-engine\\src\\com\\company\\Resources\\Models"));
        }
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Load Model");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (drawingDrawingToolsMenu != null && drawingDrawingToolsMenu.drawToolFrame != null){
                    drawingDrawingToolsMenu.drawToolFrame.dispose();
                    isNewLineDrawing = false;
                    modelPanels2DStack.get(0).setLineDrawing(false);
                }

                if(drawingDrawingToolsMenu != null){
                    self.remove(drawingDrawingToolsMenu);
                    parentFrame.repaint();
                }
                self.clearPanels();


                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    self.clearPanels();
                    String fileName = fileChooser.getSelectedFile().getName();
                    AnimationCreatorPanel animationCreatorPanel = new AnimationCreatorPanel(parentFrame, fileName);
                    animationToolsMenu = new AnimationToolsMenu(parentFrame, animationCreatorPanel);
                    self.add(animationToolsMenu);
                    animationCreatorPanelsStack.add(animationCreatorPanel);
                    parentFrame.add(animationCreatorPanel);
                    currPanel = animationCreatorPanel;
                    animationCreatorPanel.setVisible(true);
                    parentFrame.revalidate();
                }


            }
        });
        return jMenuItem;
    }

    private JMenuItem initModelLoader() {
        MainMenu self = this;
        JMenuItem jMenuItem = new JMenuItem("Load Model");

        JFileChooser fileChooser = new JFileChooser();
        if(prevDirInFileChooser != null){
            fileChooser.setCurrentDirectory(prevDirInFileChooser);
        } else {
            fileChooser.setCurrentDirectory(new File("D:\\Programs\\Java\\UNIVERAS_LABAS\\game-engine\\src\\com\\company\\Resources\\Models"));
        }
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Load Model");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    self.clearPanels();
                    String fileName = fileChooser.getSelectedFile().getName();
                    ModelPanel2D modelPanel2D = new ModelPanel2D(parentFrame, fileName);
                    modelPanels2DStack.add(modelPanel2D);
                    parentFrame.add(modelPanel2D);
                    currPanel = modelPanel2D;
                    modelPanel2D.setVisible(true);
                    parentFrame.revalidate();
                }
            }
        });

        return jMenuItem;
    }

    private JMenuItem initSaveModel() {
        MainMenu self = this;
        JMenuItem jMenuItem = new JMenuItem("Save Model");

        JFileChooser fileChooser = new JFileChooser();
        if(prevDirInFileChooser != null){
            fileChooser.setCurrentDirectory(prevDirInFileChooser);
        } else {
            fileChooser.setCurrentDirectory(new File("D:\\Programs\\Java\\UNIVERAS_LABAS\\game-engine\\src\\com\\company\\Resources\\Models"));
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Save Model");



        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "*.*");
                //fileChooser.setFileFilter(filter);
                if ( fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    ArrayList<Model2D> model2D = modelPanels2DStack.get(0).getModel();
                    modelService.modelSaver(fileChooser.getSelectedFile(), model2D);
                }
            }
        });


        return  jMenuItem;
    }

    private JMenuItem initNewDrawingPanel() {
        MainMenu self = this;
        JMenuItem jMenuItem = new JMenuItem("New Panel");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(animationToolsMenu != null){
                    self.remove(animationToolsMenu);
                    parentFrame.repaint();
                }
                self.clearPanels();
                drawingDrawingToolsMenu = new DrawingToolsMenu(parentFrame);
                self.add(drawingDrawingToolsMenu);
                parentFrame.repaint();
                ModelPanel2D modelPanel2D = new ModelPanel2D(parentFrame);
                modelPanels2DStack.add(modelPanel2D);
                //self.add(options);
                parentFrame.add(modelPanel2D);
                currPanel = modelPanel2D;
                modelPanel2D.setVisible(true);
                parentFrame.revalidate();
            }
        });

        return jMenuItem;
    }


    private void clearPanels(){

        for (ModelPanel2D modelPanel2D :
                modelPanels2DStack) {
            modelPanel2D.setVisible(false);
        }
        modelPanels2DStack.clear();


        for (AnimationCreatorPanel animationCreatorPanel :
                animationCreatorPanelsStack) {
            animationCreatorPanel.setVisible(false);
        }
        animationCreatorPanelsStack.clear();


    }



}
