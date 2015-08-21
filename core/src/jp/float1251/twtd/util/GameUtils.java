package jp.float1251.twtd.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;

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
        return new Vector2((int) x / 64, (int) y / 64);
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
}
