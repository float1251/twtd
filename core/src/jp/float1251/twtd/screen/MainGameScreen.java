package jp.float1251.twtd.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.TWTD;
import jp.float1251.twtd.game.SelectedCell;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class MainGameScreen implements Screen {
    private final TWTD game;
    private final FitViewport viewport;
    private final Stage stage;
    private final SpriteBatch batch;
    private final SelectedCell selectedCell;
    private OrthogonalTiledMapRenderer renderer;

    public MainGameScreen(final TWTD game) {
        this.game = game;
        this.batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(960, 640, camera);
        camera.setToOrtho(false, 960, 640);
        TiledMap loader = new TmxMapLoader().load("stage/stage1.tmx");
        renderer = new OrthogonalTiledMapRenderer(loader);
        renderer.setView((OrthographicCamera) viewport.getCamera());

        selectedCell = new SelectedCell(viewport);

        stage = new Stage(viewport);

        VisUI.load();
        final VisTable table = new VisTable();
        TextButton button = new VisTextButton("ABC");
        button.getStyle().font.getData().setScale(3f);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameLog.d("clicked");
                selectedCell.setSelected(false);
                table.addAction(Actions.moveTo(300, -100, 0.2f));
            }
        });
        table.setPosition(300, -100);
        table.add(button).size(200, 80).pad(2f);
        button = new VisTextButton("DEC");
        table.add(button).size(200, 80).pad(2f);
        stage.addActor(table);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                GameLog.d("stage touchdown");
                Vector3 pos = viewport.unproject(new Vector3(screenX, screenY, 0));
                selectedCell.setSelectedPosition(pos);
                table.addAction(Actions.moveTo(300, 100, 0.2f));
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        }));

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

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (selectedCell.isSelected()) {
            selectedCell.draw(batch);
        }
        batch.end();
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
