package be.zwaldeck.killemall.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public final class AssetUtil {
    private static final AssetManager assetManager = new AssetManager();
    private static final InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    //set all asset loaders
    static {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(filePathResolver));
    }

    public static void unloadAsset(String path) {
        if(assetManager.isLoaded(path)) {
            assetManager.unload(path);
        }
    }

    public static float loadProgress() {
        return assetManager.getProgress();
    }

    public static int getNumberOfAssetsQueued() {
        return assetManager.getQueuedAssets();
    }

    public static boolean updateAssetsLoading() {
        return assetManager.update();
    }

    public static boolean isAssetsLoaded(String path) {
        return assetManager.isLoaded(path);
    }

    public static void loadMapAsset(String path) {
        if(!canWeLoad(path)) {
            return;
        }

        assetManager.load(path, TiledMap.class);

        //unit we implement the loading screen, block thread
        assetManager.finishLoading();
    }

    public static TiledMap getMapAsset(String path) {
        return assetManager.isLoaded(path) ? assetManager.get(path, TiledMap.class) : null;
    }

    public static void loadAtlas(String path) {
        if(!canWeLoad(path)) {
            return;
        }

        assetManager.load(path, TextureAtlas.class);

        //unit we implement the loading screen, block thread
        assetManager.finishLoading();
    }

    public static TextureAtlas getAtlas(String path) {
        return assetManager.isLoaded(path) ? assetManager.get(path, TextureAtlas.class) : null;
    }

    private static boolean canWeLoad(String path) {
        return path != null && !path.isEmpty() && !assetManager.isLoaded(path) &&  filePathResolver.resolve(path).exists();
    }
}
