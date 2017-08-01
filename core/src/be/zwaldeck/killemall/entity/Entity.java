package be.zwaldeck.killemall.entity;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.GraphicsComponent;
import be.zwaldeck.killemall.component.InputComponent;
import be.zwaldeck.killemall.component.PhysicsComponent;
import be.zwaldeck.killemall.component.player.PlayerInputComponent;
import be.zwaldeck.killemall.entity.config.EntityConfig;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

public class Entity {

    private EntityConfig config;

    private InputComponent inputComponent;
    private PhysicsComponent physicsComponent;
    private GraphicsComponent graphicsComponent;

    private Json json;

    public Entity(EntityConfig config, InputComponent inputComponent, PhysicsComponent physicsComponent, GraphicsComponent graphicsComponent) {
        this.config = config;

        this.inputComponent = inputComponent;
        this.physicsComponent = physicsComponent;
        this.graphicsComponent = graphicsComponent;

        json = new Json();

        sendMessageToComponents(Component.MessageType.INIT_ENTITY, json.toJson(config));
    }

    public void sendMessageToComponents(Component.MessageType messageType, String... payload) {
        StringBuilder message = new StringBuilder();
        for(String s : payload) {
            message.append(s).append(Component.MESSAGE_TOKEN);
        }

        inputComponent.receiveMessage(messageType, message.toString());
        physicsComponent.receiveMessage(messageType, message.toString());
        graphicsComponent.receiveMessage(messageType, message.toString());
    }

    public void update(MapManager mapManager, Batch batch, float delta) {
        inputComponent.update(this, mapManager, delta);
        physicsComponent.update(this, mapManager, delta);
        graphicsComponent.update(this, mapManager, batch, delta);
    }

    public void dispose() {
        inputComponent.dispose();
        physicsComponent.dispose();
        graphicsComponent.dispose();
    }

    public Rectangle getBoundingBox() {
        return physicsComponent.getBoundingBox();
    }

    public InputProcessor getInputProcessor() {
        if(inputComponent instanceof PlayerInputComponent) {
            return (PlayerInputComponent) inputComponent;
        }

        return null;
    }

    public float getWidth() {
        return config.getWidth();
    }

    public float getHeight() {
        return config.getHeight();
    }
}
