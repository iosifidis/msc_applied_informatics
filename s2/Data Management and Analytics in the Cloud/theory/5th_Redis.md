### 📊 **Redis Data Types - Cheat Sheet**

---

## 🔤 **1. Redis Strings**
- **Περιγραφή:**
  - Αποθήκευση ακολουθιών bytes (κείμενο, δυαδικά δεδομένα, serialized objects).
  - Χρήση για caching, counters και bitwise operations.

- **Βασικές Εντολές:**
  - `SET key value` – Αποθήκευση τιμής.
  - `GET key` – Ανάκτηση τιμής.
  - `SETNX key value` – Αποθήκευση αν δεν υπάρχει το κλειδί (locks).
  - `MGET key1 key2` – Ανάκτηση πολλαπλών τιμών.
  - `INCR key` – Αύξηση κατά 1 (counter).
  - `INCRBY key n` – Αύξηση κατά n.
  - `INCRBYFLOAT key x` – Αύξηση κατά x (float).

- **Παράδειγμα:**
```bash
> SET user:1 "Alex"
> GET user:1
"Alex"
> INCR views:page:2
(integer) 1
```

- **Όρια:** Έως **512 MB** ανά string.

- **Απόδοση:** Οι περισσότερες εντολές είναι **O(1)**. Προσοχή στις `SUBSTR`, `GETRANGE`, `SETRANGE` που είναι **O(n)**.

---

## 📋 **2. Redis Lists**
- **Περιγραφή:**
  - Συνδεδεμένες λίστες από strings.
  - Χρήση για **stacks (LIFO)** και **queues (FIFO)**.

- **Βασικές Εντολές:**
  - `LPUSH key value` – Προσθήκη στην αρχή.
  - `RPUSH key value` – Προσθήκη στο τέλος.
  - `LPOP key` – Αφαίρεση από την αρχή.
  - `RPOP key` – Αφαίρεση από το τέλος.
  - `LLEN key` – Μήκος λίστας.
  - `LMOVE src dest LEFT RIGHT` – Μετακίνηση στοιχείου μεταξύ λιστών.
  - `LTRIM key start stop` – Περικοπή λίστας.

- **Παράδειγμα (FIFO Queue):**
```bash
> LPUSH work:queue 101
> RPUSH work:queue 237
> RPOP work:queue
"101"
```

- **Όρια:** Μέγιστο μέγεθος **2³² - 1** (4,294,967,295 στοιχεία).

- **Απόδοση:** Ενέργειες στην αρχή/τέλος είναι **O(1)**. Προσοχή στα `LINDEX`, `LINSERT`, `LSET` που είναι **O(n)**.

---

## 🔢 **3. Redis Sets**
- **Περιγραφή:**
  - **Μη ταξινομημένο** σύνολο **μοναδικών** strings.
  - Χρήση για παρακολούθηση **μοναδικών αντικειμένων** και **συνόλων** (π.χ., intersection, union).

- **Βασικές Εντολές:**
  - `SADD key value` – Προσθήκη στοιχείου.
  - `SREM key value` – Αφαίρεση στοιχείου.
  - `SISMEMBER key value` – Έλεγχος ύπαρξης.
  - `SCARD key` – Μέγεθος συνόλου.
  - `SINTER key1 key2` – Τομή συνόλων.

- **Παράδειγμα:**
```bash
> SADD user:123:favorites 347
> SADD user:123:favorites 561
> SISMEMBER user:123:favorites 561
(integer) 1
> SINTER user:123:favorites user:456:favorites
"561"
```

- **Όρια:** Μέγιστο **2³² - 1** στοιχεία (4,294,967,295).

- **Απόδοση:** Οι περισσότερες λειτουργίες είναι **O(1)**. Προσοχή στην `SMEMBERS` (O(n)). Χρήση `SSCAN` για μεγάλα σύνολα.

---

## 🗂️ **4. Redis Hashes**
- **Περιγραφή:**
  - Δομή που αποθηκεύει **ζεύγη πεδίο-τιμή** (key-value pairs).
  - Χρήση για **αντικείμενα** και **καταμετρητές**.

- **Βασικές Εντολές:**
  - `HSET key field value` – Ορισμός τιμής σε πεδίο.
  - `HGET key field` – Ανάκτηση τιμής.
  - `HMGET key field1 field2` – Ανάκτηση πολλών πεδίων.
  - `HINCRBY key field n` – Αύξηση τιμής πεδίου κατά n.
  - `HGETALL key` – Ανάκτηση όλων των πεδίων και τιμών.

- **Παράδειγμα:**
```bash
> HSET user:123 username "martina" age 29
> HGET user:123 username
"martina"
> HINCRBY user:123 views 1
(integer) 1
```

- **Όρια:** Έως **4,294,967,295** ζεύγη πεδίο-τιμή.

- **Απόδοση:** Οι περισσότερες εντολές είναι **O(1)**. Προσοχή στις `HKEYS`, `HVALS`, `HGETALL` (O(n)).

---

## 📊 **5. Redis Sorted Sets**
- **Περιγραφή:**
  - Μοναδικά strings με **βαθμολογία (score)** για **ταξινομημένη αποθήκευση**.
  - Χρήση σε **leaderboards** και **rate limiters**.

- **Βασικές Εντολές:**
  - `ZADD key score member` – Προσθήκη στοιχείου με βαθμολογία.
  - `ZRANGE key start stop` – Ανάκτηση στοιχείων (ascending).
  - `ZREVRANK key member` – Θέση στοιχείου (descending).
  - `ZREM key member` – Διαγραφή στοιχείου.

- **Παράδειγμα (Leaderboard):**
```bash
> ZADD leaderboard 100 user:1
> ZADD leaderboard 150 user:2
> ZRANGE leaderboard 0 -1 WITHSCORES
1) "user:1"
2) "100"
```

- **Απόδοση:** Οι περισσότερες λειτουργίες είναι **O(log(n))**. Προσοχή στο `ZRANGE` με μεγάλες επιστροφές (O(log(n) + m)).

---

✍️ **Σημείωση:** Το Redis είναι **εξαιρετικά γρήγορο** λόγω της αποθήκευσης δεδομένων στη μνήμη. Χρησιμοποιήστε τις **κατάλληλες δομές** ανάλογα με την περίπτωση για **απόδοση** και **αποδοτικότητα**.
