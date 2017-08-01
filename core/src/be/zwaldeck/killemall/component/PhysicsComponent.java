package be.zwaldeck.killemall.component;

import be.zwaldeck.killemall.entity.Direction;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import be.zwaldeck.killemall.map.Map;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

public abstract class PhysicsComponent implements Component {

    protected Vector2 nextEntityPosition;
    protected Vector2 currentEntityPosition;
    protected HashMap<Direction, Boolean> currentDirections;
    protected State currentState;
    protected Vector2 velocity;
    protected Rectangle boundingBox;

    protected Json json;

    protected PhysicsComponent() {
        nextEntityPosition = new Vector2(0, 0);
        currentEntityPosition = new Vector2(0, 0);
        velocity = new Vector2(100, 100);
        boundingBox = new Rectangle();

        json = new Json();

        //initialize directions
        currentDirections = new HashMap<Direction, Boolean>();
        currentDirections.put(Direction.LEFT, false);
        currentDirections.put(Direction.RIGHT, false);
        currentDirections.put(Direction.UP, false);
        currentDirections.put(Direction.DOWN, false);
    }

    public abstract void update(Entity entity, MapManager mapManager, float delta);

    protected boolean isCollisionWithMapLayer(Entity entity, MapManager mapManager) {
        MapLayer collisionLayer = mapManager.getMapLayer(Map.COLLISION_LAYER);

        if (collisionLayer == null) {
            return false;
        }

        Rectangle rectangle = null;
        for (MapObject object : collisionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                rectangle = ((RectangleMapObject) object).getRectangle();
                if (boundingBox.overlaps(rectangle)) {
                    entity.sendMessageToComponents(MessageType.COLLISION_WITH_MAP);
                    return true;
                }
            }
        }

        return false;
    }

    protected void setNextEntityPositionToCurrent(Entity entity) {
        this.currentEntityPosition.x = nextEntityPosition.x;
        this.currentEntityPosition.y = nextEntityPosition.y;

        entity.sendMessageToComponents(MessageType.CURRENT_POSITION, json.toJson(currentEntityPosition));
    }

    protected void calculateNextEntityPosition(float deltaTime) {
        float testX = currentEntityPosition.x;
        float testY = currentEntityPosition.y;

        velocity.scl(deltaTime);

        if (currentDirections.get(Direction.LEFT)) {
            testX -= velocity.x;
        }

        if (currentDirections.get(Direction.RIGHT)) {
            testX += velocity.x;
        }

        if (currentDirections.get(Direction.DOWN)) {
            testY -= velocity.y;
        }

        if (currentDirections.get(Direction.UP)) {
            testY += velocity.y;
        }

        nextEntityPosition.set(testX, testY);

        velocity.scl(1 / deltaTime);
    }

    protected void initBoundingBox(EntityConfig config) {
        boundingBox.setWidth(config.getWidth());
        boundingBox.setHeight(config.getHeight());

        updateBoundingBox(currentEntityPosition);
    }

    protected void updateBoundingBox(Vector2 position) {
        boundingBox.x = position.x;
        boundingBox.y = position.y;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
