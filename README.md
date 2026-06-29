# 🌱 Java Farm Game (MVC)

一款使用 **Java Swing**、**MySQL** 與 **MVC 架構**開發的模擬農場經營遊戲。

玩家可以購買種子、種植作物、收成並販售農作物，透過金錢循環逐步擴建自己的農場。

本專案採用 **MVC (Model-View-Controller)** 架構設計，玩家可以購買種子、種植作物、收成、出售農作物獲得金錢，再利用金錢持續擴建自己的農場，形成完整的遊戲循環。

---

# 📖 專案介紹

本專案為 Java MVC 架構實作練習作品。

透過 **Java Swing** 建立圖形化介面，並使用 **MySQL** 儲存玩家資料、背包、農地與商店資訊。

遊戲採用 **DAO + Service** 分層設計，將畫面、商業邏輯與資料存取分離，使程式更容易維護與擴充。

玩家可以：

- 建立帳號並登入遊戲
- 前往商店購買種子
- 將種子種植於農地
- 等待作物成熟
- 收成農作物
- 前往玩家商店出售農作物
- 獲得金錢後持續經營自己的農場

---

# ✨ 功能特色

- 👤 玩家登入 / 註冊
- 🛒 商店購買種子
- 🎒 玩家背包管理
- 🌱 四塊農地種植系統
- 🔓 農地解鎖功能
- ⏱️ 作物成熟倒數計時
- 🌾 作物收成
- 🏪 玩家商店出售農作物
- 💰 金錢循環系統
- 💾 玩家資料儲存

---

# 🎮 遊戲流程

```text
登入
 │
 ▼
主畫面
 │
 ├──────────────┐
 │              │
 ▼              ▼
商店          背包
 │
 ▼
購買種子
 │
 ▼
農場
 │
 ├── 種植
 ├── 成熟倒數
 └── 收成
 │
 ▼
玩家商店
 │
 ▼
出售農作物
 │
 ▼
獲得金錢
 │
 └──────────► 回到商店購買種子
```

---

# 🖼️ 系統畫面

## 🔑 登入畫面

玩家在此作登入、註冊

![登入畫面](pic/login.png)

---

## 🏠 主畫面

![主畫面](pic/main.png)

---

## 🛒 商店

玩家可以查看商品資訊、購買種子，並查看目前持有金額。

![商店](pic/shop.png)

---

## 🎒 背包

顯示玩家目前持有的種子與農作物，以及對應數量與目前金額。

![背包](pic/bag.png)

---

## 🌱 農場

玩家可以選擇種植種子、等待成熟、收成作物，並逐步解鎖更多農地。

![農場](pic/farm.png)

---

## 🏪 玩家商店

玩家可以出售收成後的農作物，獲得金錢繼續經營農場。

![玩家商店](pic/player_shop.png)

# 🛠️ 使用技術

- Java
- Java Swing
- Maven
- JDBC
- MySQL
- MVC Architecture
- DAO Pattern
- Service Layer
- Serializable
- Swing Timer

---

# 📂 專案架構

```text
src
│
├── controller
│   ├── Login
│   ├── Register
│   └── playInterface
│       ├── MainUI
│       ├── ShopUI
│       ├── BagUI
│       ├── FarmUI
│       └── PlayerShopUI
│
├── dao
│
├── entity
│
├── service
│
└── util
```

---

# 🗄️ 資料庫設計

本專案使用 **MySQL** 儲存遊戲資料。

| 資料表 | 用途 |
|---------|------|
| Player | 玩家資料 |
| Shop | 商店商品資訊 |
| Bag | 玩家背包 |
| Farm | 玩家農地資訊 |

---

# 🏗️ MVC 架構

```text
            UI (Swing)
                 │
                 ▼
           Controller
                 │
                 ▼
             Service
                 │
                 ▼
                DAO
                 │
                 ▼
              MySQL
```

---

# 🚀 執行方式

## 1. 匯入資料庫

匯入專案提供的 SQL：

```
farm_game.sql
```

---

## 2. 修改資料庫連線

修改：

```
DbConnection.java
```

設定自己的

- Database
- Username
- Password

---

## 3. Maven 打包

```bash
mvn clean package
```

---

## 4. 執行

```bash
java -jar farm-0.0.1-SNAPSHOT.jar
```

---

# 📚 開發心得

這是我第一次用Eclipse做Maven專案，途中遇到很多困難，不過對我來說是一次製作Java MVC 專案的經驗。

---

# 👨‍💻 Author

GitHub：

**https://github.com/a1313855-bit**
