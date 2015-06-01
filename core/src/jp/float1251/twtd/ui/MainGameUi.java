package jp.float1251.twtd.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.StageData;
import jp.float1251.twtd.game.SelectedCell;

/**
 * Created by takahiro iwatani on 2015/05/31.
 */
public class MainGameUi {

    private final Stage stage;
    private final Viewport viewport;
    private final SelectedCell selectedCell;

    public MainGameUi(final Viewport viewport, final StageData stageData) {
        stage = new Stage(viewport);
        this.viewport = viewport;
        selectedCell = new SelectedCell(viewport);

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
                if (stageData.enablePutUnit(pos)) {
                    GameLog.d("enablePutUnit");
                    selectedCell.setSelectedPosition(pos);
                    table.addAction(Actions.moveTo(300, 100, 0.2f));
                } else {
                    selectedCell.setSelected(false);
                    table.addAction(Actions.moveTo(300, -100, 0.2f));
                }

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

    public void act(float delta) {
        stage.act(delta);
    }

    public void draw() {
        stage.draw();
        SpriteBatch batch = (SpriteBatch) stage.getBatch();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (selectedCell.isSelected()) {
            selectedCell.draw(batch);
        }
        batch.end();
    }

}
