# 🏦 Bank Management System (Core Java + JDBC + PostgreSQL)

A console-based Bank Management System built using Core Java, JDBC, and PostgreSQL. This project demonstrates CRUD operations, transaction handling, and clean layered architecture (DAO + Service).

---

## 🚀 Features

* 👤 User Registration & Login
* 💰 Deposit Money
* 💸 Withdraw Money
* 📊 Check Balance (Real-time from DB)
* 📜 Transaction History
* 🔐 Input Validation & Error Handling

---

## 🛠️ Tech Stack

* Java (Core Java)
* JDBC
* PostgreSQL
* Maven

---

## 📁 Project Structure

```
com.bank
│
├── model        # Entity classes
├── dao          # Database operations
├── service      # Business logic
├── util         # DB connection
└── main         # Entry point
```

---

## ⚙️ Setup Instructions

1. Clone the repository:

```
git clone https://github.com/your-username/bank-management-system.git
```

2. Create PostgreSQL Database:

```sql
CREATE DATABASE bank_db;
```

3. Create Tables:

```sql
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    balance DOUBLE PRECISION DEFAULT 0
);

CREATE TABLE transactions (
    txn_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    type VARCHAR(20),
    amount DOUBLE PRECISION,
    txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

4. Update DB credentials in:

```
DBConnection.java
```

5. Run the project 🚀

---

## 📸 Sample Output

```
==== BANK SYSTEM ====
1. Register
2. Login
3. Exit
```

---

## 💡 Future Enhancements

* 🔐 Password Hashing (Security)
* 💸 Fund Transfer Feature
* 🖥️ GUI using Java Swing
* 🌐 REST API (Spring Boot upgrade)

---

## 👨‍💻 Author

**Saqlain Raza Shaikh**
📧 [ssrz0119@gmail.com](mailto:ssrz0119@gmail.com)
🔗 LinkedIn: https://www.linkedin.com/in/saqlain-raza-shaikh-064b5b246

---

## ⭐ If you like this project, give it a star!
