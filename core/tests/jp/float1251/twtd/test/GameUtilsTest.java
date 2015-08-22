package jp.float1251.twtd.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.util.GameUtils;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by takahiro iwatani on 2015/05/26.
 */
public class GameUtilsTest {

    @Test
    public void testScreenToCellPosition() {
        Vector2 pos = GameUtils.worldToCellPosition(0, 0);
        assertThat(pos.x, is(0f));

        pos = GameUtils.worldToCellPosition(65, 0);
        assertThat(pos.x, is(1f));
    }

    @Test
    public void testCreateCircle() {
        Circle c = GameUtils.createCircle(new Entity());
        assertNull(c);
        Entity e = new Entity();
        e.add(new CircleColliderComponent(5));
        e.add(new PositionComponent(1, 2));
        c = GameUtils.createCircle(e);
        assertEquals(c.radius, 5f, 0);
        assertEquals(c.x, 1, 0);
        assertEquals(c.y, 2, 2);
    }
}
