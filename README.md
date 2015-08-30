# TWTD

* Cellのサイズは64x64

# TODO
- [ ] ゲーム内UIの仕様を作成する
- [ ] 各種UIの画像
- [ ] 弾の射程表示
- [ ] SEの作成
- [ ] BGMの作成
- [ ] BGM・SEの組み込み
- [ ] Unitのアップデート
- [ ] ObjectPoolの実装
- [ ] positionから配置されているunitのデータを取得する
- [ ] はじめに簡易的なloading画面を表示する
- [ ] dispose処理
- [ ] メニューUIの作成
- [ ] GameOverUIの作成
- [ ] GameClearUIの作成

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
