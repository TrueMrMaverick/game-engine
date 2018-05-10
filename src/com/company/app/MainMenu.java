package com.company.app;

import com.company.app.newmodel.NewModelPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nikita on 06.11.2017.
 */
public class MainMenu extends JMenuBar {

    private MainFrame parentFrame;

    public MainMenu(MainFrame parentFrame) {
        this.parentFrame = parentFrame;

        JMenu mainMenu = new JMenu("Menu");

        JMenuItem newModelMenuItem = initNewModelPanel();
        mainMenu.add(newModelMenuItem);

        this.add(mainMenu);

        this.setVisible(true);

    }

    private JMenuItem initNewModelPanel() {
        MainMenu self = this;

        JMenuItem jMenuItem = new JMenuItem("New Model");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewModelPanel newModelPanel = new NewModelPanel(parentFrame);
                newModelPanel.setVisible(true);
                parentFrame.jPanelList.clear();
                parentFrame.jPanelList.add(newModelPanel);
            }
        });

        return jMenuItem;
    }


}
