package be.zwaldeck.killemall.entity;

import com.badlogic.gdx.math.MathUtils;

public enum State {
    IDLE,
    WALKING,

    IMMOBILE; //Needs to be last

    public static State getRandomState() {
        return State.values()[MathUtils.random(State.values().length - 2)];
    }
}
