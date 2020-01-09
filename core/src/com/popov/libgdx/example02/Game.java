package com.popov.libgdx.example02;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.popov.libgdx.example02.model.Drop;
import com.popov.libgdx.example02.model.Frame;
import com.popov.libgdx.example02.model.GameObject;
import com.popov.libgdx.example02.model.Player;
import com.popov.libgdx.example02.utils.Assets;

public class Game extends ApplicationAdapter {

	private Assets assets;
	private TextureAtlas textureAtlas;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Frame background;
	public Player player;

	public Sound dropSound;
	public Music music;

	public Array<Drop> drops;
	long lastDropTime = 0;

	@Override
	public void create () {
		assets = new Assets();
		textureAtlas = assets.getAssetManager().get(Assets.DEFAULT_TEXTURE_ATLAS, TextureAtlas.class);
		background = Frame.ByWidth(textureAtlas.findRegion("background"), 800);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();

		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		music.setLooping(true);
		music.play();

		player = new Player(this);
		drops = new Array<>();
		spawnDrop();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background.getSprite(), 0, 0, 800, 480);
		drawGameObject(player);
		for (Drop drop: drops) {
			drawGameObject(drop);
		}
		batch.end();

		player.handle();
		for (Drop drop: drops) {
			drop.handle();
		}

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
			spawnDrop();
		}
	}
	
	@Override
	public void dispose () {
		super.dispose();
		assets.dispose();
		batch.dispose();
		dropSound.dispose();
		music.dispose();
	}

	private void drawGameObject(GameObject gameObject) {
		Rectangle rectangle = gameObject.getRectangle();
		batch.draw(
				gameObject.getFrame().getSprite(),
				rectangle.getX(),
				rectangle.getY(),
				rectangle.getWidth(),
				rectangle.getHeight());
	}

	private void spawnDrop() {
		Drop drop = new Drop(this);
		drops.add(drop);
		lastDropTime = TimeUtils.nanoTime();
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
