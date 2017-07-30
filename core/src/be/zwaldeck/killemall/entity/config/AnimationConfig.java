package be.zwaldeck.killemall.entity.config;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationConfig {

    private String atlasName;
    private String regionName;
    private float frameTime;
    private Animation.PlayMode playMode;
    private AnimationType type;

    public AnimationConfig(String atlasName, String regionName, float frameTime, Animation.PlayMode playMode, AnimationType type) {
        this.atlasName = atlasName;
        this.regionName = regionName;
        this.frameTime = frameTime;
        this.playMode = playMode;
        this.type = type;
    }

    public AnimationConfig() {}

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public float getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(float frameTime) {
        this.frameTime = frameTime;
    }

    public Animation.PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(Animation.PlayMode playMode) {
        this.playMode = playMode;
    }

    public AnimationType getType() {
        return type;
    }

    public void setType(AnimationType type) {
        this.type = type;
    }
}
