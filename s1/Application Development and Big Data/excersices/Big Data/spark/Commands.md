# RDD Transformations and Actions in Apache Spark

## RDD Transformations
Οι μετασχηματισμοί δημιουργούν νέο RDD από υπάρχον. Είναι **νωχελικά αξιολογούμενες λειτουργίες** (lazy evaluation), δηλαδή δεν εκτελούνται μέχρι να κληθεί μια **Action**.

### Βασικές Εντολές
- **map**: Εφαρμόζει μια συνάρτηση σε κάθε στοιχείο του RDD και επιστρέφει νέο RDD.
```
  rdd = sc.parallelize([1, 2, 3])
  result = rdd.map(lambda x: x * 2)  # [2, 4, 6]
```
- **filter**: Φιλτράρει στοιχεία που ικανοποιούν μια συνθήκη.
```
rdd = sc.parallelize([1, 2, 3, 4])
result = rdd.filter(lambda x: x % 2 == 0)  # [2, 4]
```

- **flatMap**: Παράγει πολλαπλά στοιχεία από κάθε είσοδο.
```
rdd = sc.parallelize(["hello world", "spark"])
result = rdd.flatMap(lambda x: x.split(" "))  # ["hello", "world", "spark"]
```

- **distinct**: Επιστρέφει RDD με μοναδικές τιμές.
```
rdd = sc.parallelize([1, 2, 2, 3])
result = rdd.distinct()  # [1, 2, 3]
```

- **union**: Συνδυάζει δύο RDD.
```
rdd1 = sc.parallelize([1, 2])
rdd2 = sc.parallelize([3, 4])
result = rdd1.union(rdd2)  # [1, 2, 3, 4]
```

- **groupBy**: Δημιουργεί ένα RDD όπου τα στοιχεία ομαδοποιούνται βάσει μιας συνθήκης.
```
rdd = sc.parallelize([1, 2, 3, 4, 5, 6])
grouped_rdd = rdd.groupBy(lambda x: x % 2)
for key, values in grouped_rdd.collect():
    print(key, list(values))
```

# Έξοδος:
# 1 [1, 3, 5]
# 0 [2, 4, 6]

- **reduceByKey**: Συνδυάζει στοιχεία με βάση το κλειδί τους χρησιμοποιώντας μια συνάρτηση.
```
rdd = sc.parallelize([("a", 1), ("b", 1), ("a", 2)])
reduced_rdd = rdd.reduceByKey(lambda x, y: x + y)
print(reduced_rdd.collect())  # [('a', 3), ('b', 1)]
```

- **sortByKey**: Ταξινομεί ένα RDD βάσει των κλειδιών του.
```
rdd = sc.parallelize([(2, "b"), (1, "a"), (3, "c")])
sorted_rdd = rdd.sortByKey()
print(sorted_rdd.collect())  # [(1, 'a'), (2, 'b'), (3, 'c')]
```

- **join**: Ενώνει δύο RDD βάσει του κλειδιού τους.

```
rdd1 = sc.parallelize([("a", 1), ("b", 2)])
rdd2 = sc.parallelize([("a", 3), ("b", 4)])
joined_rdd = rdd1.join(rdd2)
print(joined_rdd.collect())  # [('a', (1, 3)), ('b', (2, 4))]
```

---

## RDD Actions
Οι ενέργειες ενεργοποιούν την εκτέλεση των μετασχηματισμών και επιστρέφουν αποτέλεσμα στον Driver.

### Βασικές Εντολές
- **collect**: Επιστρέφει όλα τα δεδομένα από το RDD.
```
rdd = sc.parallelize([1, 2, 3])
result = rdd.collect()  # [1, 2, 3]
```

- **count**: Επιστρέφει το πλήθος των στοιχείων.
```
rdd = sc.parallelize([1, 2, 3])
result = rdd.count()  # 3
```

- **take**: Παίρνει τα πρώτα N στοιχεία.
```
rdd = sc.parallelize([1, 2, 3])
result = rdd.take(2)  # [1, 2]
```

- **reduce**: Συνδυάζει τα στοιχεία χρησιμοποιώντας μια συνάρτηση.
```
rdd = sc.parallelize([1, 2, 3])
result = rdd.reduce(lambda x, y: x + y)  # 6
```

- **saveAsTextFile**: Αποθηκεύει το RDD σε αρχείο κειμένου.
```
rdd = sc.parallelize(["line1", "line2"])
rdd.saveAsTextFile("output")
```

Αυτές οι εντολές καλύπτουν τη βασική χρήση των Transformations και Actions στο RDD API. Χρησιμοποιήστε τα για να βελτιστοποιήσετε την εργασία σας με Apache Spark.
