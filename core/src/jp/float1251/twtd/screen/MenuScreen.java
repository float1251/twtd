package jp.float1251.twtd.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;

import jp.float1251.twtd.TWTD;
import jp.float1251.twtd.data.WaveData;
import jp.float1251.twtd.util.GameUtils;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class MenuScreen implements Screen {
    private final TWTD game;
    private Stage stage;

    public MenuScreen(final TWTD game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(960, 640));
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        VisUI.load();
        VisTextButton button = new VisTextButton("Stage1:");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // waveDataを作成する
                String json = Gdx.files.internal("stage/stage1.json").readString();
                String[] paths = GameUtils.createStageWaveDataPathArray(json);
                WaveData[] datas = new WaveData[paths.length];
                int i = 0;
                for (String path : paths) {
                    json = Gdx.files.internal(path).readString();
                    datas[i] = GameUtils.createWaveData(json);
                    i++;
                }
                game.setScreen(new MainGameScreen(game, datas));
            }
        });
        table.add(button).size(200, 100).row();
        button = new VisTextButton("setting unit");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        table.add(button).size(200, 100).row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
