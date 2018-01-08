package com.company.Menu;

import com.company.Animation;
import com.company.Frames.AnimationToolsFrame;
import com.company.Frames.ModelDrawer;
import com.company.Frames.ModelPropertiesFrame;
import com.company.Panels.AnimationCreatorPanel;
import com.company.Scenes.AnimationCreatorScene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nikita on 06.01.2018.
 */
public class AnimationToolsMenu extends JMenu {
    public AnimationToolsFrame animationToolsFrame;
    public ModelPropertiesFrame modelPropertiesFrame;

    public AnimationToolsMenu(ModelDrawer jFrame, AnimationCreatorPanel animationCreatorPanel){
        super("Инструменты");
        AnimationToolsMenu self = this;

        JMenuItem animationTool = initAnimationTool(jFrame);
        self.add(animationTool);

        JMenuItem propertiesTool = initPropertiesTool(jFrame, animationCreatorPanel);
        self.add(propertiesTool);
    }

    private JMenuItem initPropertiesTool(ModelDrawer jFrame, AnimationCreatorPanel animationCreatorPanel) {
        JMenuItem propertiesTool = new JMenuItem("Properties Tool");

        propertiesTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelPropertiesFrame = animationCreatorPanel.initPropertiesFrame();
                jFrame.revalidate();
            }
        });

        return propertiesTool;
    }

    private JMenuItem initAnimationTool(ModelDrawer jFrame) {
        JMenuItem animationTool = new JMenuItem("Animation Tool");

        animationTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationToolsFrame = new AnimationToolsFrame(jFrame);
                jFrame.revalidate();
            }
        });

        return animationTool;
    }
}
