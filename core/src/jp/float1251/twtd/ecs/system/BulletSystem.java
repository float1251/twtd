package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.BulletComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;

/**
 * Created by t-iwatani on 2015/08/20.
 */
public class BulletSystem extends IteratingSystem {
    private Engine engine;

    public BulletSystem() {
        super(Family.all(BulletComponent.class).get());
    }


    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO 敵との衝突判定

        // 発射位置からの距離をみて削除するかどうか判定する
        BulletComponent b = entity.getComponent(BulletComponent.class);
        Vector2 start = b.startPoint;
        PositionComponent pos = entity.getComponent(PositionComponent.class);
        float dst = pos.position.dst(start);
        if (dst >= b.range) {
            engine.removeEntity(entity);
        }
    }
}
