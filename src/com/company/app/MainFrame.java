package com.company.app;

import javax.swing.*;
import java.util.List;

/**
 * Created by Nikita on 22.10.2017.
 */
public class MainFrame extends JFrame {

    public MainMenu mainMenu;
    public List<JPanel> jPanelList;

    public MainFrame() {
        super("ModelDrawer");
        this.setBounds(200, 200, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu = new MainMenu(this);
        this.setJMenuBar(mainMenu);

        this.setVisible(true);
    }

}
