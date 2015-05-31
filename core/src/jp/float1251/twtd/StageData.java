package jp.float1251.twtd;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import jp.float1251.twtd.util.GameUtils;

/**
 * Created by takahiro iwatani on 2015/05/31.
 */
public class StageData {

    private final MapLayer wallData;
    private OrthogonalTiledMapRenderer renderer;

    public StageData(String fileName) {
        TiledMap loader = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(loader);
        wallData = loader.getLayers().get("wall");
    }

    /**
     * set camera by renderer.
     *
     * @param camera
     */
    public void setView(OrthographicCamera camera) {
        renderer.setView(camera);
    }

    /**
     * render tiledmap
     */
    public void render() {
        renderer.render();
    }

    public boolean enablePutUnit(Vector3 worldPos) {
        for (MapObject obj : wallData.getObjects()) {
            Rectangle rect = GameUtils.getRectangle(obj);
            if (rect.contains(worldPos.x, worldPos.y)) {
                return true;
            }
        }
        return false;
    }
}
