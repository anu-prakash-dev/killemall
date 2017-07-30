package be.zwaldeck.killemall.component.player;

import be.zwaldeck.killemall.component.Component;
import be.zwaldeck.killemall.component.InputComponent;
import be.zwaldeck.killemall.entity.Entity;
import be.zwaldeck.killemall.entity.State;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class PlayerInputComponent extends InputComponent implements InputProcessor {

    public enum InputAction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        FIRE,
        PAUSE
    }

    private HashMap<InputAction, Boolean> inputActions;
    private Vector2 mousePosition;

    public PlayerInputComponent() {
        super();
        inputActions = new HashMap<InputAction, Boolean>();
        mousePosition = new Vector2(0,0);

        //Initialize every action
        inputActions.put(InputAction.LEFT, false);
        inputActions.put(InputAction.RIGHT, false);
        inputActions.put(InputAction.UP, false);
        inputActions.put(InputAction.DOWN, false);
        inputActions.put(InputAction.FIRE, false);
        inputActions.put(InputAction.PAUSE, false);
    }

    @Override
    public void receiveMessage(MessageType type, String message) {

    }

    @Override
    public void update(Entity entity, float delta) {
        //todo: Pause functionality

        if(inputActions.get(InputAction.LEFT) || inputActions.get(InputAction.RIGHT) || inputActions.get(InputAction.UP) || inputActions.get(InputAction.DOWN)) {
            currentState = State.WALKING;
        }
        else {
            currentState = State.IDLE;
        }

        entity.sendMessageToComponents(MessageType.IS_LEFT, json.toJson(inputActions.get(InputAction.LEFT)));
        entity.sendMessageToComponents(MessageType.IS_RIGHT, json.toJson(inputActions.get(InputAction.RIGHT)));
        entity.sendMessageToComponents(MessageType.IS_UP, json.toJson(inputActions.get(InputAction.DOWN)));
        entity.sendMessageToComponents(MessageType.IS_DOWN, json.toJson(inputActions.get(InputAction.UP)));
        entity.sendMessageToComponents(MessageType.IS_FIRING, json.toJson(inputActions.get(InputAction.FIRE)));
        entity.sendMessageToComponents(MessageType.CURRENT_STATE, json.toJson(currentState));
        entity.sendMessageToComponents(MessageType.CURRENT_MOUSE_POSITION, json.toJson(mousePosition));

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.Z || keycode == Input.Keys.UP) {
            inputActions.put(InputAction.UP, true);
        }
        else if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            inputActions.put(InputAction.DOWN, true);
        }
        else if(keycode == Input.Keys.Q || keycode == Input.Keys.LEFT) {
            inputActions.put(InputAction.LEFT, true);
        }
        else if(keycode == Input.Keys.D|| keycode == Input.Keys.RIGHT) {
            inputActions.put(InputAction.RIGHT, true);
        }
        else if(keycode == Input.Keys.P) {
            inputActions.put(InputAction.PAUSE, true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.Z || keycode == Input.Keys.UP) {
            inputActions.put(InputAction.UP, false);
        }
        else if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            inputActions.put(InputAction.DOWN, false);
        }
        else if(keycode == Input.Keys.Q || keycode == Input.Keys.LEFT) {
            inputActions.put(InputAction.LEFT, false);
        }
        else if(keycode == Input.Keys.D|| keycode == Input.Keys.RIGHT) {
            inputActions.put(InputAction.RIGHT, false);
        }
        else if(keycode == Input.Keys.P) {
            inputActions.put(InputAction.PAUSE, false);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            inputActions.put(InputAction.FIRE, true);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            inputActions.put(InputAction.FIRE, false);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
