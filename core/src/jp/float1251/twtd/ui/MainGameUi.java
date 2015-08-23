package jp.float1251.twtd.ui;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.StageData;
import jp.float1251.twtd.data.PlayerData;
import jp.float1251.twtd.ecs.UnitFactory;
import jp.float1251.twtd.game.SelectedCell;

/**
 * Created by takahiro iwatani on 2015/05/31.
 */
public class MainGameUi {

    private final Stage stage;
    private final Viewport viewport;
    private final SelectedCell selectedCell;
    private final Engine engine;
    private final PlayerData data;
    private final VisLabel label;
    private final UnitSelectUI unitSelectUI;

    public MainGameUi(final Viewport viewport, final StageData stageData, final Engine engine, PlayerData data) {
        stage = new Stage(viewport);
        this.engine = engine;
        this.viewport = viewport;
        this.data = data;
        selectedCell = new SelectedCell(viewport);

        VisUI.load();

        unitSelectUI = new UnitSelectUI(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedCell.setSelected(false);
                // positionは画像の左下だからcellの真ん中に表示させるためにずらす
                // 画像のrenderingはimageのcenterをpivotとして行っている
                Entity unit = UnitFactory.createUnit(selectedCell.getWorldPosition().add(32, 32));
                engine.addEntity(unit);
                unitSelectUI.hide();
            }
        });
        stage.addActor(unitSelectUI);

        label = new VisLabel();
        label.setPosition(0, viewport.getCamera().viewportHeight - 30);
        stage.addActor(label);
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
                GameLog.d(String.format("Screen: %d, %d", screenX, screenY));
                Vector3 pos = viewport.unproject(new Vector3(screenX, screenY, 0));
                GameLog.d(String.format("World: %f, %f", pos.x, pos.y));
                if (stageData.enablePutUnit(pos)) {
                    GameLog.d("enablePutUnit");
                    selectedCell.setSelectedPosition(pos);
                    unitSelectUI.show(selectedCell.getWorldPosition());
                } else {
                    selectedCell.setSelected(false);
                    unitSelectUI.hide();
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
        label.setText("Coin: " + (int) data.coin);
        SpriteBatch batch = (SpriteBatch) stage.getBatch();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        stage.draw();
        batch.begin();
        if (selectedCell.isSelected()) {
            selectedCell.draw(batch);
        }
        batch.end();
    }

}
