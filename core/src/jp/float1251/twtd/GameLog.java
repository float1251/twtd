package jp.float1251.twtd;

import com.badlogic.gdx.Gdx;

/**
 * Created by takahiro iwatani on 2015/05/24.
 */
public class GameLog {

    private static final String TAG = "TWTD";

    public static void d(String message){
        Gdx.app.log(TAG, message);
    }


}
