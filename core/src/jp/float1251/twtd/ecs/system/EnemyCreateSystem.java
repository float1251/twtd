package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class EnemyCreateSystem extends EntitySystem {

    // 出現地点
    private final Vector2 respawnPos;
    // 時間
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

        // 仮で2秒ごと程度で敵を出現させる
        total += deltaTime;
        if (total >= 2) {
            total = 0;
            createEnemy(respawnPos);
        }
    }

    public Texture img = new Texture("enemy.png");
    public void createEnemy(Vector2 respawnPos) {
        GameLog.d("createEnemy");
        Entity enemy = new Entity();
        enemy.add(new PositionComponent(respawnPos));
        enemy.add(new VelocityComponent());
        enemy.add(new EnemyComponent());
        enemy.add(new RenderingComponent(img));
        engine.addEntity(enemy);
    }
}
