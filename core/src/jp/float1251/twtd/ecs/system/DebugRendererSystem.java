package jp.float1251.twtd.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.PositionComponent;

/**
 * Created by t-iwatani on 2015/09/10.
 */
public class DebugRendererSystem extends EntitySystem {

    private final ShapeRenderer renderer;
    private final Camera cam;
    private Engine engine;

    public DebugRendererSystem(Camera cam) {
        renderer = new ShapeRenderer();
        this.cam = cam;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> array = engine.getEntitiesFor(Family.all(PositionComponent.class, CircleColliderComponent.class).get());

        renderer.setColor(Color.YELLOW);
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (Entity e : array) {
            PositionComponent p = e.getComponent(PositionComponent.class);
            CircleColliderComponent c = e.getComponent(CircleColliderComponent.class);
            renderer.circle(p.position.x, p.position.y, c.radius);
        }
        renderer.end();
    }
}
