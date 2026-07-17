# ShinGroupB_Project（Honey Bloom）

Bグループのショッピングサイト仕様に合わせて、既存のHoney Bloom画面を生かして再設計したSpring Bootプロジェクトです。

## 主な機能

- 会員登録、ログイン、ログアウト
- 商品一覧、在庫表示
- カート追加、数量変更、削除
- 注文確認、注文確定、注文履歴
- 会員情報の確認・変更
- 管理者による商品登録・編集・削除
- PostgreSQLへの永続化

## DB接続

既定値は次のとおりです。

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/groupb_project
spring.datasource.username=postgres
spring.datasource.password=psql
```

異なる環境では `SHOP_DB_URL`、`SHOP_DB_USER`、`SHOP_DB_PASSWORD` の環境変数で上書きできます。

`spring.jpa.hibernate.ddl-auto=update`により、既存の`members`を残しながら、`products`、`cart_items`、`shop_orders`、`order_items`を起動時に調整します。

## Eclipseでの反映

1. プロジェクトを選択して`F5`
2. 右クリック→Maven→プロジェクトの更新
3. プロジェクト→クリーン
4. `GroupBProjectApplication.java`をSpring Boot Appとして実行
5. `http://localhost:8080/`を開く

初期管理者は、DBに存在しない場合だけ次の内容で作成されます。

```text
会員ID: admin
パスワード: Admin123!
```

既存の旧形式パスワードでもログインできます。新規登録したパスワードはBCrypt形式で保存されます。
