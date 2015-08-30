package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import jp.float1251.twtd.ecs.component.EnemyComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;
import jp.float1251.twtd.listener.GameNotify;

/**
 * 敵の移動と削除を行う.
 * Created by takahiro iwatani on 2015/06/02.
 */
public class EnemySystem extends IteratingSystem {
    // 移動path
    private final Array<PolylineMapObject> path;
    private final GameNotify notify;
    private Engine engine;

    public EnemySystem(Array<PolylineMapObject> path, GameNotify notify) {
        super(Family.all(PositionComponent.class, VelocityComponent.class, EnemyComponent.class).get());
        this.path = path;
        this.notify = notify;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnemyComponent e = entity.getComponent(EnemyComponent.class);
        if (e.life <= 0) {
            notify.onDestroyEnemy(entity);
            engine.removeEntity(entity);
            return;
        }

        PositionComponent p = entity.getComponent(PositionComponent.class);
        VelocityComponent v = entity.getComponent(VelocityComponent.class);
        // 目標点
        float[] tmp = path.get(e.pathIndex).getPolyline().getTransformedVertices();
        Vector2 s = new Vector2(tmp[v.verticesIndex],
                tmp[v.verticesIndex + 1]);
        // 一定距離まで来たら、次のpositionを目指す
        if (s.dst(p.position) < 3) {
            p.position.set(s);
            v.verticesIndex += 2;
            // pathのlength以上になったら最終目標点に到達したので、removeする。
            if (v.verticesIndex >= tmp.length) {
                engine.removeEntity(entity);
                if (notify != null)
                    notify.onReachEnd(entity);
            }
        }
        float speed = e.slow ? v.speed / 3.0f : v.speed;
        p.position.add(s.sub(p.position).nor().scl(speed));
        e.update(deltaTime);
    }


}
