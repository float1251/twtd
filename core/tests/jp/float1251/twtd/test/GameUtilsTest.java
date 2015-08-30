package jp.float1251.twtd.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import jp.float1251.twtd.data.WaveData;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.util.GameUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by takahiro iwatani on 2015/05/26.
 */
public class GameUtilsTest {

    @Test
    public void testWorldToCellPosition() {
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

    @Test
    public void testCreateWaveDate() {
        String text = null;
        try {
            text = readFile("tests/resources/wave_data.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        WaveData res = GameUtils.createWaveData(text);
        assertNotNull(res);
        assertEquals(res.getDataList().size(), 2);
        assertEquals(res.getDataList().get(0).life, 1);
        assertEquals(res.getDataList().get(0).speed, 2, 0);
        assertEquals(res.getDataList().get(0).time, 3.5, 0);
        assertEquals(res.getDataList().get(0).deltaTime, 0, 0);
        assertEquals(res.getDataList().get(0).total, 1, 0);
    }

    @Test
    public void createStageWaveData() throws Exception {
        String text = readFile("tests/resources/stage.json");
        String[] pathes= GameUtils.createStageWaveDataPathArray(text);
        assertEquals(pathes.length, 2);
    }

    private static String readFile(String path) throws Exception {
        File a = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(a));
        String str = br.readLine();
        String json = "";
        while (str != null) {
            json += str;
            str = br.readLine();
        }
        br.close();
        return json;
    }

}
