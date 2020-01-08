package com.popov.libgdx.example02.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.popov.libgdx.example02.Game;

public class Drop extends GameObject {

    public Drop(Game game) {
        super(game);
        frame = new Frame(new Texture("drop.png"), 32);
        rectangle.x = MathUtils.random(0, 800 - frame.getWidth());
        rectangle.y = 480;
    }

    @Override
    public void handle() {
        super.handle();
        rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
        if (rectangle.y + frame.getHeight() < 0) {
            game.drops.removeValue(this, true);
        }
        if (rectangle.overlaps(game.player.getRectangle())) {
            game.dropSound.play();
            game.drops.removeValue(this, true);
        }
    }
}
