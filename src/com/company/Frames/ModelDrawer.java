package com.company.Frames;

import com.company.Menu.MainMenu;

import javax.swing.*;

/**
 * Created by Nikita on 22.10.2017.
 */
public class ModelDrawer extends JFrame {

    public  MainMenu mainMenu;

    public ModelDrawer() {
        super("ModelDrawer");
        this.setBounds(200, 200, 800, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu = new MainMenu(this);
        this.setJMenuBar(mainMenu);
        this.setVisible(true);
    }
}
