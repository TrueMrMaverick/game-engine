package com.company.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nikita on 03.01.2018.
 */
public class DrawingToolsFrame extends JFrame {

    public JLabel coordinatesLabel;
    public JButton lineButton;

    public DrawingToolsFrame(ModelDrawer jFrame) {
        super("Drawing Tools");

        JPanel toolsPanel = initToolsPanel(jFrame);
        this.add(toolsPanel);

        coordinatesLabel = new JLabel();
        coordinatesLabel.setText("Coordinates");
        toolsPanel.add(coordinatesLabel);

        lineButton = new JButton("New Line");
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.mainMenu.isNewLineDrawing = true;
            }
        });
        toolsPanel.add(lineButton);
        this.pack();
        this.setVisible(true);
    }

    private JPanel initToolsPanel(JFrame jFrame) {
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.setLayout(new GridLayout(3, 2));
        return jPanel;
    }
}
