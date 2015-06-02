package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by takahiro iwatani on 2015/06/02.
 */
public class VelocityComponent extends Component {
    public float velocity = 1f;
    public int verticesIndex = 2; // polylineがfloat[]で偶数, 奇数がx, yとなっているため. 0, 1は0, 0.
}
