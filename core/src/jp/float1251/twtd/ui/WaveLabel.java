package jp.float1251.twtd.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import jp.float1251.twtd.asset.AssetLoader;

/**
 * Created by t-iwatani on 2015/08/27.
 */
public class WaveLabel extends Actor {

    private final BitmapFont font;
    private String text;
    private GlyphLayout layout;

    public WaveLabel() {
        this.font = AssetLoader.getInstance().get("size50.ttf", BitmapFont.class);
        // http://stackoverflow.com/questions/16600547/how-get-a-string-width-in-libgdx
        this.layout = new GlyphLayout(font, "");
    }

    public void setText(String text) {
        this.text = text;
        this.layout.setText(font, text);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, getX(), getY());
    }
}
