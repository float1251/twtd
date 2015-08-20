package jp.float1251.twtd.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.StageData;
import jp.float1251.twtd.TWTD;
import jp.float1251.twtd.ecs.system.BulletSystem;
import jp.float1251.twtd.ecs.system.EnemyCreateSystem;
import jp.float1251.twtd.ecs.system.EnemyMovementSystem;
import jp.float1251.twtd.ecs.system.MoveSystem;
import jp.float1251.twtd.ecs.system.RenderingSystem;
import jp.float1251.twtd.ecs.system.UnitSystem;
import jp.float1251.twtd.ui.MainGameUi;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class MainGameScreen implements Screen {
    private final TWTD game;
    private final FitViewport viewport;
    private final StageData stageData;
    private final MainGameUi ui;
    private final Engine engine;
    private final SpriteBatch batch;

    public MainGameScreen(final TWTD game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(960, 640, camera);
        camera.setToOrtho(false, 960, 640);
        stageData = new StageData("stage/stage1.tmx");
        stageData.setView((OrthographicCamera) viewport.getCamera());

        this.engine = new Engine();
        ui = new MainGameUi(viewport, stageData, engine);
        batch = new SpriteBatch();
        engine.addSystem(new RenderingSystem(batch));
        engine.addSystem(new EnemyCreateSystem(stageData.getRespawnPosition()));
        engine.addSystem(new EnemyMovementSystem(stageData.path.getPolyline(), new EnemyMovementSystem.IReachEndPoint() {
            @Override
            public void onReachEndPoint() {
                GameLog.d("onReachEndPoint");
            }
        }));
        engine.addSystem(new UnitSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new BulletSystem());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.getCamera().update();
        Gdx.gl20.glClearColor(0, 0, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stageData.render();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        engine.update(delta);
        ui.act(delta);
        ui.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
