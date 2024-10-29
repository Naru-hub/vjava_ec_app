-- 会員データ1
INSERT INTO users
(
   name,
   email,
   password,
   postcode,
   address,
   tel,
   created_at,
   updated_at
)
VALUES
(
   'テスト',
   'test@test.com',
   '$2a$10$I53eTbgLsU7F3TJJIelTkuXcGHzOCMYTbKIRX1W1tsHWZbN3RzLa.',
   '1234567',
   '〇〇県〇〇市〇〇町',
   '12345678910',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 会員データ2
INSERT INTO users
(
   name,
   email,
   password,
   postcode,
   address,
   tel,
   created_at,
   updated_at
)
VALUES
(
   'テスト2',
   'test2@test.com',
   '$2a$10$I53eTbgLsU7F3TJJIelTkuXcGHzOCMYTbKIRX1W1tsHWZbN3RzLa.',
   '2345678',
   '〇〇県〇〇市〇〇町2丁目',
   '12345678912',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 会員データ3
INSERT INTO users
(
   name,
   email,
   password,
   postcode,
   address,
   tel,
   created_at,
   updated_at
)
VALUES
(
   'テスト3',
   'test3@test.com',
   '$2a$10$I53eTbgLsU7F3TJJIelTkuXcGHzOCMYTbKIRX1W1tsHWZbN3RzLa.',
   '3456789',
   '〇〇県〇〇市〇〇町3丁目',
   '12345678913',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 管理者データ
INSERT INTO admin
(
   email,
   password,
   created_at,
   updated_at
)
VALUES
(
   'admin@admin.com',
   '$2a$10$I53eTbgLsU7F3TJJIelTkuXcGHzOCMYTbKIRX1W1tsHWZbN3RzLa.',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- キャラクタデータ1
INSERT INTO characters
(
   name,
   description,
   height,
   debut_date,
   is_deleted,
   image_path,
   created_at,
   updated_at
)
VALUES
(
   '胡桃のあ',
   '胡桃のあの紹介文です。',
   155,
   '2020-03-14',
   false,
   '/images/default/005-kurumi_noah.png',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- キャラクタデータ2
INSERT INTO characters
(
   name,
   description,
   height,
   debut_date,
   is_deleted,
   image_path,
   created_at,
   updated_at
)
VALUES
(
   '橘ひなの',
   '橘ひなのの紹介文です。',
   154,
   '2020-08-14',
   false,
   '/images/default/008-tachibana_hinano.png',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 商品データ1
INSERT INTO items
(
   character_id,
   name,
   detail,
   price,
   stock,
   sale_status,
   is_limited,
   is_deleted,
   image_path,
   created_at,
   updated_at
)
VALUES
(
   1,
   '胡桃のあキーホルダー',
   'キーホルダー',
   1000,
   20,
   1,
   false,
   false,
   '/images/default/default_goods.jpg',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 商品データ2
INSERT INTO items
(
   character_id,
   name,
   detail,
   price,
   stock,
   sale_status,
   is_limited,
   is_deleted,
   image_path,
   created_at,
   updated_at
)
VALUES
(
   1,
   '胡桃のあクリアファイル',
   'クリアファイル',
   1000,
   10,
   2,
   false,
   false,
   '/images/default/default_goods2.jpg',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 商品データ3
INSERT INTO items
(
   character_id,
   name,
   detail,
   price,
   stock,
   sale_status,
   is_limited,
   is_deleted,
   image_path,
   created_at,
   updated_at
)
VALUES
(
   1,
   '胡桃のあ人形',
   '人形',
   4000,
   5,
   3,
   false,
   true,
   '/images/default/default_goods3.jpg',
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文データ1
INSERT INTO orders
(
   user_id,
   postage,
   total_price,
   payment,
   delivery_name,
   delivery_postcode,
   delivery_address,
   delivery_tel,
   order_status,
   created_at,
   updated_at
)
VALUES
(
   1,
   800,
   4100,
   1,
   '配達太郎',
   '1234567',
   '〇〇県〇〇市〇〇町3丁目',
   '12345678901',
   3,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ1
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   1,
   1,
   2,
   1100,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ2
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   2,
   1,
   1,
   1100,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文データ2
INSERT INTO orders
(
   user_id,
   postage,
   total_price,
   payment,
   delivery_name,
   delivery_postcode,
   delivery_address,
   delivery_tel,
   order_status,
   created_at,
   updated_at
)
VALUES
(
   1,
   800,
   5500,
   3,
   '配達花子',
   '1234567',
   '〇〇県〇〇市〇〇町5丁目',
   '12345678902',
   1,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ3
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   1,
   2,
   1,
   1100,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ4
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   3,
   2,
   1,
   4400,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文データ3
INSERT INTO orders
(
   user_id,
   postage,
   total_price,
   payment,
   delivery_name,
   delivery_postcode,
   delivery_address,
   delivery_tel,
   order_status,
   created_at,
   updated_at
)
VALUES
(
   2,
   800,
   5500,
   1,
   '配達次郎',
   '1234568',
   '〇〇県〇〇市〇〇町6丁目',
   '12345678903',
   2,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ5
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   1,
   3,
   1,
   1100,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);
-- 注文商品データ6
INSERT INTO order_items
(
   item_id,
   order_id,
   amount,
   purchase_price,
   created_at,
   updated_at
)
VALUES
(
   3,
   3,
   1,
   4400,
   CURRENT_TIMESTAMP,
   CURRENT_TIMESTAMP
);