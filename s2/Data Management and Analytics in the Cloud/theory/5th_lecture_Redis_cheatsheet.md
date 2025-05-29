# Redis Cheat Sheet

---

# 1. Redis Strings

## Πότε τα χρησιμοποιώ

1. **Caching** (προσωρινή αποθήκευση δεδομένων)
2. **Μετρητές** (π.χ. page views, likes)
3. **Session storage** (αποθήκευση session δεδομένων)
4. **Bitwise operations** (π.χ. flags, feature toggles)
5. **Αποθήκευση σειριοποιημένων αντικειμένων** (JSON, binary data)

## Παραδείγματα

### Βασικές λειτουργίες
```bash
SET user:100 "{"name": "John", "age":30}"  # O(1)
GET user:100  # O(1)

SETNX lock:resource1 "locked"  # O(1) - χρήσιμο για distributed locks
```

### Μετρητές
```bash
INCR page:views  # O(1) - αυξάνει μετρητή
INCRBY inventory:item42 5  # O(1) - αυξάνει κατά 5
DECR inventory:item42  # O(1) - μειώνει μετρητή
```

### Λειτουργίες με χρόνο λήξης
```bash
SET session:xyz "user_data" EX 3600  # O(1) - λήγει σε 1 ώρα
```

### Bitwise operations
```bash
SETBIT user:1000:flags 3 1  # O(1) - θέτει το 3ο bit σε 1
GETBIT user:1000:flags 3  # O(1) - διαβάζει το 3ο bit
```

## Big O Σημειώσεις

- **O(1)** (σταθερός χρόνος):
  - SET, GET, SETNX, SETEX
  - INCR, DECR, INCRBY, INCRBYFLOAT
  - APPEND, STRLEN
  - SETBIT, GETBIT, BITCOUNT (για μικρούς πίνακες bits)

- **O(n)** (γραμμικός χρόνος - προσοχή με μεγάλα strings):
  - GETRANGE, SETRANGE
  - BITOP (όταν εφαρμόζεται σε μεγάλα strings)
  - BITCOUNT (για μεγάλα εύρη bits)
  - SUBSTR

## Προτάσεις

1. Χρησιμοποιήστε strings για μικρά δεδομένα (< 1KB ιδανικά)
2. Αποφύγετε μεγάλα strings (> 1MB) γιατί οι O(n) λειτουργίες γίνονται αργές
3. Για απλούς μετρητές, οι atomic INCR/DECR είναι ιδανικές
4. Χρησιμοποιήστε SETEX/SET PX για δεδομένα με TTL (time-to-live)

Μέγιστο μέγεθος: 512MB ανά string (αλλά καλύτερα να μείνετε πολύ κάτω από αυτό το όριο για καλή απόδοση).

---

# 2. Redis Lists (Λίστες)

## Πότε τις χρησιμοποιώ

1. **Ουρές (Queues)** - FIFO (First In First Out)
2. **Στοίβες (Stacks)** - LIFO (Last In First Out)
3. **Timelines** (π.χ. τελευταίες δραστηριότητες χρήστη)
4. **Background job systems** (π.χ. Celery χρησιμοποιεί Redis lists για ουρές εργασιών)
5. **Capped collections** (περιορισμένο αριθμό τελευταίων στοιχείων)

## Παραδείγματα

### Ουρά (FIFO)
```bash
LPUSH orders:queue "order1"  # O(1)
LPUSH orders:queue "order2"  # O(1)
RPOP orders:queue  # O(1) -> "order1" (το παλιότερο)
```

### Στοίβα (LIFO)
```bash
LPUSH user:100:history "pageA"  # O(1)
LPUSH user:100:history "pageB"  # O(1)
LPOP user:100:history  # O(1) -> "pageB" (το νεότερο)
```

### Capped List (περιορισμένου μεγέθους)
```bash
LPUSH recent:logs "logEntry1"  # O(1)
LTRIM recent:logs 0 99  # O(n) - κρατά μόνο τα 100 νεότερα
```

### Blocking operations
```bash
BRPOP tasks 30  # O(1) - περιμένει μέχρι 30 δευτερόλεπτα για στοιχείο
```

## Big O Σημειώσεις

- **O(1)** (σταθερός χρόνος):
  - LPUSH, RPUSH, LPOP, RPOP
  - LLEN (μήκος λίστας)
  - LMOVE (ατομική μετακίνηση)
  - LTRIM (όταν κρατάμε μόνο τα N πρώτα ή τελευταία στοιχεία)

- **O(n)** (γραμμικός χρόνος - προσοχή με μεγάλες λίστες):
  - LRANGE (επιστροφή εύρους)
  - LINDEX (πρόσβαση με δείκτη)
  - LINSERT (εισαγωγή σε συγκεκριμένη θέση)
  - LREM (διαγραφή στοιχείων)
  - LSET (επεξεργασία με δείκτη)

## Προτάσεις

1. Χρησιμοποιήστε lists για collections που αλλάζουν συχνά (εισαγωγές/διαγραφές)
2. Προτιμήστε λειτουργίες στα άκρα (O(1)) όταν είναι δυνατόν
3. Για μεγάλες λίστες (> 10K στοιχεία), αποφύγετε LRANGE με μεγάλα εύρη
4. Χρησιμοποιήστε LTRIM για να διατηρείτε λίστες μικρού μεγέθους
5. Για blocking operations, χρησιμοποιήστε BRPOP/BLPOP αντί για polling

Μέγιστο μέγεθος: 2^32 - 1 στοιχεία (~4 δισεκατομμύρια), αλλά καλύτερα να μείνετε σε λίστες < 10K στοιχείων για βέλτιστη απόδοση.

---

# 3. Redis Sets (Σύνολα)

## Πότε τα χρησιμοποιώ

1. **Παρακολούθηση μοναδικών στοιχείων**:
   - Μοναδικοί επισκέπτες σελίδας (IPs)
   - Μοναδικοί χρήστες που έχουν δει ένα περιεχόμενο

2. **Διαχείριση ετικετών (tags)**:
   - Κατηγοριοποίηση προϊόντων
   - Ομαδοποίηση χρηστών

3. **Σχέσεις και γραφήματα**:
   - Φίλοι χρήστη
   - Συνδέσεις μεταξύ οντοτήτων

4. **Λειτουργίες συνόλων**:
   - Εύρεση κοινών στοιχείων (τομή)
   - Συνδυασμός συνόλων (ένωση)
   - Εξαίρεση στοιχείων (διαφορά)

## Παραδείγματα

### Βασικές λειτουργίες
```bash
SADD users:active "user1" "user2" "user3"  # O(1) ανά μέλος
SISMEMBER users:active "user2"  # O(1) → 1 (true)
SCARD users:active  # O(1) → 3
SREM users:active "user3"  # O(1)
```

### Λειτουργίες συνόλων
```bash
SADD group:A "user1" "user2" "user3"
SADD group:B "user2" "user3" "user4"
SINTER group:A group:B  # O(n*m) → "user2", "user3"
SUNION group:A group:B  # O(n) → "user1", "user2", "user3", "user4"
SDIFF group:A group:B  # O(n) → "user1"
```

### Τυχαία επιλογή
```bash
SRANDMEMBER group:A  # O(1) → τυχαίο μέλος
SPOP group:A  # O(1) → τυχαίο μέλος με διαγραφή
```

### Επαναληπτική ανάκτηση (για μεγάλα σύνολα)
```bash
SSCAN users:active 0 MATCH "user*" COUNT 100  # O(1) ανά κλήση
```

## Χρονική Πολυπλοκότητα (Big O)

- **O(1)**:
  - SADD, SREM, SISMEMBER, SCARD
  - SRANDMEMBER, SPOP
  - Κάθε βήμα SSCAN

- **O(n)**:
  - SMEMBERS (επιστρέφει όλα τα μέλη)
  - SUNION, SDIFF
  - SUNIONSTORE, SDIFFSTORE

- **O(n*m)**:
  - SINTER (n = μέγεθος μικρότερου συνόλου, m = πλήθος συνόλων)
  - SINTERSTORE

## Προτάσεις

1. Χρησιμοποιήστε `SSCAN` αντί για `SMEMBERS` για σύνολα > 10K στοιχείων
2. Αποφύγετε `SINTER` με πολλά μεγάλα σύνολα
3. Για συχνές ερωτήσεις τομής, αποθηκεύστε τα αποτελέσματα με `SINTERSTORE`
4. Το μέγιστο μέγεθος είναι 2^32-1 στοιχεία (~4 δισεκατομμύρια), αλλά καλύτερα < 100K για βέλτιστη απόδοση

---

# 4. Redis Hashes (Κατακερματισμοί)

## Πότε τα χρησιμοποιώ

1. **Αναπαράσταση αντικειμένων**:
   - Προφίλ χρηστών
   - Στοιχεία προϊόντων
   - Δομημένα δεδομένα με πολλά γνωρίσματα

2. **Ομαδοποιημένοι μετρητές**:
   - Στατιστικά στοιχεία
   - Μετρήσεις από διαφορετικές πηγές

3. **Ρυθμίσεις και παραμετροποίηση**:
   - Ρυθμίσεις συστήματος
   - Παραμέτρους διαμόρφωσης

## Παραδείγματα

### Βασικές λειτουργίες
```bash
HSET user:1000 username "john_doe" email "john@example.com" age 30  # O(1) ανά field
HGET user:1000 username  # O(1) → "john_doe"
HMGET user:1000 username age  # O(n) για n fields → "john_doe", "30"
HLEN user:1000  # O(1) → 3 (fields)
```

### Επεξεργασία τιμών
```bash
HINCRBY user:1000 visits 1  # O(1) → αυξάνει visits κατά 1
HINCRBYFLOAT product:777 price 0.50  # O(1) → αυξάνει price κατά 0.50
```

### Ανάκτηση δεδομένων
```bash
HGETALL user:1000  # O(n) → όλα τα field-value pairs
HKEYS user:1000  # O(n) → "username", "email", "age", "visits"
HVALS user:1000  # O(n) → "john_doe", "john@example.com", "30", "1"
```

### Επαναληπτική ανάκτηση (για μεγάλα hashes)
```bash
HSCAN user:1000 0 COUNT 100  # O(1) ανά κλήση
```

## Χρονική Πολυπλοκότητα (Big O)

- **O(1)**:
  - HSET, HGET, HEXISTS, HDEL
  - HINCRBY, HINCRBYFLOAT
  - HLEN
  - Κάθε βήμα HSCAN

- **O(n)**:
  - HGETALL, HKEYS, HVALS
  - HMGET (όπου n = αριθμός πεδίων που ζητούνται)
  - HSET με πολλαπλά πεδία (όπου n = αριθμός πεδίων)

## Προτάσεις

1. Χρησιμοποιήστε `HSCAN` αντί για `HGETALL`/`HKEYS`/`HVALS` για hashes > 10K πεδίων
2. Για συχνά ερωτήματα πολλαπλών πεδίων, προτιμήστε `HMGET` αντί για πολλαπλά `HGET`
3. Το μέγιστο μέγεθος είναι 2³²-1 ζεύγη πεδίων-τιμών (~4 δισεκατομμύρια)
4. Βέλτιστο για objects με < 100 πεδία
5. Για πολύ μικρά objects (1-2 πεδία), ίσως τα Strings (με JSON) να είναι πιο αποδοτικά

## Σύγκριση με Strings

Χρησιμοποιήστε Hashes όταν:
- Έχετε πολλά πεδία για το ίδιο αντικείμενο
- Χρειάζεστε atomic updates σε μεμονωμένα πεδία
- Θέλετε να αποφύγετε τη σειριοποίηση/αποσειριοποίηση

Χρησιμοποιήστε Strings όταν:
- Τα δεδομένα σας είναι πολύ απλά (1-2 πεδία)
- Χρησιμοποιείτε ήδη JSON ή άλλη μορφή σειριοποίησης
- Δεν χρειάζεστε atomic updates σε επιμέρους πεδία

---

# 5. Redis Sorted Sets (Ταξινομημένα Σύνολα)

## Πότε τα χρησιμοποιώ

1. **Πίνακες κατάταξης (Leaderboards)**:
   - Ταξινόμηση παικτών σε παιχνίδι
   - Δημοφιλή προϊόντα/άρθρα

2. **Χρονικές σειρές δεδομένων**:
   - Event timestamps (χρήση timestamp ως score)
   - Sliding window rate limiting

3. **Διαχείριση προτεραιοτήτων**:
   - Ουρές εργασιών με προτεραιότητες
   - Task scheduling

## Παραδείγματα

### Βασικές λειτουργίες
```bash
ZADD leaderboard 100 "player1" 200 "player2"  # O(log N) ανά μέλος
ZSCORE leaderboard "player1"  # O(1) → "100"
ZRANK leaderboard "player2"  # O(log N) → 1 (θέση σε αύξουσα σειρά)
ZREVRANK leaderboard "player2"  # O(log N) → 0 (θέση σε φθίνουσα σειρά)
ZCARD leaderboard  # O(1) → 2
```

### Ανάκτηση εύρους
```bash
ZADD scores 150 "A" 250 "B" 300 "C" 100 "D"
ZRANGE scores 0 2 WITHSCORES  # O(log N + M) → "D","100","A","150","B","250" (top 3)
ZREVRANGE scores 0 1 WITHSCORES  # O(log N + M) → "C","300","B","250" (top 2)
```

### Εύρεση με βάση score
```bash
ZRANGEBYSCORE scores 100 200  # O(log N + M) → μέλη με score 100-200
ZCOUNT scores 100 300  # O(log N) → αριθμός μελών με score 100-300
```

### Rate limiting
```bash
ZADD ratelimit:user1 <current_timestamp> <request_id>  # O(log N)
ZREMRANGEBYSCORE ratelimit:user1 -inf (<current_timestamp>-60)  # O(log N + M)
```

## Χρονική Πολυπλοκότητα (Big O)

- **O(log N)**:
  - ZADD, ZREM, ZRANK, ZREVRANK
  - ZINCRBY, ZCOUNT
  - ZPOPMIN, ZPOPMAX

- **O(1)**:
  - ZCARD, ZSCORE

- **O(log N + M)**:
  - ZRANGE, ZREVRANGE
  - ZRANGEBYSCORE, ZREVRANGEBYSCORE
  - ZREMRANGEBYRANK, ZREMRANGEBYSCORE

- **O(N)**:
  - ZINTERSTORE, ZUNIONSTORE (για πολλαπλά σύνολα)

## Προτάσεις

1. Χρησιμοποιήστε `ZSCAN` για επανάληπτη ανάκτηση μεγάλων συνόλων (>10K μελών)
2. Για leaderboards, συνδυάστε `ZREVRANGE` με `WITHSCORES`
3. Χρησιμοποιήστε timestamps ως scores για χρονικά δεδομένα
4. Το μέγιστο μέγεθος είναι 2³²-1 μέλη (~4 δισεκατομμύρια)
5. Βέλτιστη απόδοση για σύνολα <100K μελών

## Σύγκριση με άλλες δομές

Χρησιμοποιήστε Sorted Sets όταν:
- Χρειάζεστε ταξινόμηση και range queries
- Θέλετε μοναδικά μέλη με scores
- Χρειάζεστε efficient rank/top-k queries

Αποφύγετε όταν:
- Δεν χρειάζεστε ταξινόμηση (απλά Sets είναι πιο γρήγορα)
- Έχετε πολύ μεγάλα σύνολα με συχνά updates (λόγω O(log N) κόστους)
