package be.zwaldeck.killemall.entity;

import be.zwaldeck.killemall.component.player.PlayerGraphicsComponent;
import be.zwaldeck.killemall.component.player.PlayerInputComponent;
import be.zwaldeck.killemall.component.player.PlayerPhysicsComponent;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class EntityFactory {
    private static EntityFactory instance = null;

    public static EntityFactory getInstance() {
        if(instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    private Json json;

    private EntityFactory() {
        json = new Json();
    }

    public enum EntityType {
        PLAYER("config/player.json");

        public final String configPath;

        EntityType(String configPath) {
            this.configPath = configPath;
        }
    }

    public Entity getEntity(EntityType type) {
        EntityConfig config = null;
        switch (type) {
            case PLAYER:
                config = json.fromJson(EntityConfig.class, Gdx.files.internal(type.configPath));
                return new Entity(config, new PlayerInputComponent(), new PlayerPhysicsComponent(), new PlayerGraphicsComponent());
        }

        return null;
    }
}
