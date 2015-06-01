package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class PositionComponent extends Component {
    public Vector2 position;

    public PositionComponent(Vector2 respawnPos) {
        position = respawnPos.cpy();
    }
}
