package jp.float1251.twtd.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.EnemyComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by t-iwatani on 2015/08/19.
 */
public class EnemyManager {

    public static final Texture img = new Texture("enemy.png");

    private EnemyManager() {

    }

    public static Entity createEnemy(Vector2 respawnPos) {
        Entity enemy = new Entity();
        enemy.add(new PositionComponent(respawnPos));
        enemy.add(new VelocityComponent());
        enemy.add(new EnemyComponent());
        enemy.add(new RenderingComponent(img));
        enemy.add(new CircleColliderComponent(30f));
        return enemy;
    }

    public static Entity createEnemy(Vector2 pos, float life, float speed,int pathIndex){
        Entity enemy = new Entity();
        enemy.add(new PositionComponent(pos));
        enemy.add(new VelocityComponent(speed));
        enemy.add(new EnemyComponent(life, pathIndex));
        enemy.add(new RenderingComponent(img));
        enemy.add(new CircleColliderComponent(30f));
        return enemy;
    }

    /**
     * posから一番近くにいるEnemyを返す
     *
     * @param pos
     * @return
     */
    public static Entity getMinDistanceEnemy(Vector2 pos, Engine engine) {
        ImmutableArray<Entity> list = engine.getEntitiesFor(Family.all(EnemyComponent.class).get());
        float min = Float.MAX_VALUE;
        Entity res = null;
        for (Entity enemy : list) {
            float dst = enemy.getComponent(PositionComponent.class).position.dst(pos);
            if (min > dst) {
                min = dst;
                res = enemy;
            }
        }
        return res;
    }
}
