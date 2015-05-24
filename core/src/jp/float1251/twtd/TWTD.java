package jp.float1251.twtd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.twtd.screen.TitleScreen;

public class TWTD extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		setScreen(new TitleScreen(this));
	}

}
