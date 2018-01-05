package com.company.Menu;

import com.company.Frames.DrawingToolsFrame;
import com.company.Frames.ModelDrawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nikita on 02.01.2018.
 */
public class ToolsMenu extends JMenu {

    public DrawingToolsFrame drawToolFrame;

    public ToolsMenu(ModelDrawer jFrame) {
        super("Инструменты");
        ToolsMenu self = this;

        JMenuItem drawTool = initDrawTool(jFrame);
        self.add(drawTool);
    }

    private JMenuItem initDrawTool(ModelDrawer jFrame) {
        JMenuItem drawTool = new JMenuItem("Draw tool");
        ToolsMenu self = this;

        drawTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawToolFrame = new DrawingToolsFrame(jFrame);
                jFrame.revalidate();
            }
        });

        return drawTool;
    }
}
