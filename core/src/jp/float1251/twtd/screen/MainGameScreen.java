package jp.float1251.twtd.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.StageData;
import jp.float1251.twtd.TWTD;
import jp.float1251.twtd.ecs.component.BulletComponent;
import jp.float1251.twtd.ecs.component.CircleColliderComponent;
import jp.float1251.twtd.ecs.component.EnemyComponent;
import jp.float1251.twtd.ecs.system.BulletSystem;
import jp.float1251.twtd.ecs.system.CollisionSystem;
import jp.float1251.twtd.ecs.system.EnemyCreateSystem;
import jp.float1251.twtd.ecs.system.EnemyLifeRenderingSystem;
import jp.float1251.twtd.ecs.system.EnemySystem;
import jp.float1251.twtd.ecs.system.MoveSystem;
import jp.float1251.twtd.ecs.system.RenderingSystem;
import jp.float1251.twtd.ecs.system.UnitSystem;
import jp.float1251.twtd.listener.GameNotify;
import jp.float1251.twtd.listener.IColliderListener;
import jp.float1251.twtd.listener.IEnemyEventListener;
import jp.float1251.twtd.ui.MainGameUi;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class MainGameScreen implements Screen {
    private final TWTD game;
    private final FitViewport viewport;
    private final StageData stageData;
    private final MainGameUi ui;
    private final Engine engine;
    private final SpriteBatch batch;

    public MainGameScreen(final TWTD game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(960, 640, camera);
        camera.setToOrtho(false, 960, 640);
        stageData = new StageData("stage/stage1.tmx");
        stageData.setView((OrthographicCamera) viewport.getCamera());

        GameNotify notify = new GameNotify();
        this.engine = new Engine();
        ui = new MainGameUi(viewport, stageData, engine);
        batch = new SpriteBatch();
        engine.addSystem(new RenderingSystem(batch));
        engine.addSystem(new EnemyCreateSystem(stageData.getRespawnPosition()));
        engine.addSystem(new EnemySystem(stageData.path.getPolyline(), notify));
        notify.addEnemyEventListner(new IEnemyEventListener() {
            @Override
            public void onDestroyEnemy(Entity e) {
                GameLog.d("onDestroyEnemy");
            }

            @Override
            public void onReachEnd(Entity e) {
                GameLog.d("onReachEnd");
            }
        });
        engine.addSystem(new UnitSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new BulletSystem());
        engine.addSystem(new EnemyLifeRenderingSystem(batch));
        engine.addSystem(new CollisionSystem(notify));
        notify.addColliderListener(new IColliderListener() {
            @Override
            public void onCollision(Entity e1, Entity e2) {
                EnemyComponent ec1 = e1.getComponent(EnemyComponent.class);
                EnemyComponent ec2 = e2.getComponent(EnemyComponent.class);
                BulletComponent bc1 = e1.getComponent(BulletComponent.class);
                BulletComponent bc2 = e2.getComponent(BulletComponent.class);

                // ともに敵 or 弾の際は何もしない
                if((ec1 != null && ec2 != null) || (bc1 != null && bc2 !=null)){
                    return;
                }

                if(ec1 != null){
                    ec1.life -= bc2.power;
                    e2.getComponent(CircleColliderComponent.class).isRemove = true;
                }else{
                    ec2.life -= bc1.power;
                    e1.getComponent(CircleColliderComponent.class).isRemove = true;
                }
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.getCamera().update();
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stageData.render();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        engine.update(delta);
        ui.act(delta);
        ui.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
