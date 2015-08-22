package jp.float1251.twtd;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jp.float1251.twtd.data.PlayerData;
import jp.float1251.twtd.util.GameUtils;

/**
 * Created by takahiro iwatani on 2015/05/31.
 */
public class StageData {

    private final MapLayer wallData;
    private final Vector2 respawnPosition;
    public PolylineMapObject path;
    private OrthogonalTiledMapRenderer renderer;

    public StageData(String fileName) {
        TiledMap loader = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(loader);
        wallData = loader.getLayers().get("wall");
        MapProperties val = loader.getLayers().get("start_goal").getObjects().get("enemy").getProperties();
        respawnPosition = new Vector2(val.get("x", Float.class), val.get("y", Float.class));
        path = loader.getLayers().get("path").getObjects().getByType(PolylineMapObject.class).get(0);
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

    public Vector2 getRespawnPosition() {
        return respawnPosition;
    }


}
