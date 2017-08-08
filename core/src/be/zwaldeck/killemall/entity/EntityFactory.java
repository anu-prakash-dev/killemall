package be.zwaldeck.killemall.entity;

import be.zwaldeck.killemall.component.bullet.BulletGraphicsComponent;
import be.zwaldeck.killemall.component.bullet.BulletPhysicsComponent;
import be.zwaldeck.killemall.component.placeholder.PlaceholderInputComponent;
import be.zwaldeck.killemall.component.player.PlayerGraphicsComponent;
import be.zwaldeck.killemall.component.player.PlayerInputComponent;
import be.zwaldeck.killemall.component.player.PlayerPhysicsComponent;
import be.zwaldeck.killemall.entity.config.BulletConfig;
import be.zwaldeck.killemall.entity.config.CharacterConfig;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import be.zwaldeck.killemall.gun.GunManager;
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
        PLAYER("config/player.json"),
        BULLET("config/bullet.json");

        public final String configPath;

        EntityType(String configPath) {
            this.configPath = configPath;
        }
    }

    public Entity getEntity(EntityType type) {
        EntityConfig config = null;
        switch (type) {
            case PLAYER:
                config = json.fromJson(CharacterConfig.class, Gdx.files.internal(type.configPath));
                GunManager gunManager = new GunManager();
                gunManager.addOwnedGun("GLOCK 23");
                gunManager.setCurrentGun("GLOCK 23");
                return new Entity(config, new PlayerInputComponent(), new PlayerPhysicsComponent(gunManager), new PlayerGraphicsComponent());
            case BULLET:
                config = json.fromJson(BulletConfig.class, Gdx.files.internal(type.configPath));
                return new Entity(config, new PlaceholderInputComponent(), new BulletPhysicsComponent(), new BulletGraphicsComponent());
        }

        return null;
    }
}
