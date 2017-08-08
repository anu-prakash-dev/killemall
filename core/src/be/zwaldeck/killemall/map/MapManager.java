package be.zwaldeck.killemall.map;

import be.zwaldeck.killemall.entity.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    private Map currentMap;
    private OrthographicCamera camera;

    public MapManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void loadMap(MapFactory.MapType mapType) {
        Map map = MapFactory.getMap(mapType);

        if(map == null) {
            Gdx.app.debug(TAG, "Map does not exists! ( " + mapType.mapPath + " )");
            return;
        }

        currentMap = map;
    }

    public TiledMap getCurrentTiledMap() {
        return currentMap != null ? currentMap.getCurrentMap() : null;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public MapLayer getMapLayer(String layerName) {
        return currentMap != null ? currentMap.getMapLayer(layerName) : null;
    }

    public void updateMapEntities(Batch batch, float delta) {
        currentMap.updateEntities(this, batch, delta);
    }

    public void addBullet(Entity bullet) {
        currentMap.getBulletEntities().add(bullet);
    }

    public void removeBullet(Entity bullet) {
        currentMap.getBulletEntities().removeValue(bullet, true);
    }
}
