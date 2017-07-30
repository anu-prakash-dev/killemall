package be.zwaldeck.killemall.map;

public final class MapFactory {
    public enum MapType {
        FOREST("maps/forest.tmx");

        public final String mapPath;

        MapType(String mapPath) {
            this.mapPath = mapPath;
        }
    }

    public static Map getMap(MapType type) {
        switch (type){
            case FOREST:
                return new Map(type.mapPath);
        }

        return null;
    }
}
