package jp.float1251.twtd.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by t-iwatani on 2015/08/20.
 */
public class AssetLoader implements Disposable {

    private final AssetManager manager;

    private AssetLoader() {
        manager = new AssetManager();
    }

    /**
     * https://github.com/libgdx/libgdx/wiki/Managing-your-assets#creating-an-assetmanager
     * staticにするとandroidで問題が発生するってある。
     * AssetManagerではなく、自分のinstanceだから大丈夫か？？
     */
    private static AssetLoader instance = new AssetLoader();

    public static AssetLoader getInstance() {
        return instance;
    }

    public void load() {
        manager.load("cell/cell_unit.png", Texture.class);
        manager.load("bullet.png", Texture.class);

        // TODO blockされるからasyncにする
        manager.finishLoading();
    }

    public <T> T get(String file, Class<T> type) {
        return manager.get(file, type);
    }

    public <T> T get(String file) {
        return manager.get(file);
    }

    @Override
    public void dispose() {
        if (manager != null) {
            manager.dispose();
        }
    }
}
