package jp.float1251.twtd.ecs.component;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class RenderingComponent extends Component {
    public Texture texture;

    public RenderingComponent(Texture texture) {
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public int width;
    public int height;
}
