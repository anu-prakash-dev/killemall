package be.zwaldeck.killemall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import be.zwaldeck.killemall.KillEmAllGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 720;
		config.title = "Kill Em All!";

		new LwjglApplication(new KillEmAllGame(), config);
	}
}
