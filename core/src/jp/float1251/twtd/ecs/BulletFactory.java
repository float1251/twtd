package jp.float1251.twtd.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.asset.AssetLoader;
import jp.float1251.twtd.ecs.component.BulletComponent;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class BulletFactory {

    public static Entity createBullet(Vector2 pos, Vector2 dir, float power, float range, float speed, boolean slow, float slowTime, boolean rangeAttack) {
        Entity bullet = new Entity();
        bullet.add(new PositionComponent(pos));
        BulletComponent b = new BulletComponent();
        b.power = power;
        b.startPoint.set(pos);
        b.range = range;
        b.slow = slow;
        b.slowTime = slowTime;
        b.isRemovedWhenHit = !rangeAttack;
        bullet.add(b);
        RenderingComponent rc = new RenderingComponent(AssetLoader.getInstance().get("bullet.png", Texture.class));
        rc.width = 20 * 2;
        rc.height = 20 * 2;
        bullet.add(rc);
        VelocityComponent vel = new VelocityComponent();
        vel.speed = speed;
        vel.direction.set(dir);
        bullet.add(vel);
        bullet.add(new CircleColliderComponent(18f));
        return bullet;
    }
}
