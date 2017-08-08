package be.zwaldeck.killemall.map;

import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.util.AssetUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

public class Map {
    public static final float UNIT_SCALE = 1f / 32f;

    //Map Layers
    public static final String GRASS_LAYER = "GRASS";
    public static final String BORDER_LAYER = "BORDER";
    public static final String DECORATION_LAYER = "DECORATION";
    public static final String COLLISION_LAYER = "COLLISION";
    public static final String SPAWN_LAYER = "SPAWN";

    private TiledMap currentMap;

    //entities
    private Array<Entity> bulletEntities = new Array<Entity>();

    public Map(String mapPath) {
        currentMap = AssetUtils.getMapAsset(mapPath);
    }

    public MapLayer getMapLayer(String layerName) {
        return currentMap.getLayers().get(layerName);
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

    public Array<Entity> getBulletEntities() {
        return bulletEntities;
    }

    public void updateEntities(MapManager mapManager, Batch batch, float delta) {

        for(int i = 0; i < bulletEntities.size; i++) {
            bulletEntities.get(i).update(mapManager, batch, delta);
        }
    }
}
