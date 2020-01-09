package com.popov.libgdx.example02.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.popov.libgdx.example02.Game;

public class Drop extends GameObject {

    public Drop(Game game) {
        super(game);
        frame = Frame.ByWidth(game.getTextureAtlas().findRegion("drop"), 64);
        rectangle.x = MathUtils.random(0, 800 - frame.getSprite().getWidth());
        rectangle.y = 480;
    }

    @Override
    public void handle() {
        super.handle();
        rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
        if (rectangle.y + frame.getSprite().getHeight() < 0) {
            game.drops.removeValue(this, true);
        }
        if (rectangle.overlaps(game.player.getRectangle())) {
            game.dropSound.play();
            game.drops.removeValue(this, true);
        }
    }
}
