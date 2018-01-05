package com.company.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nikita on 03.01.2018.
 */
public class DrawingToolsFrame extends JFrame {

    public JLabel coordinatesLable;
    public JButton lineButton;

    public DrawingToolsFrame(ModelDrawer jFrame) {
        super("Drawing Tools");
        this.setBounds(100, 100, 100, 300);

        JPanel toolsPanel = initToolsPanel(jFrame);
        this.add(toolsPanel);

        coordinatesLable = new JLabel();
        coordinatesLable.setText("Coordinates");
        toolsPanel.add(coordinatesLable);

        lineButton = new JButton("New Line");
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.mainMenu.isNewLineDrawing = true;
            }
        });
        toolsPanel.add(lineButton);

        JButton button = new JButton("Shit");
        toolsPanel.add(button);

        JButton button1 = new JButton("Shit");
        toolsPanel.add(button1);


        this.setVisible(true);
    }

    private JPanel initToolsPanel(JFrame jFrame) {
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.setSize(50, 150);
        jPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.setLayout(new GridLayout(3, 2));
        return jPanel;
    }
}
