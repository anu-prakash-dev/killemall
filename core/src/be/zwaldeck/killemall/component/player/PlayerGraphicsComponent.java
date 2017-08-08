package be.zwaldeck.killemall.component.player;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.GraphicsComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import be.zwaldeck.killemall.entity.config.AnimationType;
import be.zwaldeck.killemall.entity.config.CharacterConfig;
import be.zwaldeck.killemall.map.Map;
import be.zwaldeck.killemall.map.MapManager;
import be.zwaldeck.killemall.screen.GameScreen;
import be.zwaldeck.killemall.util.AssetUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerGraphicsComponent extends GraphicsComponent {

    private static final float ROTATION_OFFSET = 195;

    //tmp
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Vector2 mousePosition = new Vector2(0, 0);
    private float angle;

    private TextureRegion crosshairTexture;

    @Override
    public void receiveMessage(MessageType type, String message) {
        String[] parts = message.split(Component.MESSAGE_TOKEN);

        switch (type) {
            case INIT_ENTITY:
                CharacterConfig config = json.fromJson(CharacterConfig.class, parts[0]);
                currentPosition = new Vector2(1600, 1600);
                loadAnimations(config.getAnimations());
                currentAnimation = animations.get(AnimationType.HANDGUN_IDLE);
                loadCrosshair();
                break;
            case CURRENT_POSITION:
                currentPosition = json.fromJson(Vector2.class, parts[0]);
                break;
            case CURRENT_STATE:
                currentState = json.fromJson(State.class, parts[0]);
                break;
            case CURRENT_MOUSE_POSITION:
                mousePosition = json.fromJson(Vector2.class, parts[0]);
                break;
        }
    }

    @Override
    public void update(Entity entity, MapManager mapManager, Batch batch, float delta) {
        selectCurrentAnimation();
        updateAnimation(delta);

        updateCamera(mapManager);
        updateAngle(entity);

        batch.begin();
        batch.draw(currentFrame,
                currentPosition.x, currentPosition.y,
                entity.getWidth() / 2.0f, entity.getHeight() / 2.0f,
                entity.getWidth(), entity.getHeight(),
                1, 1,
                angle + ROTATION_OFFSET, false);
        batch.draw(crosshairTexture, mousePosition.x - 16, mousePosition.y - 16, 32 ,32);
        batch.end();

        //tmps
        Rectangle bb = entity.getBoundingBox();
        shapeRenderer.setProjectionMatrix(mapManager.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(bb.x, bb.y, bb.width, bb.height);
        shapeRenderer.end();
    }

    private void selectCurrentAnimation() {

    }

    private void updateCamera(MapManager mapManager) {
        Camera camera = mapManager.getCamera();

        float x = currentPosition.x;
        float y = currentPosition.y;

        TiledMapTileLayer layer = (TiledMapTileLayer) mapManager.getMapLayer(Map.GRASS_LAYER);
        float mapWidthInPx = layer.getTileWidth() * layer.getWidth();
        float mapHeightInPx = layer.getTileHeight() * layer.getHeight();

        if (x > GameScreen.WOLD_WIDTH / 2.0f && x <= mapWidthInPx - (GameScreen.WOLD_WIDTH / 2)) {
            camera.position.x = x;
        }

        if (y > GameScreen.WOLD_HEIGHT / 2.0f && y <= mapHeightInPx - (GameScreen.WOLD_HEIGHT / 2)) {
            camera.position.y = y;
        }

        camera.update();
    }

    private void updateAngle(Entity entity) {
        Vector2 curPos = currentPosition.cpy();
        curPos.x += entity.getWidth() / 2;
        curPos.y += entity.getHeight() / 2;
        angle = (float) Math.toDegrees(Math.atan2(mousePosition.x - curPos.x , -(mousePosition.y - curPos.y)));
        entity.sendMessageToComponents(MessageType.CURRENT_ANGLE, json.toJson(angle));
    }

    private void loadCrosshair() {
        TextureAtlas atlas = AssetUtils.getAtlas("packs/hud.atlas");

        if(atlas != null) {
            crosshairTexture = atlas.findRegion("crosshair");
        }
    }
}
