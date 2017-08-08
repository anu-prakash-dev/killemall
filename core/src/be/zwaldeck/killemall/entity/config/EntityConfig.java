package be.zwaldeck.killemall.entity.config;

import com.badlogic.gdx.utils.Array;

public class EntityConfig {

    private int width;
    private int height;

    public EntityConfig(int width, int height) {
        this.width = width;
        this.height = height;
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

}
