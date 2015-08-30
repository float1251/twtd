package jp.float1251.twtd.data;

/**
 * Created by t-iwatani on 2015/08/22.
 */
public class EnemySpawnData {
    /**
     * 出現時間
     */
    public float time;
    //public float type;
    /**
     * ライフ
     */
    public int life;
    /**
     * 移動速度
     */
    public float speed;
    /**
     * 何秒ごとに出現させるか
     */
    public float deltaTime;
    /**
     * 何体出現させるか
     */
    public int total;

    /**
     * path
     */
    public int pathIndex;
}
