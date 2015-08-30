package jp.float1251.twtd.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jp.float1251.twtd.asset.AssetLoader;

/**
 * Created by t-iwatani on 2015/08/29.
 */
public class CustomTextLabel extends Actor{

    protected final BitmapFont font;
    protected final GlyphLayout layout;
    private String text;

    public CustomTextLabel(String text){
        this.font = AssetLoader.getInstance().get("size50.ttf", BitmapFont.class);
        // http://stackoverflow.com/questions/16600547/how-get-a-string-width-in-libgdx
        this.layout = new GlyphLayout(font, text);
        this.text = text;
    }

    public void setText(String text){
        this.layout.setText(font, text);
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public float getTextWidth(){
        return this.layout.width;
    }

    @Override
    public float getWidth() {
        return this.layout.width;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, getX(), getY());
    }
}
