package be.zwaldeck.killemall.entity;

import com.badlogic.gdx.math.MathUtils;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public static Direction getRandomDirection() {
        return Direction.values()[MathUtils.random(Direction.values().length - 1)];
    }
}
