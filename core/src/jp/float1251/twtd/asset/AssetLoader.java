package jp.float1251.twtd.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
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
        manager.load("cell/cell_unit_2.png", Texture.class);
        manager.load("cell/cell_unit_3.png", Texture.class);
        manager.load("cell/cell_unit_4.png", Texture.class);
        manager.load("bullet.png", Texture.class);

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));


        FreetypeFontLoader.FreeTypeFontLoaderParameter param = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        param.fontFileName = "font/PixelMplus12-Regular.ttf";
        param.fontParameters.size = 30;
        param.fontParameters.shadowOffsetX = 3;
        param.fontParameters.shadowOffsetY = 3;
        manager.load("size30.ttf", BitmapFont.class, param);

        FreetypeFontLoader.FreeTypeFontLoaderParameter param2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        param2.fontFileName = "font/PixelMplus12-Regular.ttf";
        param2.fontParameters.size = 100;
        param2.fontParameters.shadowOffsetX = 3;
        param2.fontParameters.shadowOffsetY = 3;
        manager.load("size50.ttf", BitmapFont.class, param2);
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
