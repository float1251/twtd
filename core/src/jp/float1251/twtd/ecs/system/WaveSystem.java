package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.data.EnemySpawnData;
import jp.float1251.twtd.data.WaveData;
import jp.float1251.twtd.game.EnemyManager;
import jp.float1251.twtd.listener.GameNotify;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class WaveSystem extends EntitySystem {

    // 出現地点
    private final Vector2 respawnPos;
    private final GameNotify notify;
    private final WaveData data;
    // 時間
    private float time;
    private float enemyDeltaTime;
    // 出現数
    private int total;
    private Engine engine;
    private int index;

    public WaveSystem(Vector2 respawnPos, WaveData data, GameNotify notify) {
        this.respawnPos = respawnPos;
        this.notify = notify;
        this.data = data;
        init();
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (index >= data.getDataList().size()) {
            // TODO 敵を全滅したらwave終了のイベントを呼ぶ
            return;
        }

        time += deltaTime;

        // 出現時間を超えていたら出現させる
        EnemySpawnData ed = data.getDataList().get(index);
        if (ed.time <= time) {
            if (ed.deltaTime <= enemyDeltaTime) {
                Entity enemy = EnemyManager.createEnemy(respawnPos, ed.life, ed.speed);
                engine.addEntity(enemy);
                enemyDeltaTime = 0f;
                total++;

                GameLog.d(String.format("%d, %f, %f", index, time, enemyDeltaTime));
                if (ed.total <= total) {
                    enemyDeltaTime = 0f;
                    total = 0;
                    index++;
                }
            }
            this.enemyDeltaTime += deltaTime;
        }

    }

    public void init() {
        this.index = 0;
        this.time = 0f;
        this.enemyDeltaTime = 0f;
    }

}
