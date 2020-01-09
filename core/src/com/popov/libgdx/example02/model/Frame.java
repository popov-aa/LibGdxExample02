package com.popov.libgdx.example02.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Frame {
    private TextureRegion textureRegion;
    protected Sprite sprite;

    public Frame(TextureRegion textureRegion, float width, float height) {
        this.textureRegion = textureRegion;
        sprite = new Sprite(textureRegion);
        sprite.setSize(width, height);
    }

    public static Frame ByWidth(TextureRegion textureRegion, float width) {
        return new Frame(textureRegion, width, width / textureRegion.getRegionWidth() * textureRegion.getRegionHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }
}
