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
```json
{
  "time": number, // 出現時間
  "type": string,
  "life": int, // HP
  "speed": float, // 速度
  "delta_time": float, // 何秒ごとに
  "total": int,// 何体出すか
  "path_index": int,// stagedataに設定しているpathのindex
}
```

[![Gyazo](https://i.gyazo.com/de7cde6f69617f3d09edf3ca545e8d75.gif)](https://gyazo.com/de7cde6f69617f3d09edf3ca545e8d75)
