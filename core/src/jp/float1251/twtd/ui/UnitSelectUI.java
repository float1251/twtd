package jp.float1251.twtd.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * 4つのunitを選択するUIを作成する
 * Created by t-iwatani on 2015/08/23.
 */
public class UnitSelectUI extends VisTable {


    public UnitSelectUI(ClickListener listener) {
        VisTextButton b1 = createButton("unit1: 100");
        add(b1).size(80, 80).padRight(80);
        VisTextButton b2 = createButton("unit2: 200");
        add(b2).size(80, 80);
        this.row();
        VisTextButton b3 = createButton("unit3: 300");
        add(b3).size(80, 80).padRight(80);
        VisTextButton b4 = createButton("unit3: 300");
        add(b4).size(80, 80);
        setDebug(true);
        b1.addListener(listener);
        b2.addListener(listener);
        b3.addListener(listener);
        b4.addListener(listener);
    }

    public void show(Vector2 pos) {
        setPosition(pos.x + 32, pos.y + 32);
        setVisible(true);
    }

    public void show(float x, float y) {
        setPosition(x, y);
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    private VisTextButton createButton(String text) {
        VisTextButton button = new VisTextButton(text);
        button.getStyle().font.getData().setScale(2f);
        return button;
    }
}
