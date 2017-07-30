package be.zwaldeck.killemall.component.player;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.PhysicsComponent;
import be.zwaldeck.killemall.entity.Direction;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import be.zwaldeck.killemall.map.MapManager;

public class PlayerPhysicsComponent extends PhysicsComponent{

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        switch (type) {
            case INIT_ENTITY:
                EntityConfig config = json.fromJson(EntityConfig.class, parts[0]);
                currentEntityPosition.set(0,0);
                nextEntityPosition.set(0,0);
                initBoundingBox(config);
                break;
            case IS_LEFT:
                currentDirections.put(Direction.LEFT, json.fromJson(Boolean.class, parts[0]));
                break;
            case IS_RIGHT:
                currentDirections.put(Direction.RIGHT, json.fromJson(Boolean.class, parts[0]));
                break;
            case IS_UP:
                currentDirections.put(Direction.UP, json.fromJson(Boolean.class, parts[0]));
                break;
            case IS_DOWN:
                currentDirections.put(Direction.DOWN, json.fromJson(Boolean.class, parts[0]));
                break;
            case IS_FIRING:
                break;
        }
    }

    @Override
    public void update(Entity entity, MapManager mapManager, float delta) {
        updateBoundingBox(nextEntityPosition);

        if(!isCollisionWithMapLayer(entity, mapManager)) {
            setNextEntityPositionToCurrent(entity);
        }
        else {
            updateBoundingBox(currentEntityPosition);
        }

        calculateNextEntityPosition(delta);
    }

    @Override
    public void dispose() {}
}
