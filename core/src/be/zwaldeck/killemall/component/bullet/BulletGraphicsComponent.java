package be.zwaldeck.killemall.component.bullet;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.GraphicsComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.config.BulletConfig;
import be.zwaldeck.killemall.entity.config.TextureRegionConfig;
import be.zwaldeck.killemall.map.MapManager;
import be.zwaldeck.killemall.util.AssetUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class BulletGraphicsComponent extends GraphicsComponent {

    private float angle = 0.0f;

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        Vector2 position = null;

        switch (type) {
            case INIT_ENTITY:
                BulletConfig config = json.fromJson(BulletConfig.class, parts[0]);
                loadCurrentFrame(config.getTexture());
                break;
            case INIT_START_POSITION:
                position = json.fromJson(Vector2.class, parts[0]);
                currentPosition.set(position.x, position.y);
                break;
            case CURRENT_POSITION:
                position = json.fromJson(Vector2.class, parts[0]);
                currentPosition.set(position.x, position.y);
                break;
            case INIT_ANGLE:
                angle = json.fromJson(Double.class, parts[0]).floatValue();
                break;
        }
    }

    @Override
    public void update(Entity entity, MapManager mapManager, Batch batch, float delta) {
        batch.begin();
        batch.draw(currentFrame,
                currentPosition.x, currentPosition.y,
                entity.getWidth() / 2.0f, entity.getHeight() / 2.0f,
                entity.getWidth(), entity.getHeight(),
                1, 1,
                angle, true);
        batch.end();
    }

    private void loadCurrentFrame(TextureRegionConfig config) {
        TextureAtlas atlas = AssetUtils.getAtlas("packs/" + config.getAtlasName());

        if(atlas != null) {
            currentFrame = atlas.findRegion(config.getRegionName());
        }
    }
}
