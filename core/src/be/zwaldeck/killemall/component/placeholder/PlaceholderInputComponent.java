package be.zwaldeck.killemall.component.placeholder;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.InputComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.InputProcessor;

/**
 * Placeholder class
 */
public class PlaceholderInputComponent extends InputComponent {

    @Override
    public void update(Entity entity, MapManager mapManager, float delta) {}

    @Override
    public void dispose() {}

    @Override
    public void receiveMessage(MessageType type, String message) {}
}
