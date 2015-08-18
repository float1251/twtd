package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.ecs.BulletFactory;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class UnitComponent extends Component{
    /**
     * 攻撃力
     */
    public float power = 0f;

    /**
     * 攻撃間隔
     */
    public float interval = 2f;

    /**
     *
     */
    public UnitType type = UnitType.RIFLE;

    public enum UnitType{
        RIFLE;
    }

    private float time;

    public void addTime(float time){
        this.time += time;
    }

    public void shotIfNeeded(Engine engine, Vector2 pos, Vector2 dir){
        if(time < interval)
            return;
        engine.addEntity(BulletFactory.createBullet(pos, dir));
        time = 0f;
    }
}
