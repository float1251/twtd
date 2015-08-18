package jp.float1251.twtd.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import org.junit.Assert;
import org.junit.Test;

import jp.float1251.twtd.ecs.UnitFactory;
import jp.float1251.twtd.ecs.component.PositionComponent;
import jp.float1251.twtd.ecs.component.UnitComponent;

/**
 * Created by t-iwatani on 2015/08/18.
 */
public class UnitFactoryTest {

    @Test
    public void testCreateUnit(){
        Entity entity = UnitFactory.createUnit(new Vector2());
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getComponent(PositionComponent.class));
        Assert.assertNotNull(entity.getComponent(UnitComponent.class));
        // textureはtestしない。workingディレクトリとかが違うのかtextureの読み込みでErrorとなるので
        // Assert.assertNotNull(entity.getComponent(RenderingComponent.class));
    }

}
