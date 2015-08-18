package jp.float1251.twtd.ecs.component;

import com.badlogic.ashley.core.Component;

/**
 * 攻撃を与えるものに付加するcomponent
 * Created by t-iwatani on 2015/08/18.
 */
public class AttackComponent extends Component {
    public float power;

    public AttackComponent(float power) {
        this.power = power;
    }
}
