package com.popov.libgdx.example02.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.popov.libgdx.example02.Game;

public class Player extends GameObject {

    private static int WIDTH = 64;

    private Frame normalFrame = new Frame(new Texture("player.png"), WIDTH);
    private Frame rightFrame = new Frame(new Texture("player_right.png"), WIDTH);
    private Frame leftFrame = new Frame(new Texture("player_left.png"), WIDTH);

    private Vector3 touchPos = new Vector3();

    public Player(Game game) {
        super(game);
        prepareFrame();
        rectangle.x = 800 / 2 - frame.getTexture().getWidth() / 2;
        rectangle.y = 20;
    }

    @Override
    public void handle(){
        super.handle();
        prepareFrame();
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Game.camera.unproject(touchPos);
            rectangle.x = Math.round(touchPos.x - frame.getWidth() / 2f);
        }  else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rectangle.x -= 200 * Gdx.graphics.getDeltaTime();
        }  else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rectangle.x += 200 * Gdx.graphics.getDeltaTime();
        }
        if (rectangle.x < 0) {
            rectangle.x = 0;
        } else if (rectangle.x > 800 - frame.getWidth()) {
            rectangle.x = 800 - frame.getWidth();
        }
    }

    @Override
    public void prepareFrame() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            frame = rightFrame;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            frame = leftFrame;
        } else {
            frame = normalFrame;
        }
    }
}
