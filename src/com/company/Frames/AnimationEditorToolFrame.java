package com.company.Frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikita on 09.01.2018.
 */
public class AnimationEditorToolFrame extends JFrame {

    private JPanel mainPanel;

    public AnimationEditorToolFrame() {
        super("Animation Editor");

        mainPanel = initMainPanel();


    }

    private JPanel initMainPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.setLayout(new GridLayout(0, 3));
        return jPanel;
    }
}
