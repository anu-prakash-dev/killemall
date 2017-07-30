package be.zwaldeck.killemall.map;

import be.zwaldeck.killemall.util.AssetUtil;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Map {
    public static final float UNIT_SCALE = 1f / 32f;

    //Map Layers
    public static final String GRASS_LAYER = "GRASS";
    public static final String BORDER_LAYER = "BORDER";
    public static final String DECORATION_LAYER = "DECORATION";
    public static final String COLLISION_LAYER = "COLLISION";
    public static final String SPAWN_LAYER = "SPAWN";

    private TiledMap currentMap;

    public Map(String mapPath) {
        AssetUtil.loadMapAsset(mapPath);
        currentMap = AssetUtil.getMapAsset(mapPath);
    }

    public MapLayer getMapLayer(String layerName) {
        return currentMap.getLayers().get(layerName);
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }
}
