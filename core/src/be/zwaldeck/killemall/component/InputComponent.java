package be.zwaldeck.killemall.component;

import be.zwaldeck.killemall.entity.Direction;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import com.badlogic.gdx.utils.Json;

public abstract class InputComponent implements Component {

    protected Direction currentDirection;
    protected State currentState;

    protected Json json;

    protected InputComponent() {
        json = new Json();
    }

    public abstract void update(Entity entity, float delta);

}
