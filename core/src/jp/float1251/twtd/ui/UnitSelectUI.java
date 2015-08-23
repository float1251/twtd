package jp.float1251.twtd.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.util.ArrayList;

import jp.float1251.twtd.ecs.component.UnitComponent;

/**
 * 4つのunitを選択するUIを作成する
 * Created by t-iwatani on 2015/08/23.
 */
public class UnitSelectUI extends VisTable {


    public UnitSelectUI(ArrayList<UnitComponent> dataList, IUnitSelectListener listener) {
        if (dataList.size() != 4) {
            new RuntimeException("UnitData must be added 4 data");
        }
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
        b1.setUserObject(dataList.get(0));
        b1.addListener(new UnitButtonListener(b1, listener));
        b2.setUserObject(dataList.get(1));
        b2.addListener(new UnitButtonListener(b2, listener));
        b3.setUserObject(dataList.get(2));
        b3.addListener(new UnitButtonListener(b3, listener));
        b4.setUserObject(dataList.get(3));
        b4.addListener(new UnitButtonListener(b4, listener));
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

    interface IUnitSelectListener {
        void onSelected(UnitComponent data);
    }

    private class UnitButtonListener extends ClickListener {

        private final Button button;
        private final UnitSelectUI.IUnitSelectListener listener;

        public UnitButtonListener(Button button, IUnitSelectListener listener) {
            this.button = button;
            this.listener = listener;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            listener.onSelected((UnitComponent) button.getUserObject());
        }
    }
}
