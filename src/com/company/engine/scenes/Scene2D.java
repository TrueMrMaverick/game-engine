package com.company.engine.scenes;

import com.company.engine.core.cameras.Camera2D;
import com.company.engine.models.CompositeModel2D;
import com.company.engine.renderers.Renderer;

import javax.swing.*;
import java.util.Map;

public class Scene2D extends Camera2D {

    private Renderer renderer;

    private Map<String, CompositeModel2D> compositeModel2DMap;

    public Scene2D(JPanel jPanel) {
        super(jPanel);
    }
}
