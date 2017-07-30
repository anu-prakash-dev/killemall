package be.zwaldeck.killemall.component.player;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.GraphicsComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import be.zwaldeck.killemall.entity.config.AnimationType;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class PlayerGraphicsComponent extends GraphicsComponent {

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        switch (type) {
            case INIT_ENTITY:
                EntityConfig config = json.fromJson(EntityConfig.class, parts[0]);
                currentPosition = new Vector2(0,0);
                loadAnimations(config.getAnimations());
                currentAnimation = animations.get(AnimationType.FLASHLIGHT_IDLE);
                break;
            case CURRENT_POSITION:
                currentPosition = json.fromJson(Vector2.class, parts[0]);
                break;
            case CURRENT_STATE:
                currentState = json.fromJson(State.class, parts[0]);
                break;
        }
    }

    @Override
    public void update(Entity entity, MapManager mapManager, Batch batch, float delta) {
        selectCurrentAnimation();
        updateAnimation(delta);

        updateCamera(mapManager);

        batch.begin();
        batch.draw(currentFrame, currentPosition.x, currentPosition.y);
        batch.end();
    }

    private void selectCurrentAnimation() {

    }

    private void updateCamera(MapManager mapManager) {
        Camera camera = mapManager.getCamera();

        camera.position.x = currentPosition.x;
        camera.position.y = currentPosition.y;
        camera.update();
    }
}
