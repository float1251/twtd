package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.UnitComponent;
import jp.float1251.twtd.game.EnemyManager;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class UnitSystem extends IteratingSystem {

    private Engine engine;

    public UnitSystem() {
        super(Family.all(PositionComponent.class, UnitComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = entity.getComponent(PositionComponent.class);
        UnitComponent uc = entity.getComponent(UnitComponent.class);
        uc.addTime(deltaTime);
        if (uc.shouldShoot()) {
            // TODO EngineからEnemyをすべて取ってきて、ターゲットを決める
            // ターゲットは距離が一番近い敵にする
            Entity enemy = EnemyManager.getMinDistanceEnemy(pos.position);
            PositionComponent target = enemy.getComponent(PositionComponent.class);
            Vector2 angle = target.position.cpy().sub(pos.position).nor();
            uc.shotIfNeeded(engine, pos.position, angle);
        }
    }
}
