package jp.float1251.twtd.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.component.AttackComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.VelocityComponent;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class BulletFactory {

    public static Entity createBullet(Vector2 pos, Vector2 dir){
        Entity bullet = new Entity();
        bullet.add(new AttackComponent(2));
        bullet.add(new PositionComponent(pos));
        bullet.add(new RenderingComponent(new Texture("enemy.png")));
        VelocityComponent vel = new VelocityComponent();
        vel.direction.set(dir);
        bullet.add(vel);
        return bullet;
    }
}