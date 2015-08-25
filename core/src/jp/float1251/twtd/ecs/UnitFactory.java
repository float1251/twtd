package jp.float1251.twtd.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import jp.float1251.twtd.asset.AssetLoader;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.RenderingComponent;
import jp.float1251.twtd.ecs.component.UnitComponent;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class UnitFactory {
    public static Entity createUnit(Vector2 pos, UnitComponent data) {
        Entity entity = new Entity();
        entity.add(new PositionComponent(pos));
        entity.add(new UnitComponent(data));
        entity.add(new RenderingComponent(AssetLoader.getInstance().get(data.texture, Texture.class)));
        return entity;
    }

}
