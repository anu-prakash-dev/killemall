package be.zwaldeck.killemall;

import be.zwaldeck.killemall.screen.GameScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KillEmAllGame extends Game {

	private final GameScreen GAME_SCREEN = new GameScreen(this);

	@Override
	public void create () {
		setScreen(GAME_SCREEN);
	}


	@Override
	public void dispose () {
		GAME_SCREEN.dispose();
	}
}
