package be.zwaldeck.killemall.screen;

import be.zwaldeck.killemall.KillEmAllGame;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

    protected KillEmAllGame game;

    public AbstractScreen(KillEmAllGame game) {
        this.game = game;
    }

    public void pause() {}
    public void resume() {}
    public void hide() {}
}
