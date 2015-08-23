package jp.float1251.twtd.listener;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

import jp.float1251.twtd.listener.IEnemyEventListener;

/**
 * engineにobserverの役割をもたせる.
 * Created by t-iwatani on 2015/08/22.
 */
public class GameNotify {

    private ArrayList<IEnemyEventListener> enemyEventListenerList = new ArrayList<>();
    private ArrayList<IColliderListener> colliderListenerList = new ArrayList<>();

    public void addEnemyEventListner(IEnemyEventListener o) {
        enemyEventListenerList.add(o);
    }

    public void addColliderListener(IColliderListener o) {
        colliderListenerList.add(o);
    }

    public void onDestroyEnemy(Entity e) {
        for (IEnemyEventListener i : enemyEventListenerList) {
            i.onDestroyEnemy(e);
        }
    }

    public void onReachEnd(Entity e) {
        for (IEnemyEventListener i : enemyEventListenerList) {
            i.onReachEnd(e);
        }
    }

    public void onCollision(Entity e, Entity e2) {
        for (IColliderListener i : colliderListenerList) {
            i.onCollision(e, e2);
        }
    }

}
