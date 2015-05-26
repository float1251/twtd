package jp.float1251.twtd.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import jp.float1251.twtd.util.GameUtils;

/**
 * Created by takahiro iwatani on 2015/05/26.
 */
public class SelectedCell implements Disposable {
    private final Viewport viewport;
    Vector2 position = new Vector2();
    Texture img;
    boolean selected = false;

    public SelectedCell(Viewport viewport) {
        img = new Texture("alpha_cell.png");
        this.viewport = viewport;
    }

    @Override
    public void dispose() {
        img.dispose();
    }

    public void setSelectedPosition(Vector3 worldPos) {
        // convert
        position.set(GameUtils.worldToCellPosition(worldPos.x, worldPos.y));
        this.selected = true;
    }

    public Vector2 getSelectedCellPos() {
        return position;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(img, position.x * 64, position.y * 64);
    }
}
