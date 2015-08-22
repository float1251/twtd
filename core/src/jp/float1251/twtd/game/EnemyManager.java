package jp.float1251.twtd.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;
import jp.float1251.twtd.ecs.component.EnemyComponent;

/**
 * Created by t-iwatani on 2015/08/19.
 */
public class EnemyManager {

    public static Texture img = new Texture("enemy.png");

    public static ArrayList<Entity> enemyList = new ArrayList<>();

    private EnemyManager() {

    }

    public static Entity createEnemy(Vector2 respawnPos) {
        Entity enemy = new Entity();
        enemy.add(new PositionComponent(respawnPos));
        enemy.add(new VelocityComponent());
        enemy.add(new EnemyComponent());
        enemy.add(new RenderingComponent(img));
        enemy.add(new CircleColliderComponent(30f));
        enemyList.add(enemy);
        return enemy;
    }

    public static void removeEnemy(Engine engine, Entity enemy) {
        enemyList.remove(enemy);
        engine.removeEntity(enemy);
    }


    /**
     * posから一番近くにいるEnemyを返す
     *
     * @param pos
     * @return
     */
    public static Entity getMinDistanceEnemy(Vector2 pos) {
        float min = Float.MAX_VALUE;
        if (enemyList.isEmpty()) {
            return null;
        }
        Entity res = null;
        for (Entity enemy : enemyList) {
            float dst = enemy.getComponent(PositionComponent.class).position.dst(pos);
            if (min > dst) {
                min = dst;
                res = enemy;
            }
        }
        return res;
    }
}
