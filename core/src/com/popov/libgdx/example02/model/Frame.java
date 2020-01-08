package com.popov.libgdx.example02.model;

import com.badlogic.gdx.graphics.Texture;

public class Frame {
    private float width, height;
    private Texture texture;

    public Frame(Texture texture, float width) {
        this.texture = texture;
        this.width = width;
        height = texture.getHeight() * width / texture.getWidth();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

}
