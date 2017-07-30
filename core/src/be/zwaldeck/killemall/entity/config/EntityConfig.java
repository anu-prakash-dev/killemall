package be.zwaldeck.killemall.entity.config;

import com.badlogic.gdx.utils.Array;

public class EntityConfig {

    private int width;
    private int height;
    private Array<AnimationConfig> animations;

    public EntityConfig(int width, int height, Array<AnimationConfig> animations) {
        this.width = width;
        this.height = height;
        this.animations = animations;
    }

    public EntityConfig() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Array<AnimationConfig> getAnimations() {
        return animations;
    }

    public void setAnimations(Array<AnimationConfig> animations) {
        this.animations = animations;
    }
}
