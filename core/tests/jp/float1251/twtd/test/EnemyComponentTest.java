package jp.float1251.twtd.test;

import org.junit.Test;

import jp.float1251.twtd.ecs.component.EnemyComponent;

import static org.junit.Assert.*;

/**
 * Created by t-iwatani on 2015/08/29.
 */
public class EnemyComponentTest {

    @Test
    public void startSlow() {
        EnemyComponent com = new EnemyComponent();
        com.startSlow(3.2f);
        assertTrue(com.slow);
        assertEquals(com.slowTime, 3.2f, 0);
    }

    @Test
    public void update() {
        EnemyComponent com = new EnemyComponent();
        com.startSlow(3.2f);
        assertTrue(com.slow);
        com.update(3f);
        assertTrue(com.slow);
        com.update(2f);
        assertFalse(com.slow);
    }
}
