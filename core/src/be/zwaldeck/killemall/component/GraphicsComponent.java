package be.zwaldeck.killemall.component;

import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import be.zwaldeck.killemall.entity.config.AnimationConfig;
import be.zwaldeck.killemall.entity.config.AnimationType;
import be.zwaldeck.killemall.map.MapManager;
import be.zwaldeck.killemall.util.AssetUtil;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

public abstract class GraphicsComponent implements Component {

    protected TextureRegion currentFrame;
    protected Animation<TextureRegion> currentAnimation;
    protected HashMap<AnimationType, Animation<TextureRegion>> animations;
    protected float frameTime;

    protected State currentState;
    protected Vector2 currentPosition;

    protected Json json;

    protected GraphicsComponent() {
        frameTime = 0f;

        currentPosition = new Vector2(0, 0);
        currentState = State.WALKING;

        json = new Json();
    }

    public abstract void update(Entity entity, MapManager mapManager, Batch batch, float delta);

    @Override
    public void dispose() {
        currentFrame = null;
        currentAnimation = null;
        animations = null;
    }

    protected void updateAnimation(float delta) {
        frameTime = ((frameTime + delta) % 5);
        currentFrame = currentAnimation.getKeyFrame(frameTime);
    }

    protected void loadAnimations(Array<AnimationConfig> animationConfigs) {
        animations = new HashMap<AnimationType, Animation<TextureRegion>>();
        for (AnimationConfig animationConfig : animationConfigs) {
            Animation<TextureRegion> animation = loadAnimation(animationConfig);
            if (animation != null) {
                animations.put(animationConfig.getType(), animation);
            }
        }
    }

    protected Animation<TextureRegion> loadAnimation(AnimationConfig animationConfig) {
        TextureAtlas atlas = AssetUtil.getAtlas("packs/" + animationConfig.getAtlasName());

        if (atlas != null) {
            return new Animation<TextureRegion>(animationConfig.getFrameTime(), atlas.findRegions(animationConfig.getRegionName()), animationConfig.getPlayMode());
        }

        return null;
    }

}
