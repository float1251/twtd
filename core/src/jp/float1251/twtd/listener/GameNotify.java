package jp.float1251.twtd.listener;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.HashMap;

import jp.float1251.twtd.GameLog;

/**
 * engineにobserverの役割をもたせる.
 * Created by t-iwatani on 2015/08/22.
 */
public class GameNotify {

    private final HashMap<String, ArrayList<Runnable>> listeners;
    private ArrayList<IEnemyEventListener> enemyEventListenerList = new ArrayList<>();
    private ArrayList<IColliderListener> colliderListenerList = new ArrayList<>();

    public GameNotify() {
        listeners = new HashMap<>();
    }

    public void addListener(String msg, Runnable callback) {
        if (!listeners.containsKey(msg)) {
            listeners.put(msg, new ArrayList<Runnable>());
        }
        listeners.get(msg).add(callback);
    }

    public void removeAllListener(String msg) {
        if (listeners.containsKey(msg))
            listeners.get(msg).clear();
    }

    public int getListenerCount(String msg) {
        if (!listeners.containsKey(msg)) {
            listeners.put(msg, new ArrayList<Runnable>());
        }
        return listeners.get(msg).size();
    }

    public void addEnemyEventListener(IEnemyEventListener o) {
        enemyEventListenerList.add(o);
    }

    public void sendMessage(String msg, Object... args) {
        GameLog.d(msg);
        if(!listeners.containsKey(msg))
            return;
        for (Runnable a : listeners.get(msg)) {
            a.run(args);
        }
    }

    public void sendMessage(String msg) {
        if(!listeners.containsKey(msg))
            return;
        for (Runnable a : listeners.get(msg)) {
            a.run();
        }
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

    public interface Runnable {
        void run(Object... args);
    }

}
