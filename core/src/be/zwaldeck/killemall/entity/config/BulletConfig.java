package be.zwaldeck.killemall.entity.config;

public class BulletConfig extends EntityConfig {

    private TextureRegionConfig texture;

    public BulletConfig() {
    }

    public BulletConfig(int width, int height, TextureRegionConfig texture) {
        super(width, height);
        this.texture = texture;
    }

    public TextureRegionConfig getTexture() {
        return texture;
    }

    public void setTexture(TextureRegionConfig texture) {
        this.texture = texture;
    }
}
