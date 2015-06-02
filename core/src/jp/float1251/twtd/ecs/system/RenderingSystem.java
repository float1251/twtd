package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.twtd.ecs.component.PositionComponent;

/**
 * Created by takahiro iwatani on 2015/06/01.
 */
public class RenderingSystem extends EntitySystem {

    private final SpriteBatch batch;
    private final Texture img;
    private Engine engine;

    public RenderingSystem(SpriteBatch batch) {
        this.batch = batch;
        img = new Texture("enemy.png");
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.begin();
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class).get());
        for (Entity entity : entities) {
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            batch.draw(img, positionComponent.position.x - img.getWidth() / 2,
                    positionComponent.position.y - img.getHeight() / 2);
        }
        batch.end();
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }
}
