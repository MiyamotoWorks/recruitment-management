# 採用管理システム

Java / Spring Boot / MariaDBを使用して作成した、候補者情報を管理するWebアプリケーションです。

## 概要

採用担当者が候補者情報を登録・検索・編集・削除できる採用管理システムを作成しました。

ログイン機能とユーザー権限（ADMIN / USER）を実装し、権限に応じて候補者情報を操作できる範囲を制御しています。

## 主な機能

### ログイン機能

* ユーザー名・パスワードによるログイン
* MariaDBに保存したユーザー情報を使用
* HttpSessionによるログイン状態の管理
* ログアウト機能

### ユーザー権限

| 権限    | 一覧・詳細 | 新規登録 | 編集 | 削除 |
| ----- | ----- | ---- | -- | -- |
| ADMIN | ○     | ○    | ○  | ○  |
| USER  | ○     | ×    | ×  | ×  |

* ログイン時にDBからユーザー情報を取得
* ADMIN / USERの権限をSessionに保存
* Controller側で権限を確認し、操作を制御
* Thymeleafの条件分岐により、権限に応じて操作ボタンを表示

### 候補者管理

* 候補者一覧表示
* 選考状況による検索
* 候補者詳細表示
* 候補者登録
* 候補者情報編集
* 候補者削除
* 入力値バリデーション

## 使用技術

* Java 17
* Spring Boot 4.0.8
* Spring MVC
* Spring Data JPA
* Thymeleaf
* MariaDB
* Maven
* HTML
* Git / GitHub
* VS Code

## 主なJava・Springの学習内容

* クラス・オブジェクト
* コンストラクタ
* インスタンス化（`new`）
* カプセル化（getter / setter）
* 継承・インターフェース
* ジェネリクス
* `List`
* `Optional`
* Stream API
* アノテーション
* Spring MVCのController
* Spring Data JPAのRepository
* EntityによるDBデータのオブジェクト化
* コンストラクタインジェクション
* HttpSessionによるログイン状態・権限管理

## アプリケーション構成

```text
src
└─ main
   ├─ java
   │  └─ com.example.recruitment_management
   │     ├─ Candidate.java
   │     ├─ CandidateRepository.java
   │     ├─ HomeController.java
   │     ├─ User.java
   │     ├─ UserRepository.java
   │     └─ LoginController.java
   │
   └─ resources
      └─ templates
         ├─ index.html
         ├─ login.html
         ├─ candidates.html
         ├─ candidate-form.html
         └─ candidate-detail.html
```

## 権限制御の仕組み

ログイン時にDBからユーザー情報を取得し、権限をSessionに保存します。

```text
ログイン
   ↓
UserRepository
   ↓
MariaDBからUserを取得
   ↓
username / passwordを確認
   ↓
role（ADMIN / USER）をSessionへ保存
   ↓
候補者一覧
   ↓
Controllerでroleを確認
   ↓
ADMIN → 登録・編集・削除可能
USER  → 閲覧のみ
```

また、画面上で操作ボタンを非表示にするだけではなく、Controller側でも権限を確認することで、USERがURLを直接入力して操作するケースにも対応しています。

## 学習目的

Javaの基本文法だけでなく、Spring Bootを使用したWebアプリケーション開発、DBとの連携、ログイン・セッション管理、ユーザー権限制御までを実際に実装することを目的として作成しました。
