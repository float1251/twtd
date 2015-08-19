package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;
import jp.float1251.twtd.game.EnemyManager;

/**
 * Created by takahiro iwatani on 2015/06/02.
 */
public class EnemyMovementSystem extends IteratingSystem {
    // 移動path
    private final Polyline path;
    private Engine engine;

    public EnemyMovementSystem(Polyline path) {
        super(Family.all(PositionComponent.class, VelocityComponent.class, EnemyComponent.class).get());
        this.path = path;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent p = entity.getComponent(PositionComponent.class);
        VelocityComponent v = entity.getComponent(VelocityComponent.class);
        // 目標点
        Vector2 s = new Vector2(path.getTransformedVertices()[v.verticesIndex],
                path.getTransformedVertices()[v.verticesIndex + 1]);
        // 一定距離まで来たら、次のpositionを目指す
        if (s.dst(p.position) < 3) {
            p.position.set(s);
            v.verticesIndex += 2;
            // pathのlength以上になったら最終目標点に到達したので、removeする。
            if (v.verticesIndex >= path.getTransformedVertices().length) {
                EnemyManager.removeEnemy(engine, entity);
            }
        }
        p.position.add(s.sub(p.position).nor().scl(v.speed));
    }
}
