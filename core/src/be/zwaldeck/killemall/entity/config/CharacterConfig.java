package be.zwaldeck.killemall.entity.config;

import com.badlogic.gdx.utils.Array;

public class CharacterConfig extends EntityConfig {

    private Array<AnimationConfig> animations;

    public CharacterConfig() {
    }

    public CharacterConfig(int width, int height, Array<AnimationConfig> animations) {
        super(width, height);
        this.animations = animations;
    }

    public Array<AnimationConfig> getAnimations() {
        return animations;
    }

    public void setAnimations(Array<AnimationConfig> animations) {
        this.animations = animations;
    }
}
