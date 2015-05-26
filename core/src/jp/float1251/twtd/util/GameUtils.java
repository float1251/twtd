package jp.float1251.twtd.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiro iwatani on 2015/05/26.
 */
public class GameUtils {
    private GameUtils() {

    }

    /**
     * @return
     */
    public static Vector2 worldToCellPosition(float x, float y) {
        return new Vector2((int)x / 64, (int)y / 64);
    }


}
