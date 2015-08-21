package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;


/**
 * Created by t-iwatani on 2015/08/21.
 */
public class CircleColliderComponent extends Component{
    public final ICollision callback;
    public float radius = 0f;

    public CircleColliderComponent(float radius, ICollision callback){
        this.radius = radius;
        this.callback = callback;
    }

    public interface ICollision{
        void onCollision(Entity mine, Entity other);
    }

}
