package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Timer;
import java.util.TimerTask;

import jp.float1251.twtd.data.EnemySpawnData;
import jp.float1251.twtd.data.WaveData;
import jp.float1251.twtd.ecs.component.EnemyComponent;
import jp.float1251.twtd.game.EnemyManager;
import jp.float1251.twtd.listener.GameNotify;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class WaveSystem extends EntitySystem {

    // 出現地点
    private final Vector2 respawnPos;
    private final GameNotify notify;
    private WaveData data;
    // 時間
    private float time;
    // 敵の出現間隔の時間
    private float enemyDeltaTime;
    // 出現数
    private int total;
    private Engine engine;
    // enemyspawnDataのindex
    private int index;

    // waveが終了しているかどうか
    private boolean finishWave = false;

    public WaveSystem(Vector2 respawnPos, WaveData data, GameNotify notify) {
        this.respawnPos = respawnPos;
        this.notify = notify;
        this.data = data;
        init();
    }

    /**
     * waveDataを設定する.
     * 設定をしたらゲームが開始される.
     *
     * @param data
     */
    public void setWaveData(WaveData data) {
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
        if (finishWave)
            return;

        if (index >= data.getDataList().size()) {
            ImmutableArray<Entity> list = engine.getEntitiesFor(Family.all(EnemyComponent.class).get());
            if (list.size() == 0) {
                // 敵を全滅したらwave終了のイベントを呼ぶ
                finishWave = true;
                notify.sendMessage("onWaveEnd");
            }
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
        this.finishWave = false;
    }

}