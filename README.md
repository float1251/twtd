# TWTD

* Cellのサイズは64x64

# TODO
- [x] Unitの画像
- [x] Enemyの画像
- [x] Enemyの体力
- [x] bulletの画像
- [x] Enemyの体力表示
- [ ] ゲーム内UIの仕様を作成する
- [ ] 各種UIの画像
- [x] Unitから弾が出る
- [x] 弾が一番近い敵に発射される
- [ ] 弾の射程表示
- [x] 弾が一定距離に達したらremove
- [x] 弾とEnemyの衝突判定
- [x] 敵を倒したらコインが貰える
- [x] コイン表示
- [ ] Unitの設定周りのUI変更
- [ ] Unitの配置のコスト
- [ ] Unitのアップデート
- [x] AssetManagerの実装
- [ ] ObjectPoolの実装
- [ ] Unitのタイプ分け
- [ ] bulletのタイプ分け
- [ ] Playerのlife表示
- [x] 敵の出現をスクリプトで行う
- [ ] 敵の出現のwave化
- [ ] positionから配置されているunitのデータを取得する

# json仕様
{
  time: , // 出現時間
  type: ,
  life: , // HP
  speed: , // 速度
  deltatime: , // 何秒ごとに
  total: // 何体出すか
}

[![Gyazo](https://i.gyazo.com/a232afd41b9b6ea056f77ec15edc9fc0.gif)](https://gyazo.com/a232afd41b9b6ea056f77ec15edc9fc0)
