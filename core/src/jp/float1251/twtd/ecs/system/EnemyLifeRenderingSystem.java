package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jp.float1251.twtd.asset.AssetLoader;
import jp.float1251.twtd.ecs.component.EnemyComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;

/**
 * Created by t-iwatani on 2015/08/21.
 */
public class EnemyLifeRenderingSystem extends EntitySystem {

    private final SpriteBatch batch;
    private final Family family;
    private Engine engine;

    public EnemyLifeRenderingSystem(SpriteBatch batch) {
        super();
        this.batch = batch;
        this.family = Family.all(EnemyComponent.class).get();
    }


    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);
        BitmapFont f = AssetLoader.getInstance().get("size30.ttf");
        batch.begin();
        for (Entity e : entities) {
            PositionComponent pc = e.getComponent(PositionComponent.class);
            EnemyComponent ec = e.getComponent(EnemyComponent.class);
            f.draw(batch, String.format("%d", (int) ec.life), pc.position.x - 15, pc.position.y + 50);
        }
        batch.end();
    }
}
