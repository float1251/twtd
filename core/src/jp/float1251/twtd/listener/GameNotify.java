package jp.float1251.twtd.listener;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * engineにobserverの役割をもたせる.
 * Created by t-iwatani on 2015/08/22.
 */
public class GameNotify {

    private final HashMap<String, ArrayList<Runnable>> listners;
    private ArrayList<IEnemyEventListener> enemyEventListenerList = new ArrayList<>();
    private ArrayList<IColliderListener> colliderListenerList = new ArrayList<>();

    public GameNotify() {
        listners = new HashMap<>();
    }

    public void addListener(String msg, Runnable callback) {
        if (!listners.containsKey(msg)) {
            listners.put(msg, new ArrayList<Runnable>());
        }
        listners.get(msg).add(callback);
    }

    public void removeAllListener(String msg) {
        if (listners.containsKey(msg))
            listners.get(msg).clear();
    }

    public int getListnerCount(String msg) {
        if (!listners.containsKey(msg)) {
            listners.put(msg, new ArrayList<Runnable>());
        }
        return listners.get(msg).size();
    }

    public void addEnemyEventListner(IEnemyEventListener o) {
        enemyEventListenerList.add(o);
    }

    public void sendMessage(String msg, Object... args) {
        for (Runnable a : listners.get(msg)) {
            a.run(args);
        }
    }

    public void sendMessage(String msg) {
        for (Runnable a : listners.get(msg)) {
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
