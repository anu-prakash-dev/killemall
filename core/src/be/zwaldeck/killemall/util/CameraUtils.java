package be.zwaldeck.killemall.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class CameraUtils {

    public static Vector2 screenToMap(Vector2 screenPosition, OrthographicCamera camera) {
        Vector3 pos = new Vector3(screenPosition.x, screenPosition.y, 0);
        camera.unproject(pos);
        return new Vector2(pos.x, pos.y);
    }
}
