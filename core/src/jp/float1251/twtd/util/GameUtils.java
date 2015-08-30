package jp.float1251.twtd.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import jp.float1251.twtd.data.EnemySpawnData;
import jp.float1251.twtd.data.WaveData;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.UnitComponent;

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


    public static Vector2 getXY(MapObject obj) {
        float x = obj.getProperties().get("x", Float.class);
        float y = obj.getProperties().get("y", Float.class);
        return new Vector2(x, y);
    }

    public static Rectangle getRectangle(MapObject obj) {
        Vector2 pos = getXY(obj);
        float width = obj.getProperties().get("width", Float.class);
        float height = obj.getProperties().get("height", Float.class);
        return new Rectangle(pos.x, pos.y, width, height);
    }

    public static Circle createCircle(Entity entity) {
        PositionComponent pos = entity.getComponent(PositionComponent.class);
        CircleColliderComponent cc = entity.getComponent(CircleColliderComponent.class);

        if (pos == null || cc == null)
            return null;

        return new Circle(pos.position.x, pos.position.y, cc.radius);
    }

    public static WaveData createWaveData(String json) {
        WaveData wd = new WaveData();
        JsonValue jsonValue = new JsonReader().parse(json);
        int size = jsonValue.get("wave_data").size;
        JsonValue wave = jsonValue.get("wave_data");
        for (int i = 0; i < size; i++) {
            JsonValue jd = wave.get(i);
            EnemySpawnData data = new EnemySpawnData();
            data.time = jd.getFloat("time", 0);
            data.life = jd.getInt("life", 0);
            data.speed = jd.getFloat("speed", 0);
            data.deltaTime = jd.getFloat("delta_time", 0);
            data.total = jd.getInt("total", 0);
            data.pathIndex = jd.getInt("path_index", 0);
            wd.addEnemySpawnData(data);
        }
        return wd;
    }

    public static ArrayList<UnitComponent> createUnitList() {
        ArrayList<UnitComponent> list = new ArrayList<>();
        UnitComponent uc = new UnitComponent();
        uc.power = 1;
        uc.interval = 0.1f;
        uc.range = 100f;
        uc.speed = 2f;
        uc.cost = 10;
        uc.texture = "cell/cell_unit.png";
        list.add(uc);
        uc = new UnitComponent();
        uc.power = 2;
        uc.interval = 1.0f;
        uc.range = 200f;
        uc.speed = 2f;
        uc.cost = 20;
        uc.type = UnitComponent.UnitType.SLOW;
        uc.slowTime = 5f;
        uc.texture = "cell/cell_unit_2.png";
        list.add(uc);
        uc = new UnitComponent();
        uc.power = 2;
        uc.interval = 0.5f;
        uc.range = 300f;
        uc.speed = 3f;
        uc.cost = 30;
        uc.texture = "cell/cell_unit_3.png";
        list.add(uc);
        uc = new UnitComponent();
        uc.power = 5;
        uc.interval = 2.0f;
        uc.range = 400f;
        uc.cost = 40;
        uc.texture = "cell/cell_unit_4.png";
        list.add(uc);
        return list;
    }
}
