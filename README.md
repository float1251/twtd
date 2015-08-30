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
- [ ] SEの作成
- [ ] BGMの作成
- [ ] BGM・SEの組み込み
- [x] Unitの設定周りのUI変更
- [x] Unitの配置のコスト
- [ ] Unitのアップデート
- [x] AssetManagerの実装
- [ ] ObjectPoolの実装
- [x] Unitのタイプ分け
- [x] bulletのタイプ分け
- [x] Playerのlife表示
- [x] 敵の出現をスクリプトで行う
- [x] 敵の出現のwave化
- [ ] positionから配置されているunitのデータを取得する
- [ ] はじめに簡易的なloading画面を表示する
- [ ] dispose処理

# json仕様
{
  time: , // 出現時間
  type: ,
  life: , // HP
  speed: , // 速度
  delta_time: , // 何秒ごとに
  total: // 何体出すか
  path_index: // stagedataに設定しているpathのindex
}

[![Gyazo](https://i.gyazo.com/a232afd41b9b6ea056f77ec15edc9fc0.gif)](https://gyazo.com/a232afd41b9b6ea056f77ec15edc9fc0)
