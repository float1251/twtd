package jp.float1251.twtd.data;

import java.util.ArrayList;

/**
 * Created by t-iwatani on 2015/08/22.
 */
public class WaveData{
    ArrayList<EnemySpawnData> dataList = new ArrayList<>();

    public void addEnemySpawnData(EnemySpawnData data){
        dataList.add(data);
    }

    public ArrayList<EnemySpawnData> getDataList(){
        return dataList;
    }
}



