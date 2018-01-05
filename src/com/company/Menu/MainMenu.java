package com.company.Menu;

import com.company.Frames.ModelDrawer;
import com.company.Panels.ModelPanel2D;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nikita on 06.11.2017.
 */
public class MainMenu extends JMenuBar {

    public MainMenu(ModelDrawer parentFrame) {
        createMainMenu(parentFrame);
    }
    private ArrayList<ModelPanel2D> modelPanelsStack2D = new ArrayList<>();
    private ModelPanel2D currPanel;
    public ToolsMenu toolsMenu;
    public ModelDrawer parentFrame;
    public boolean isNewLineDrawing = false;




    public void createMainMenu(ModelDrawer jFrame){
        MainMenu self = this;
        this.parentFrame = jFrame;
        JMenu mainMenu = new JMenu("Меню");

        JMenuItem newDrawingPanel = initNewDrawingPanel();
        mainMenu.add(newDrawingPanel);

        JMenuItem saveModelButton = initSaveModel();
        mainMenu.add(saveModelButton);

        toolsMenu = new ToolsMenu(jFrame);



        this.add(mainMenu);
        this.add(toolsMenu);
        this.setVisible(true);
    }

    private JMenuItem initSaveModel() {
        MainMenu self = this;
        JMenuItem jMenuItem = new JMenuItem("Save Model");

        JFileChooser fileChooser = new JFileChooser();

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "*.*");
                fileChooser.setDialogTitle("Сохрание модели");
                fileChooser.setFileFilter(filter);
                if ( fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
//                    try ( FileWriter fw = new FileWriter(fileChooser.getSelectedFile()) ) {
//                        fw.write("Blah blah blah...");
//                    }
//                    catch ( IOException e ) {
//                        System.out.println("Всё погибло!");
//                    }
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
                self.clearPanels();
                ModelPanel2D modelPanel2D = new ModelPanel2D(parentFrame);
                modelPanelsStack2D.add(modelPanel2D);
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
                modelPanelsStack2D) {
            modelPanel2D.setVisible(false);
        }
        modelPanelsStack2D.clear();


    }

}
