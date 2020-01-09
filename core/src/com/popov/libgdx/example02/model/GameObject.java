package com.popov.libgdx.example02.model;

import com.badlogic.gdx.math.Rectangle;
import com.popov.libgdx.example02.Game;

public abstract class GameObject {
    protected Rectangle rectangle = new Rectangle();
    protected Game game;
    protected Frame frame;

    public GameObject(Game game) {
        this.game = game;
    }

    public void handle() {
        prepareFrame();
        rectangle.width = frame.getSprite().getWidth();
        rectangle.height = frame.getSprite().getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Frame getFrame() {
        return frame;
    }

    public void prepareFrame() {
    }
}
