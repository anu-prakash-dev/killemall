package be.zwaldeck.killemall.screen;

import be.zwaldeck.killemall.KillEmAllGame;
import be.zwaldeck.killemall.map.MapFactory;
import be.zwaldeck.killemall.map.MapManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends AbstractScreen {

    public static final int WOLD_WIDTH = 1280;
    public static final int WOLD_HEIGHT = 720;

    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer mapRenderer;
    private SpriteBatch batch;
    private MapManager mapManager;
    private InputMultiplexer multiplexer;

    public GameScreen(KillEmAllGame game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WOLD_WIDTH, WOLD_HEIGHT, camera);
        viewport.apply(true);
        mapManager = new MapManager(camera);
        mapManager.loadMap(MapFactory.MapType.FOREST);

        multiplexer = new InputMultiplexer();
        //todo the hud input must come first
//        multiplexer.addProcessor(player.getInputProcessor());

        Gdx.input.setInputProcessor(multiplexer);

        if(mapRenderer == null) {
            mapRenderer = new OrthogonalTiledMapRenderer(mapManager.getCurrentTiledMap());
        }

        batch = (SpriteBatch) mapRenderer.getBatch();

        //temp
        camera.position.set(0,0,0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.setTitle("Kill Em All! - " + Gdx.graphics.getFramesPerSecond() + " FPS");

        mapRenderer.setView(camera);
        mapRenderer.render();
        batch.begin();
        //draw entities
        batch.end();

        //draw hud

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
    }

    private void update(float delta) {

    }
}
