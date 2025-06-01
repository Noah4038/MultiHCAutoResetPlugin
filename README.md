# MultiHCAutoReset

## このプラグインについて
MultiHCAutoResetは、Minecraftのハードコアサーバーを自動でリセットするためのプラグインです。プレイヤーが死んだときに、サーバーを自動的にリセットしてくれます。

## 仕様
- プレイヤーが死んだら自動でサーバーをリセット
- エンドでの特別な処理（エンドにいる全員が死んだときだけリセット）
- リセットまでのカウントダウン表示
- リセット時に全員をスペクテイターに自動で切り替え

## インストール、使い方

- [インストール、使い方ガイド](docs/GUIDE.md) - プラグインのインストールから詳細な使用方法についてはこちら
1. `.bat`の設定：

- `.bat`ファイルの内容を以下のものにする

```bat
@echo off
:RESTART
java -Xmx2G -jar purpur-1.21.5.jar nogui

echo Server has stopped.

if exist reset.flag (
    echo Resetting worlds...
    rmdir /s /q world
    rmdir /s /q world_nether
    rmdir /s /q world_the_end
    del reset.flag
)

goto RESTART
```

> ⚠️ `purpur-1.21.5.jar`は、使用しているサーバーの`.jar`名に変更してください。

2. プラグインの導入

- [Releases](https://github.com/Noah4038/MultiHCAutoResetPlugin/releases)でプラグインの`.jar`ファイルをダウンロードする
- `.jar`ファイルを`plugins`フォルダに配置

3. サーバーの起動
- `.bat`ファイルをダブルクリックで起動

## 設定について
今のバージョンでは、以下の設定が固定されています：
- リセットまでの待ち時間：10秒

## 注意点
- サーバーを閉じたい場合は`stop`コマンドではなく、直接サーバーのウィンドウを閉じてください。

## 動作環境
- Minecraft 1.20.5以上
- Spigot/Paper/Purpur サーバー


## サポート・お問い合わせ
何か問題や質問がある場合は、[Issue](https://github.com/Noah4038/MultiHCAutoResetPlugin/issues)からお知らせください。 