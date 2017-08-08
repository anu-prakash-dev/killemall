package be.zwaldeck.killemall.component.bullet;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.PhysicsComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.config.BulletConfig;
import be.zwaldeck.killemall.gun.Gun;
import be.zwaldeck.killemall.map.MapManager;
import be.zwaldeck.killemall.util.CameraUtils;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BulletPhysicsComponent extends PhysicsComponent {

    private Vector2 direction;
    private Gun gun;

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        switch (type) {
            case INIT_ENTITY:
                BulletConfig config = json.fromJson(BulletConfig.class, parts[0]);
                initBoundingBox(config);
                break;
            case INIT_START_POSITION:
                Vector2 position = json.fromJson(Vector2.class, parts[0]);
                currentEntityPosition.set(position.x, position.y);
                nextEntityPosition.set(position.x, position.y);
                break;
            case INIT_DIRECTION:
                direction = json.fromJson(Vector2.class, parts[0]);
                break;
            case INIT_GUN:
                gun = json.fromJson(Gun.class, parts[0]);
                velocity = new Vector2(100 * gun.getBulletSpeed() * direction.x, 100 * gun.getBulletSpeed() * direction.y);
                break;
        }
    }

    @Override
    public void update(Entity entity, MapManager mapManager, float delta) {
        updateBoundingBox(nextEntityPosition);

        if (CameraUtils.isOutOfScreen(mapManager.getCamera(), nextEntityPosition) ||
                isCollisionWithMapLayer(entity, mapManager)) {
            mapManager.removeBullet(entity);
        } else {
            setNextEntityPositionToCurrent(entity);
        }

        calculateNextEntityPosition(delta);
    }

    @Override
    public void dispose() {

    }

    protected void calculateNextEntityPosition(float deltaTime) {
        float testX = currentEntityPosition.x;
        float testY = currentEntityPosition.y;

        velocity.scl(deltaTime);

        testX += velocity.x;
        testY += velocity.y;

        nextEntityPosition.set(testX, testY);

        velocity.scl(1 / deltaTime);
    }
}
