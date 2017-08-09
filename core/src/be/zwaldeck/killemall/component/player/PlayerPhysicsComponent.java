package be.zwaldeck.killemall.component.player;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.PhysicsComponent;
import be.zwaldeck.killemall.entity.Direction;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.EntityFactory;
import be.zwaldeck.killemall.entity.config.CharacterConfig;
import be.zwaldeck.killemall.gun.GunManager;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PlayerPhysicsComponent extends PhysicsComponent{

    private boolean isFiring = false;
    private boolean isTriggerReleased = true;
    private float angle;

    private Vector2 mousePosition;
    private GunManager gunManager;


    public PlayerPhysicsComponent(GunManager gunManager) {
        super();

        this.gunManager = gunManager;
        velocity = new Vector2(200, 200);
        mousePosition = new Vector2(0,0);
    }

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        switch (type) {
            case INIT_ENTITY:
                CharacterConfig config = json.fromJson(CharacterConfig.class, parts[0]);
                currentEntityPosition.set(1600,1600);
                nextEntityPosition.set(1600,1600);
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
                isFiring = json.fromJson(Boolean.class, parts[0]);
                break;
            case CURRENT_MOUSE_POSITION:
                mousePosition = json.fromJson(Vector2.class, parts[0]);
                break;
            case CURRENT_ANGLE:
                angle = json.fromJson(Float.class, parts[0]);
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

        updateFiring(mapManager);
    }

    @Override
    public void dispose() {}

    private void updateFiring(MapManager mapManager) {
        if(isFiring) {
            if(gunManager.canCurrentGunFire(isTriggerReleased)) {
                Vector2 bulletStartPos = currentEntityPosition.cpy();
                Vector2 targetPos = mousePosition.cpy();
                Vector2 diff = new Vector2(targetPos.x - bulletStartPos.x, targetPos.y - bulletStartPos.y).nor();

                Entity bullet = EntityFactory.getInstance().getEntity(EntityFactory.EntityType.BULLET);
                mapManager.addBullet(bullet);

                bullet.sendMessageToComponents(MessageType.INIT_START_POSITION, json.toJson(bulletStartPos));
                bullet.sendMessageToComponents(MessageType.INIT_DIRECTION, json.toJson(diff));
                bullet.sendMessageToComponents(MessageType.INIT_GUN, json.toJson(gunManager.getCurrentGun()));
                bullet.sendMessageToComponents(MessageType.INIT_ANGLE, json.toJson(angle));
            }
        }

        isTriggerReleased = !isFiring;
    }
}
