-- テーブルが存在したら削除する
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS characters CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_items;
-- 会員テーブル
CREATE TABLE users
(
   id SERIAL PRIMARY KEY,
   name VARCHAR (255) NOT NULL,
   email VARCHAR (255) NOT NULL UNIQUE,
   password VARCHAR (255) NOT NULL,
   postcode VARCHAR (255) NOT NULL,
   address TEXT NOT NULL,
   tel VARCHAR (255) NOT NULL,
   is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
   role VARCHAR (50) DEFAULT 'USER' NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL
);
-- 管理者テーブル
CREATE TABLE admin
(
   id SERIAL PRIMARY KEY,
   email VARCHAR (255) NOT NULL UNIQUE,
   password VARCHAR (255) NOT NULL,
   role VARCHAR (50) DEFAULT 'ADMIN' NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL
);
-- キャラクタテーブル
CREATE TABLE characters
(
   id SERIAL PRIMARY KEY,
   name VARCHAR (255) UNIQUE NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL
);
-- 商品テーブル
CREATE TABLE items
(
   id SERIAL PRIMARY KEY,
   character_id INTEGER,
   name VARCHAR (255) NOT NULL UNIQUE,
   detail TEXT NOT NULL,
   price INTEGER NOT NULL,
   stock INTEGER NOT NULL,
   sale_status INTEGER NOT NULL,
   is_limited BOOLEAN DEFAULT FALSE NOT NULL,
   is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
   image_path TEXT NOT NULL UNIQUE,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL,
   -- キャラクタテーブルへの外部キー制約
   FOREIGN KEY (character_id) REFERENCES characters (id)
);
-- カート・アイテムテーブル
CREATE TABLE cart_items
(
   id SERIAL PRIMARY KEY,
   user_id INTEGER NOT NULL,
   item_id INTEGER NOT NULL,
   amount INTEGER NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL,
   -- 会員・商品テーブルへの外部キー制約
   FOREIGN KEY (user_id) REFERENCES users (id),
   FOREIGN KEY (item_id) REFERENCES items (id)
);
-- 注文テーブル
CREATE TABLE orders
(
   id SERIAL PRIMARY KEY,
   user_id INTEGER NOT NULL,
   postage INTEGER NOT NULL,
   total_price INTEGER NOT NULL,
   payment INTEGER NOT NULL,
   delivery_name TEXT NOT NULL,
   delivery_postcode VARCHAR (255) NOT NULL,
   delivery_address TEXT NOT NULL,
   delivery_tel VARCHAR (255) NOT NULL,
   order_status INTEGER DEFAULT 1 NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL,
   -- 会員テーブルへの外部キー制約
   FOREIGN KEY (user_id) REFERENCES users (id)
);
-- 注文商品テーブル
CREATE TABLE order_items
(
   id SERIAL PRIMARY KEY,
   item_id INTEGER NOT NULL,
   order_id INTEGER NOT NULL,
   amount INTEGER NOT NULL,
   purchase_price INTEGER NOT NULL,
   created_at TIMESTAMP without time zone NOT NULL,
   updated_at TIMESTAMP without time zone NOT NULL,
   -- 商品・注文テーブルへの外部キー制約
   FOREIGN KEY (item_id) REFERENCES items (id),
   FOREIGN KEY (order_id) REFERENCES orders (id)
);