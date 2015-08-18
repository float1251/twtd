package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class MoveSystem extends IteratingSystem {

    public MoveSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).exclude(EnemyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pc = entity.getComponent(PositionComponent.class);
        VelocityComponent vc = entity.getComponent(VelocityComponent.class);
        pc.position.add(vc.direction.cpy().nor().scl(vc.speed));
    }
}
