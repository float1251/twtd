package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.BulletFactory;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class UnitComponent extends Component {
    /**
     * 攻撃力
     */
    public float power = 0f;

    /**
     * 攻撃間隔
     */
    public float interval = 1f;

    /**
     * 攻撃の射程範囲
     */
    public float range = 100f;

    /**
     * 弾のスピード
     */
    public float speed = 1f;

    /**
     * コスト
     */
    public int cost = 0;

    public String texture = "cell/cell_unit.png";

    /**
     *
     */
    public UnitType type = UnitType.RIFLE;

    public float slowTime = 0f;

    public enum UnitType {
        RIFLE,
        SLOW,
        RANGE_ATTACK
    }

    private float time;

    public UnitComponent() {

    }

    public UnitComponent(UnitComponent data) {
        power = data.power;
        interval = data.interval;
        range = data.range;
        speed = data.speed;
        type = data.type;
        slowTime = data.slowTime;
    }

    public void addTime(float time) {
        this.time += time;
    }

    public boolean shouldShoot() {
        return time >= interval;
    }

    public void shotIfNeeded(Engine engine, Vector2 pos, Vector2 dir) {
        if (time < interval)
            return;
        engine.addEntity(BulletFactory.createBullet(pos, dir, power, range, speed, type == UnitType.SLOW, slowTime, type == UnitType.RANGE_ATTACK));
        time = 0f;
    }
}
