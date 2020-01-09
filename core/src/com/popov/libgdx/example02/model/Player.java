package com.popov.libgdx.example02.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.popov.libgdx.example02.Game;

public class Player extends GameObject {

    private static int WIDTH = 64;
    private static int SPEED = 300;
    private enum Movement {
        None,
        Left,
        Right
    }

    private Frame normalFrame = new Frame(new Texture("player.png"), WIDTH);
    private Frame rightFrame = new Frame(new Texture("player_right.png"), WIDTH);
    private Frame leftFrame = new Frame(new Texture("player_left.png"), WIDTH);

    private Vector3 touchPos = new Vector3();
    private Movement movement = Movement.None;

    public Player(Game game) {
        super(game);
        prepareFrame();
        rectangle.x = 800 / 2 - frame.getTexture().getWidth() / 2;
        rectangle.y = 20;
    }

    @Override
    public void handle(){
        super.handle();

        updateMovement();
        prepareFrame();

        int deltaX = 0;
        switch (movement) {
            case Left: deltaX -= SPEED; break;
            case Right: deltaX += SPEED; break;
        }
        rectangle.x += deltaX * Gdx.graphics.getDeltaTime();
        if (rectangle.x < 0) {
            rectangle.x = 0;
        } else if (rectangle.x > 800 - frame.getWidth()) {
            rectangle.x = 800 - frame.getWidth();
        }
    }

    @Override
    public void prepareFrame() {
        switch (movement) {
            case None: frame = normalFrame; break;
            case Left: frame = leftFrame; break;
            case Right: frame = rightFrame; break;
        }
    }

    private void updateMovement() {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Game.camera.unproject(touchPos);
            if (touchPos.x < rectangle.x) {
                movement = Movement.Left;
            } else if (touchPos.x > rectangle.x + frame.getWidth()) {
                movement = Movement.Right;
            } else {
                movement = Movement.None;
            }
        }  else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movement = Movement.Left;
        }  else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movement = Movement.Right;
        } else {
            movement = Movement.None;
        }
    }
}
