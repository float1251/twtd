package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by t-iwatani on 2015/08/20.
 */
public class BulletComponent extends Component {

    public Vector2 startPoint = new Vector2();
    /**
     * 射程範囲
     * 範囲外になったら削除される.
     */
    public float range;
    public float power = 2f;

    /**
     * この弾に当たったら遅くなるか
     */
    public boolean slow = false;

    public float slowTime = 0f;

    /**
     * ヒット時に取り除かれるか.
     */
    public boolean isRemovedWhenHit = true;
}
