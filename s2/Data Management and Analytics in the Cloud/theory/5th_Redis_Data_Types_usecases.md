# Περιπτώσεις χρήσης των δομών δεδομένων του Redis

Οι δομές δεδομένων του Redis επιλέγονται βάσει των απαιτήσεων της εφαρμογής, με κριτήρια όπως **ταχύτητα**, **πολυπλοκότητα ερωτημάτων**, και **φύση των δεδομένων**. Ακολουθεί ανάλυση ανά περίπτωση χρήσης:

---

### **1. Strings (Αλφαριθμητικά)**
- **Χρήση**:  
  - Αποθήκευση **απλών τιμών** (κειμένου, αριθμών, δυαδικών δεδομένων).  
  - **Cache** (π.χ., HTML σελίδες, session data).  
  - **Counters** (π.χ., `INCR` για page views).  
  - **Διαρκείς μεταβλητές** (π.χ., ρυθμίσεις).  
- **Παράδειγμα**:  
  ```redis
  SET user:123:session "abc123" EX 3600  // Cache session με TTL 1 ώρα
  INCR site:visits  // Μετρητής επισκέψεων
  ```

---

### **2. Lists (Λίστες)**
- **Χρήση**:  
  - **Ουρές (Queues)** (π.χ., εργασίες background με `LPUSH`/`RPOP`).  
  - **Stacks** (με `LPUSH`/`LPOP`).  
  - **Χρονολογικές λίστες** (π.χ., τελευταίες δραστηριότητες χρήστη).  
- **Παράδειγμα**:  
  ```redis
  LPUSH notifications:user1 "New message"  // Ουρά ειδοποιήσεων
  LRANGE posts:recent 0 9  // 10 πιο πρόσφατες δημοσιεύσεις
  ```

---

### **3. Sets (Σύνολα)**
- **Χρήση**:  
  - **Μοναδικά στοιχεία** (π.χ., tags, followers).  
  - **Συνόλου πράξεις** (ένωση, τομή).  
  - **Φιλτράρισμα duplicates** (π.χ., μοναδικοί επισκέπτες).  
- **Παράδειγμα**:  
  ```redis
  SADD article:123:tags "tech" "redis"  // Tags άρθρου
  SINTER user1:followers user2:followers  // Κοινό followers
  ```

---

### **4. Sorted Sets (ZSETs)**
- **Χρήση**:  
  - **Rankings** (π.χ., leaderboards με score).  
  - **Χρονοσειρές** (timestamp ως score).  
  - **Εύρος τιμών** (π.χ., εύρεση προϊόντων με τιμή 10-20€).  
- **Παράδειγμα**:  
  ```redis
  ZADD leaderboard 1000 "player1"  // Score παίκτη
  ZRANGE leaderboard 0 9 WITHSCORES  // Top 10
  ```

---

### **5. Hashes (Πίνακες Κατακερματισμού)**
- **Χρήση**:  
  - **Αντικείμενα** (π.χ., προφίλ χρήστη με πεδία `name`, `email`).  
  - **Ομαδοποίηση δεδομένων** (π.χ., ρυθμίσεις συστήματος).  
- **Παράδειγμα**:  
  ```redis
  HSET user:123 name "Alice" email "alice@example.com"  // Αποθήκευση αντικειμένου
  HGETALL user:123  // Ανάκτηση όλων πεδίων
  ```

---

### **6. Bitmaps**
- **Χρήση**:  
  - **Στατιστικά real-time** (π.χ., active users ανά ημέρα).  
  - **Feature flags** (δυαδικές καταστάσεις).  
- **Παράδειγμα**:  
  ```redis
  SETBIT logins:2023-10-01 123 1  // Ο χρήστης 123 συνδέθηκε
  BITCOUNT logins:2023-10-01  // Πλήθος συνδέσεων
  ```

---

### **7. HyperLogLog**
- **Χρήση**:  
  - **Εκτίμηση μοναδικών στοιχείων** (π.χ., unique visitors) **με ελάχιστη μνήμη**.  
- **Παράδειγμα**:  
  ```redis
  PFADD site:visitors "192.168.1.1"  // Προσθήκη IP
  PFCOUNT site:visitors  // Μοναδικοί επισκέπτες (~99% ακρίβεια)
  ```

---

### **8. Streams**
- **Χρήση**:  
  - **Event sourcing** (π.χ., μηνύματα chat, ενημερώσεις stock).  
  - **Consumer groups** (κατανεμημένη επεξεργασία μηνυμάτων).  
- **Παράδειγμα**:  
  ```redis
  XADD orders * product_id 123 quantity 5  // Event νέας παραγγελίας
  XREAD STREAMS orders 0  // Ανάγνωση events
  ```

---

### **9. Geospatial**
- **Χρήση**:  
  - **Εύρεση σημείων σε ακτίνα** (π.χ., delivery tracking, εφαρμογές location-based).  
- **Παράδειγμα**:  
  ```redis
  GEOADD stores 23.7275 37.9838 "Athens Store"  // Προσθήκη συντεταγμένων
  GEORADIUS stores 23.7 37.9 10 km  // Καταστήματα σε 10km
  ```

---

### **10. JSON (με RedisJSON module)**
- **Χρήση**:  
  - **Αποθήκευση και ερώτημα JSON** (π.χ., nested δεδομένα όπως προφίλ χρηστών με υπο-αντικείμενα).  
- **Παράδειγμα**:  
  ```redis
  JSON.SET user:123 $ '{"name":"Alice", "address":{"city":"Athens"}}'
  JSON.GET user:123 $.address.city  // "Athens"
  ```

---

### **Πίνακας Σύνοψης**
| **Δομή**         | **Κύρια Χρήση**                          | **Πλεονεκτήματα**               |
|------------------|----------------------------------------|--------------------------------|
| Strings          | Cache, counters                        | Απλότητα, O(1) access         |
| Lists            | Ουρές, stacks                         | Γρήγορη εισαγωγή/διαγραφή      |
| Sets             | Μοναδικά στοιχεία, πράξεις συνόλων    | Αποδοτικότητα με unions        |
| Sorted Sets      | Rankings, εύρη τιμών                  | Ταξινόμηση + γρήγορη αναζήτηση |
| Hashes           | Αντικείμενα, ομαδοποίηση πεδίων       | O(1) ανά πεδίο                |
| Bitmaps          | Δυαδικά στατιστικά                    | Ελάχιστη μνήμη                |
| HyperLogLog      | Cardinality estimation                | Ακρίβεια με ~1% σφάλμα        |
| Streams          | Event streaming, messaging            | Υποστήριξη consumer groups    |
| Geospatial       | Location-based εφαρμογές              | Γεωχωρικά ερωτήματα           |
| JSON             | Nested δεδομένα                       | Επεξεργασία JSON              |

---

### **Πότε να Επιλέξετε Redis;**
- **Γρήγορη πρόσβαση**: Όταν χρειάζεστε latency <1ms (in-memory).  
- **Πολυπλοκότητα με απλότητα**: Αν θέλετε πολλές δομές δεδομένων σε ένα εργαλείο.  
- **Real-time εφαρμογές**: Chat, gaming, analytics.  
- **Προσοχή**: Δεν είναι κατάλληλο για:  
  - **Δεδομένα με σχέσεις** (όπως SQL για JOINs).  
  - **Μεγάλα δεδομένα εκτός μνήμης** (χρειάζεται ρύθμιση για persistence).  

Για λεπτομέρειες: [Redis Documentation](https://redis.io/docs/data-types/).
