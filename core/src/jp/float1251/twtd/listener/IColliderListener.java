package jp.float1251.twtd.listener;

import com.badlogic.ashley.core.Entity;

/**
 * Created by t-iwatani on 2015/08/22.
 */
public interface IColliderListener {
    void onCollision(Entity e1, Entity e2);
}
