package jp.float1251.twtd.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import jp.float1251.twtd.TWTD;
import jp.float1251.twtd.asset.AssetLoader;
import jp.float1251.twtd.ui.CustomTextLabel;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class TitleScreen implements Screen {

    private final TWTD game;
    private final Stage stage;

    public TitleScreen(final TWTD game) {
        this.game = game;

        AssetLoader.getInstance().load();
        CustomTextLabel title = new CustomTextLabel("TWTD");
        stage = new Stage(new FitViewport(960, 640));
        title.setPosition(stage.getCamera().viewportWidth / 2 - title.getTextWidth() / 2, stage.getCamera().viewportHeight / 2);
        stage.addActor(title);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
