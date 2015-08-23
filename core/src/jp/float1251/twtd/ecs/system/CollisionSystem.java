package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.listener.GameNotify;
import jp.float1251.twtd.util.GameUtils;

/**
 * Created by t-iwatani on 2015/08/21.
 */
public class CollisionSystem extends EntitySystem {

    private final GameNotify notify;
    private Engine engine;

    public CollisionSystem(GameNotify notify) {
        this.notify = notify;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(CircleColliderComponent.class).get());
        int start = 0;
        // 全件判定する
        for (Entity e : entities) {
            Circle c1 = GameUtils.createCircle(e);
            CircleColliderComponent cc1 = e.getComponent(CircleColliderComponent.class);
            for (int i = start; i < entities.size(); i++) {
                Entity e2 = entities.get(i);
                if (e.equals(e2)) {
                    continue;
                }
                Circle c2 = GameUtils.createCircle(e2);
                CircleColliderComponent cc2 = e2.getComponent(CircleColliderComponent.class);
                // 3つにあたっていると思っていたが、違う？？
                if (Intersector.overlaps(c1, c2) && !cc1.isRemove && !cc2.isRemove) {
                    notify.onCollision(e, e2);
                }
            }
            // 削除フラグが立っていたら削除する
            if (cc1.isRemove) {
                engine.removeEntity(e);
            }
            start++;
        }
    }
}