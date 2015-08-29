package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class EnemyComponent extends Component {
    public float life = 10f;
    public boolean slow = false;
    /**
     * 動きが遅い時間
     */
    public float slowTime = 0;

    private float time;

    public EnemyComponent() {

    }

    public void startSlow(float slowTime){
        this.slow = true;
        this.time = 0f;
        this.slowTime = slowTime;
    }

    public void update(float deltaTime){
        this.time += deltaTime;
        if(time >= slowTime){
            slow = false;
        }
    }

    public EnemyComponent(float life) {
        this.life = life;
    }
}
