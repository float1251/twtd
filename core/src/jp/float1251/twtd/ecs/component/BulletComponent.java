package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by t-iwatani on 2015/08/20.
 */
public class BulletComponent extends Component {

    public Vector2 startPoint = new Vector2();
    public float range;
    public float power = 2f;

}
