package com.popov.libgdx.example02.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.popov.libgdx.example02.Game;

public class Player extends GameObject {

    private static int WIDTH = 100;
    private static int SPEED = 200;
    private enum Movement {
        None,
        Left,
        Right
    }

    private Frame normalFrame;
    private Frame rightFrame;
    private Frame leftFrame;

    private Vector3 touchPos = new Vector3();
    private Movement movement = Movement.None;

    public Player(Game game) {
        super(game);
        normalFrame = Frame.ByWidth(game.getTextureAtlas().findRegion("player"), WIDTH);
        rightFrame = Frame.ByWidth(game.getTextureAtlas().findRegion("player_right"), WIDTH);
        leftFrame = Frame.ByWidth(game.getTextureAtlas().findRegion("player_left"), WIDTH);
        prepareFrame();
        rectangle.x = 800 / 2 - frame.getSprite().getWidth() / 2;
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
        } else if (rectangle.x > 800 - frame.getSprite().getWidth()) {
            rectangle.x = 800 - frame.getSprite().getWidth();
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
            game.getCamera().unproject(touchPos);
            if (touchPos.x < 400) {
                movement = Movement.Left;
            } else if (touchPos.x > 400) {
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
