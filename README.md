# VJava ECサイト

![スクリーンショット (75)](https://github.com/user-attachments/assets/2017644c-a3f2-4cd0-aed9-8befe9d2485b)

## アプリ概要
チーム開発で制作したVチューバーグッズのECサイト

**期間**：10/7～11/8

**実働日数**：19日

**開発者数**：4名

## 設計書
<details><summary>要件定義</summary>
    
![要件定義](https://github.com/user-attachments/assets/5b778434-d941-4217-a5fd-1bc0e4fe7b84)
</details>

<details><summary>ER図</summary>
    
![ER](https://github.com/user-attachments/assets/3abf5661-ba24-4128-8a6b-a8b723315577)
</details>

<details><summary>機能一覧</summary>
  
![機能一覧(会員)](https://github.com/user-attachments/assets/e23bd3f9-f647-4097-aa75-f4c3b6588ccc)

---
![機能一覧(管理者)](https://github.com/user-attachments/assets/a0f16cd4-b823-4b8d-b196-97cfc93cf650)
</details>

<details><summary>ルーティング設計</summary>
  
![ルーティング設計(会員)](https://github.com/user-attachments/assets/6e014357-05d0-4e3b-a7ce-80229791ffad)

---
![ルーティング設計(管理者)](https://github.com/user-attachments/assets/23c13b9f-075b-48d9-87d5-b971c15dbdcc)
</details>

<details><summary>UIフロー図</summary>
    
![UIフロー図会員サイド](https://github.com/user-attachments/assets/df133d30-955d-449e-9c2c-1ac9f94e684e)

---
![UIフロー図管理者サイド](https://github.com/user-attachments/assets/c111ee56-a41d-437c-89d8-91dcd51c96df)
</details>
  
<details><summary>ワイヤーフレーム</summary>
    
- 会員側
![ワイヤーフレーム会員サイド](https://github.com/user-attachments/assets/5529b1e8-7b7f-4237-a987-8ae825962a40)

---
- 管理者側
![ワイヤーフレーム管理者サイド](https://github.com/user-attachments/assets/3d2a6ce5-290d-4fdb-9ea8-7155f5d90228)
</details>


## 機能：会員側
  ### ログイン、ログアウト
  <details><summary>ログイン、ログアウト画面</summary>
    
  #### 会員のログイン、ログアウトが可能
![スクリーンショット (72)](https://github.com/user-attachments/assets/d6889f83-f224-488f-92b0-2a6cc72033bd)

  ---
![スクリーンショット (76)](https://github.com/user-attachments/assets/20824328-84c8-4097-94ab-d83b61293ba9)
 </details>
  
  ### 商品一覧
  <details><summary>商品一覧表示画面</summary>

  #### 一覧にて登録されている商品一覧を確認可能
 ![スクリーンショット (100)](https://github.com/user-attachments/assets/1674333e-ca90-44d9-8fb0-52bdb56622e3)
  </details>

  ### 商品詳細 
  <details><summary>商品詳細表示画面</summary>

  #### 一覧にて登録されている商品詳細を確認可能
  ![スクリーンショット (101)](https://github.com/user-attachments/assets/70051d83-6a1c-498c-b665-da08b9824a24)
  </details>
  
  ### カート機能
  <details><summary>カート一覧</summary>

  #### カートの中の商品を確認することが可能
  ![スクリーンショット (106)](https://github.com/user-attachments/assets/f38de121-73de-4056-9552-6385fe3725eb)
  </details>
  
  <details><summary>購入情報入力</summary>

  #### 購入情報を入力することが可能
  - 購入情報入力ページ
![スクリーンショット (103)](https://github.com/user-attachments/assets/03c874a9-e154-44b5-afd0-0f2632c9dcb8)
  </details>

<details><summary>購入情報確認</summary>

  #### 購入情報を確認、購入することが可能
  - 購入情報確認ページ
![スクリーンショット (107)](https://github.com/user-attachments/assets/16e77bbc-8e4e-454d-b2bf-7aaa4696e0e8)
  </details>

  ### 会員情報詳細 
  <details><summary>注文履歴確認、会員情報編集、退会が可能</summary>  
    
  ![スクリーンショット (83)](https://github.com/user-attachments/assets/c0f1e10a-4e1f-47df-884e-338425a06307)

  ---  
  ![スクリーンショット (111)](https://github.com/user-attachments/assets/d45d1781-2c19-47f0-b4dd-2e5106d2e245)

  ---
  ![スクリーンショット (112)](https://github.com/user-attachments/assets/5f8fb720-8e2d-4a4c-8fd1-8a8a2135e9fa)
  </details>


## 機能：管理者側
  ### ログイン、ログアウト
  <details><summary>ログイン、ログアウト画面</summary>

  #### 管理者のログイン、ログアウトが可能
  ![image](https://github.com/user-attachments/assets/aeb3d5cd-3960-43af-a7ae-058a520adeaf)

  ---
  ![image](https://github.com/user-attachments/assets/b21babf5-eca3-4aaf-8382-454c7c431005)
 </details>
  
  ### 商品一覧表示
  <details><summary>商品一覧表示画面</summary>

  #### 一覧にて登録されている商品一覧を確認可能
 ![スクリーンショット (115)](https://github.com/user-attachments/assets/71f83cbd-d43a-43ef-b2ad-e926adefc34a)
  </details>

  ### CRUD機能
  <details><summary>商品新規登録</summary>

  #### 商品を新規登録することが可能
  ![postCreate](https://github.com/user-attachments/assets/3496b3a0-2dec-4bf1-a66c-4cc46d037768)
  </details>
  
  <details><summary>商品詳細表示</summary>

  #### 商品の詳細を確認することが可能
  - 商品の詳細ページ
  ![スクリーンショット (42)](https://github.com/user-attachments/assets/27abf072-ec75-4ba5-9e04-bd7758e7cde9)
  </details>
  
  <details><summary>商品編集</summary>
    
  #### 商品を編集することが可能
  - 商品の編集ページ
  ![スクリーンショット (43)](https://github.com/user-attachments/assets/140754a8-0bde-46d2-9c40-d3b3ce72e5bc)
  </details>
  
  <details><summary>商品削除</summary>
    
  #### 商品を削除することが可能  　
 ![スクリーンショット (46)](https://github.com/user-attachments/assets/94c54965-c443-45b3-8713-72fd91192997)
  </details>


  ### 会員一覧  
  <details><summary>会員ステータスの変更が可能</summary>  
    
  ![スクリーンショット (49)](https://github.com/user-attachments/assets/13cf3b6e-0597-41a1-9d6a-216b2cab51ce)

  ---  
  ![スクリーンショット (50)](https://github.com/user-attachments/assets/9b1dcc69-8ae9-4b4a-928f-0fcee9f9bec8)

  ---
  ![スクリーンショット (51)](https://github.com/user-attachments/assets/dcc93071-5ffc-485f-b53f-b11cb75c2ce9)
  </details>
  
  ### 注文履歴一覧
   <details><summary>注文履歴の検索、編集が可能</summary>
     
  ![スクリーンショット (53)](https://github.com/user-attachments/assets/72a7bbb1-e367-4b9b-b83e-7bb5d3ce1da9)

  ---
  ![スクリーンショット (116)](https://github.com/user-attachments/assets/e6499f0e-bfb4-45ee-ba2d-ff556e4dfecf)
  </details>

  ### キャラクター一覧
  <details><summary>キャラクターの新規登録、編集、削除が可能</summary>
    
![スクリーンショット (120)](https://github.com/user-attachments/assets/ee4f9a6b-3863-4481-990b-e1733f560ae4)

  ---
![スクリーンショット (121)](https://github.com/user-attachments/assets/05a70a52-ae74-4ad3-8994-1cd44ee7245a)
  </details>

## 引用
 ### ぶいすぽっ！ 公式サイト：https://vspo.jp/
 ※グループワークの学習として作成したため、商用目的等や予定などは一切ありません。
