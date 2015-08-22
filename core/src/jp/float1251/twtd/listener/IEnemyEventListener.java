package jp.float1251.twtd.listener;

import com.badlogic.ashley.core.Entity;

/**
 * Created by t-iwatani on 2015/08/22.
 */
public interface IEnemyEventListener {
    void onDestroyEnemy(Entity e);

    void onReachEnd(Entity e);
}
