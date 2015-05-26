package jp.float1251.twtd.test;

import com.badlogic.gdx.math.Vector2;

import org.junit.Assert;
import org.junit.Test;

import jp.float1251.twtd.util.GameUtils;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by takahiro iwatani on 2015/05/26.
 */
public class GameUtilsTest {

    @Test
    public void testScreenToCellPosition() {
        Vector2 pos = GameUtils.worldToCellPosition(0, 0);
        Assert.assertThat(pos.x, is(0f));

        pos = GameUtils.worldToCellPosition(65, 0);
        Assert.assertThat(pos.x, is(1f));
    }

}
