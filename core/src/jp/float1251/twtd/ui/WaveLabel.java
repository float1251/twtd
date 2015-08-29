package jp.float1251.twtd.ui;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by t-iwatani on 2015/08/27.
 */
public class WaveLabel extends CustomTextLabel {

    public WaveLabel(String text) {
        super(text);
    }

    public void show(final Runnable onComplete) {
        setVisible(true);
        float width = getStage().getViewport().getCamera().viewportWidth;
        float height = getStage().getViewport().getCamera().viewportHeight;
        setPosition(-layout.width, height / 2);
        addAction(Actions.sequence(
                Actions.moveTo(width / 2 - layout.width / 2, height / 2, 1),
                Actions.delay(1),
                Actions.moveTo(width + layout.width, height / 2, 1),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                        if (onComplete != null)
                            onComplete.run();
                    }
                })
        ));
    }

    public void hide() {
        setVisible(false);
    }

}
