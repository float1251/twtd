package jp.float1251.twtd.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.TWTD;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class MainGameScreen implements Screen {
    private final TWTD game;
    private final FitViewport viewport;
    private final Stage stage;
    private OrthogonalTiledMapRenderer renderer;

    public MainGameScreen(final TWTD game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(960, 640, camera);
        camera.setToOrtho(false, 960, 640);
        TiledMap loader = new TmxMapLoader().load("stage/stage1.tmx");
        renderer = new OrthogonalTiledMapRenderer(loader);
        renderer.setView((OrthographicCamera) viewport.getCamera());

        stage = new Stage(viewport);

        VisUI.load();
        final Table table = new Table();
        TextButton button = new TextButton("ABC", VisUI.getSkin());
        button.getStyle().font.getData().setScale(3f);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameLog.d("clicked");
                table.addAction(Actions.moveTo(300, -100, 0.2f));
            }
        });
        table.setPosition(300, 100);
        table.add(button).size(200, 80).pad(2f);
        button = new TextButton("DEC", VisUI.getSkin());
        table.add(button).size(200, 80).pad(2f);
        stage.addActor(table);
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameLog.d("stage touchdown");
                Vector3 pos = viewport.getCamera().unproject(new Vector3(x, y, 0));
                // TODO pos‚©‚çƒZƒ‹‚ÌˆÊ’u‚ðŒvŽZ‚·‚é
                table.addAction(Actions.moveTo(300, 100, 0.2f));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.getCamera().update();
        Gdx.gl20.glClearColor(0, 0, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        stage.act(delta);
        stage.draw();
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
