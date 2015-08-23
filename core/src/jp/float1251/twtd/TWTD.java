package jp.float1251.twtd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.twtd.screen.TitleScreen;

public class TWTD extends Game {

    @Override
    public void create() {
        setScreen(new TitleScreen(this));
    }

}
