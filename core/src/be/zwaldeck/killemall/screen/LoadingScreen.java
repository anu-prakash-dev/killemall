package be.zwaldeck.killemall.screen;

import be.zwaldeck.killemall.KillEmAllGame;
import be.zwaldeck.killemall.util.AssetUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingScreen extends AbstractScreen {

    public static final int WOLD_WIDTH = 1280;
    public static final int WOLD_HEIGHT = 720;

    private static final float PROGRESS_BAR_WIDTH = 1000;
    private static final float PROGRESS_BAR_HEIGHT = 25;

    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private OrthographicCamera camera;

    private float progress = 0;

    public LoadingScreen(KillEmAllGame game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(WOLD_WIDTH / 2, WOLD_HEIGHT / 2, 0);
        camera.update();

        viewport = new FillViewport(WOLD_WIDTH, WOLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        //All the assets we need to load
        AssetUtil.loadAtlas("packs/hud.atlas");
        AssetUtil.loadMapAsset("maps/forest.tmx");
        AssetUtil.loadAtlas("packs/player.atlas");
    }

    @Override
    public void render(float delta) {
        if (AssetUtil.updateAssetsLoading()) {
            game.setScreen(new GameScreen(game));
        } else {
            progress = AssetUtil.loadProgress();
            System.out.println(progress);
        }

        //Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(WOLD_WIDTH / 2 - PROGRESS_BAR_WIDTH / 2, WOLD_HEIGHT / 2 - PROGRESS_BAR_HEIGHT / 2,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
