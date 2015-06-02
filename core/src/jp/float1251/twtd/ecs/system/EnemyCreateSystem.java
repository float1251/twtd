package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class EnemyCreateSystem extends EntitySystem {

    private final Vector2 respawnPos;
    private float total;
    private Engine engine;

    public EnemyCreateSystem(Vector2 respawnPos) {
        this.respawnPos = respawnPos;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        total += deltaTime;
        if (total >= 2) {
            total = 0;
            createEnemy(respawnPos);
        }
    }

    public void createEnemy(Vector2 respawnPos) {
        GameLog.d("createEnemy");
        Entity enemy = new Entity();
        enemy.add(new PositionComponent(respawnPos));
        enemy.add(new VelocityComponent());
        engine.addEntity(enemy);
    }
}
