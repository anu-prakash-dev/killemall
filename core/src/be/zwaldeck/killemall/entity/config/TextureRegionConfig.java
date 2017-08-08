package be.zwaldeck.killemall.entity.config;

import com.badlogic.gdx.graphics.g2d.Animation;

public class TextureRegionConfig {
    private String atlasName;
    private String regionName;

    public TextureRegionConfig(String atlasName, String regionName) {
        this.atlasName = atlasName;
        this.regionName = regionName;
    }

    public TextureRegionConfig() {}

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

}
