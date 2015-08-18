package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiro iwatani on 2015/06/02.
 */
public class VelocityComponent extends Component {
    /**
     * 速さ
     */
    public float speed = 1f;

    /**
     * 方向ベクトル
     */
    public Vector2 direction = new Vector2();

    /**
     * 敵の移動の際に使用する.
     */
    public int verticesIndex = 2; // polylineがfloat[]で偶数, 奇数がx, yとなっているため. 0, 1は0, 0.
}
