# Παραδείγματα

1. Βρείτε τους ηθοποιούς που έχουν παίξει σε ταινία που έχει κυκλοφορήσει μετά το 2000 και το όνομα τους αρχίζει από “Μ” ή έχουν παίξει το ρόλο του “Neo”.

```
MATCH (p:Person)-[r:ACTED_IN]->(m:Movie)
WHERE m.released > 2000 AND (p.name STARTS WITH "M" OR "Neo" IN
r.roles)
RETURN p
```

2. Βρείτε τα άτομα που συμμετείχαν και ως ηθοποιοί και ως σκηνοθέτες στην ίδια ταινία. Εμφανίστε το όνομα του ανθρώπου και τον τίτλο της ταινίας.

```
MATCH (p:Person)-[:ACTED_IN]->(m:Movie)
WHERE (p)-[:DIRECTED]->(m)
RETURN p.name, m.title
```

3. Βρείτε το όνομα των σκηνοθετών που δεν έχουν παίξει ποτέ.

```
MATCH (p:Person)-[:DIRECTED]->(m:Movie)
WHERE NOT (p)-[:ACTED_IN]->()
RETURN DISTINCT p.name
```

4. Εμφανίστε το πλήθος των ταινιών που έχει παίξει κάθε ηθοποιός που έπαιξε στο Matrix, ταξινομημένο σε φθίνουσα σειρά.

```
MATCH (:Movie { title: "The Matrix" })<-[:ACTED_IN]-(actor)-
[:ACTED_IN]->(movie)
RETURN actor.name, COUNT(*) AS count
ORDER BY count DESC
```

5. Το πλήθος των ηθοποιών που έχουν γεννηθεί μεταξύ 1955 και 1975 ανά ταινία.

```
MATCH (p)-[:ACTED_IN]->(m)
WHERE p.born>=1955 AND p.born<=1975
RETURN m.title, COUNT(*) AS count
```

6. Βρείτε την ταινία στην οποία έχουν παίξει οι περισσότεροι ηθοποιοί.

```
MATCH (m:Movie)<-[:ACTED_IN]-(p:Person)
WITH m, COUNT(p) AS played
RETURN m.title, played
ORDER BY played DESC
LIMIT 1
```

7. Τους ηθοποιούς που έχουν παίξει στην ταινία “Snow Falling on Cedars” ή στην ταινία “The Green Mile”. Να εμφανίζονται και οι ταινίες.

```
MATCH (p)-[:ACTED_IN]->(m)
WHERE m.title="Snow Falling on Cedars" OR m.title ="The Green Mile"
RETURN m,p
```

8. Τις ταινίες στις οποίες έχει παίξει και ο Keanu Reeves και ο Hugo Weaving.

```
MATCH (p)-[:ACTED_IN]->(m)<-[:ACTED_IN]-(p1)
WHERE p.name="Keanu Reeves" AND p1.name="Hugo Weaving"
RETURN m
```

9. Τα ονόματα των ηθοποιών που δεν έχουν παίξει σε καμία ταινία που έχει παίξει ο Keanu Reeves αλλά ούτε και ο Tom Hanks.

```
MATCH (p)-[:ACTED_IN]->(m)
WHERE NOT (p)-[:ACTED_IN]->()<-[:ACTED_IN]-(:Person{name:"Keanu
Reeves"}) AND NOT (p)-[:ACTED_IN]->()<-[:ACTED_IN]-
(:Person{name:"Tom Hanks"}) AND p.name<>"Keanu Reeves" AND
p.name<>"Tom Hanks"
RETURN DISTINCT p.name
```

10. Τα ονόματα των ηθοποιών που έχουν παίξει σε τουλάχιστον 3 ταινίες, ταξινομημένα από τον ηθοποιό με τις περισσότερες ταινίες προς αυτόν με τις λιγότερες.

```
MATCH (p)-[:ACTED_IN]->(m)
WITH p, count(*) AS played
WHERE played>=3
RETURN p.name, played
ORDER BY played DESC
```

11. Εμφανίστε το πλήθος των ηθοποιών που έχει κάθε ταινία για τις ταινίες στις οποίες έχει παίξει ένας ηθοποιός που έπαιξε και στο “A Few Good Men” ταξινομόντας τις ταινίες κατά φθίνουσα σειρά με βάση το πλήθος.

```
MATCH (:Movie{title:"A Few Good Men"})<-[:ACTED_IN]-(p:Person)-
[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]-(p1:Person)
RETURN m.title, COUNT(DISTINCT p1) AS played
ORDER BY played DESC
```

12. Οι ηθοποίοι που έχουν παίξει μαζί σε δύο ταινίες (τουλάχιστον).

```
MATCH (m1)<-[:ACTED_IN]-(p)-[:ACTED_IN]->(m)<-[:ACTED_IN]-(p1)-
[:ACTED_IN]->(m1)
WHERE p.name<p1.name
RETURN DISTINCT p.name, p1.name
```

13. Για κάθε ηθοποιό, το μήκος του συντομότερου που τον συνδέει με τον Tom Hanks.

```
MATCH p=shortestPath((tom:Person {name:"Tom Hanks"})-[*]-
(p1:Person) )
WHERE p1<>bacon
RETURN p1.name, length(p)
```

14. Ζευγή ατόμων και ταινιών όπου το άτομο συνδέεται με τουλάχιστον δύο διαφορετικούς τύπους σχέσεων με την ταινία.

```
MATCH (p:Person)-[relatedTo]-(m:Movie)
WITH p,m, COUNT(DISTINCT Type(relatedTo)) AS types
WHERE types>=2
RETURN p.name, m.title, types
```

15. Όλους τους κόμβους τύπου Person.

```
MATCH (n:Person) RETURN n
```

16. Τις ταινίες στις οποίες έχει παίξει ο Tom Hanks μαζί με είτε την Meg Ryan, είτε τον Kevin Bacon.

```
MATCH (p:Person{name:'Tom Hanks'})-[:ACTED_IN]->(m)<-[:ACTED_IN]-
(:Person{name:'Meg Ryan'}),(p)-[:ACTED_IN]->(m1)<-[:ACTED_IN]-
(:Person{name:'Kevin Bacon'})
RETURN m,m1

MATCH (p:Person{name:'Tom Hanks'})-[:ACTED_IN]->(m)<-[:ACTED_IN]-
(:Person{name:'Meg Ryan'})
RETURN m
UNION
MATCH (:Person{name:'Tom Hanks'})-[:ACTED_IN]->(m)<-[:ACTED_IN]-
(:Person{name:'Kevin Bacon'})
RETURN m
```

17. Τα άτομα που δεν έχουν παίξει σε καμία ταινία.

```
MATCH (p:Person)
WHERE NOT (p)-[:ACTED_IN]->(:Movie)
RETURN p

MATCH (p:Person)
WHERE NOT EXISTS((p)-[:ACTED_IN]->(:Movie))
RETURN p
```

18. Τους κόμβους που απέχουν το πολύ 3 και από τον Tom Hanks και από τον Keanu Reeves.

```
MATCH (p2{name:'Keanu Reeves'})-[*1..3]-(p)-[*1..3]-(p1{name:'Tom
Hanks'})
RETURN p
```

19. Τα άτομα που είτε έγραψαν είτε σκηνοθέτησαν κάποια ταινία μετά το 1970 (εμφανίστε και τις ταινίες).

```
MATCH (p:Person)-[:DIRECTED|WROTE]->(m:Movie)
WHERE m.released>1970
RETURN p,m
```

20. Το πλήθος και οι τίτλοι των ταινιών που έχει παίξει ο κάθε ηθοποιός σε λίστα.

```
MATCH (p:Person)-[:ACTED_IN]->(m:Movie)
RETURN p.name, COUNT(*), collect(m.title)
```
