package com.company.app.newmodel;

import com.company.engine.scenes.Scene2D;

import javax.swing.*;
import java.awt.*;

public class NewModelPanel extends JPanel{

    private JFrame parentFrame;

    private Scene2D scene2D;



    public NewModelPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setBorder(BorderFactory.createLineBorder(Color.black));

        scene2D = new Scene2D(this);

        this.setVisible(true);
    }
}
