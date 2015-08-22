package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;


/**
 * Created by t-iwatani on 2015/08/21.
 */
public class CircleColliderComponent extends Component{
    public float radius = 0f;

    /**
     * 削除するかどうか
     */
    public boolean isRemove = false;

    public CircleColliderComponent(float radius){
        this.radius = radius;
    }

}
