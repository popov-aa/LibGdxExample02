package com.popov.libgdx.example02.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    public static String DEFAULT_TEXTURE_ATLAS = "default.atlas";

    private AssetManager assetManager;

    public Assets() {
        assetManager = new AssetManager();
        assetManager.load(DEFAULT_TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
