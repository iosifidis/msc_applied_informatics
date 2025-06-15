# **Σενάριο Γραφήματος Neo4j: Εταιρείες, Υπάλληλοι και Projects**

**Οντότητες και Σχέσεις:**

* (:Εταιρεία {όνομα})
* (:Υπάλληλος {όνομα, ειδικότητα})
* (\:Project {τίτλος, προϋπολογισμός})
* Σχέσεις:

  * (:Υπάλληλος)-\[:ΕΡΓΑΖΕΤΑΙ\_ΣΕ]->(:Εταιρεία)
  * (:Υπάλληλος)-\[:ΣΥΜΜΕΤΕΧΕΙ]->(\:Project)
  * (\:Project)-\[:ΑΝΗΚΕΙ\_ΣΕ]->(:Εταιρεία)
  * (:Υπάλληλος)-\[:ΣΥΝΕΡΓΑΖΕΤΑΙ\_ΜΕ]->(:Υπάλληλος)

---

**Ερωτήσεις Πολλαπλής Επιλογής (1-10 από 100)**

1. Ποιο ερώτημα βρίσκει τους υπαλλήλους που δεν εργάζονται σε καμία εταιρεία;

α. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

β. `MATCH (u:Υπάλληλος) WHERE NOT (u)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->() RETURN u`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος) RETURN DISTINCT e`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->() RETURN u`

**Σωστή απάντηση: β**

---

2. Ποιο ερώτημα βρίσκει τα projects χωρίς καθόλου συμμετοχές υπαλλήλων;

α. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN DISTINCT p`

β. `MATCH (p:Project) WHERE NOT ()-[:ΣΥΜΜΕΤΕΧΕΙ]->(p) RETURN p`

γ. `MATCH (p:Project)<-[:ΑΝΗΚΕΙ_ΣΕ]-(e:Εταιρεία) RETURN p`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN DISTINCT p`

**Σωστή απάντηση: β**

---

3. Ποιο ερώτημα επιστρέφει τις εταιρείες που έχουν τουλάχιστον 3 projects;

α. `MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project) RETURN e`

β. `MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project) WITH e, COUNT(p) AS c WHERE c >= 3 RETURN e`

γ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN DISTINCT e`

δ. `MATCH (e:Εταιρεία) RETURN e SKIP 3`

**Σωστή απάντηση: β**

---

4. Ποιο ερώτημα βρίσκει τους υπαλλήλους που εργάζονται σε εταιρεία με όνομα "TechNova";

α. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία {όνομα:'TechNova'}) RETURN u`

β. `MATCH (e:Εταιρεία {όνομα:'TechNova'})<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος) RETURN e`

γ. `MATCH (u:Υπάλληλος) WHERE u.εταιρεία = 'TechNova' RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(e:Εταιρεία {όνομα:'TechNova'}) RETURN u`

**Σωστή απάντηση: α**

---

5. Ποιο ερώτημα επιστρέφει τους υπαλλήλους που συμμετέχουν σε projects με προϋπολογισμό > 100000;

α. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) WHERE p.προϋπολογισμός > 100000 RETURN u`

β. `MATCH (p:Project) WHERE p.προϋπολογισμός > 100000 RETURN p`

γ. `MATCH (u:Υπάλληλος) WHERE u.προϋπολογισμός > 100000 RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) WHERE e.προϋπολογισμός > 100000 RETURN u`

**Σωστή απάντηση: α**

---

6. Ποιο ερώτημα βρίσκει projects στα οποία συμμετέχουν "Data Scientist" υπάλληλοι;

α. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) WHERE u.ειδικότητα = 'Data Scientist' RETURN p`

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (u:Υπάλληλος {ειδικότητα:'Data Scientist'}) RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(p:Project) RETURN p`

**Σωστή απάντηση: α**

---

7. Ποιο ερώτημα βρίσκει τα ονόματα των υπαλλήλων που συνεργάζονται με την "Eleni";

α. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος {όνομα:'Eleni'}) RETURN u1.όνομα`

β. `MATCH (u1:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(u2:Υπάλληλος {όνομα:'Eleni'}) RETURN u1.όνομα`

γ. `MATCH (u1:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) WHERE u1.όνομα='Eleni' RETURN u1.όνομα`

δ. `MATCH (u2:Υπάλληλος {όνομα:'Eleni'})-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u1:Υπάλληλος) RETURN u2.όνομα`

**Σωστή απάντηση: α**

---

8. Ποιο ερώτημα επιστρέφει projects που ανήκουν σε εταιρεία με όνομα που ξεκινάει από "Inno";

α. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) WHERE e.όνομα STARTS WITH 'Inno' RETURN p`

β. `MATCH (e:Εταιρεία) WHERE e.όνομα STARTS WITH 'Inno' RETURN e`

γ. `MATCH (p:Project) WHERE p.όνομα STARTS WITH 'Inno' RETURN p`

δ. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN p`

**Σωστή απάντηση: α**

---

9. Ποιο ερώτημα βρίσκει τις εταιρείες χωρίς κανέναν υπάλληλο;

α. `MATCH (e:Εταιρεία) WHERE NOT ()-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e) RETURN e`

β. `MATCH (e:Εταιρεία)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->() RETURN e`

γ. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN e`

δ. `MATCH (u:Υπάλληλος) WHERE NOT (u)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->() RETURN u`

**Σωστή απάντηση: α**

---

10. Ποιο ερώτημα βρίσκει υπαλλήλους που συμμετέχουν σε τουλάχιστον 2 projects;

α. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) WITH u, COUNT(p) AS num WHERE num >= 2 RETURN u`

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) WITH COUNT(p) > 2 RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->() RETURN u`

**Σωστή απάντηση: α**

---

11. Ποιο ερώτημα βρίσκει υπαλλήλους που εργάζονται σε τουλάχιστον δύο διαφορετικές εταιρείες;

α. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) WITH u, COUNT(DISTINCT e) AS cnt WHERE cnt >= 2 RETURN u`

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος) WITH COUNT(e) > 2 RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(e:Εταιρεία) RETURN u`

**Σωστή απάντηση: α**

---

12. Ποιο ερώτημα επιστρέφει projects που έχουν δημιουργηθεί από περισσότερες από μία εταιρείες;

α. `MATCH (p:Project)<-[:ΑΝΗΚΕΙ_ΣΕ]-(e:Εταιρεία) WITH p, COUNT(DISTINCT e) AS c WHERE c > 1 RETURN p`

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN p`

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN DISTINCT p`

δ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

**Σωστή απάντηση: α**

---

13. Ποιο ερώτημα βρίσκει όλους τους υπαλλήλους που συνεργάζονται έμμεσα με την "Maria"; (δηλ. μέσω άλλων συνεργατών)

α. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ*2..]->(u2:Υπάλληλος {όνομα:'Maria'}) RETURN DISTINCT u1`

β. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος {όνομα:'Maria'}) RETURN u1`

γ. `MATCH (u1:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u2:Υπάλληλος {όνομα:'Maria'}) RETURN u1`

δ. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ*]->(u2:Υπάλληλος {όνομα:'Maria'}) WHERE length(path) = 1 RETURN u1`

**Σωστή απάντηση: α**

---

14. Ποιο ερώτημα επιστρέφει υπαλλήλους που εργάζονται στην ίδια εταιρεία με την "Dimitris";

α. `MATCH (u1:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u2:Υπάλληλος {όνομα:'Dimitris'}) WHERE u1 <> u2 RETURN u1`

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος {όνομα:'Dimitris'}) RETURN e`

δ. `MATCH (u:Υπάλληλος {όνομα:'Dimitris'})-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(c:Υπάλληλος) RETURN c`

**Σωστή απάντηση: α**

---

15. Ποιο ερώτημα επιστρέφει τον αριθμό των υπαλλήλων ανά ειδικότητα;

α. `MATCH (u:Υπάλληλος) RETURN u.ειδικότητα, COUNT(*) AS αριθμός`

β. `MATCH (u:Υπάλληλος) RETURN COUNT(u.ειδικότητα)`

γ. `MATCH (u:Υπάλληλος) WITH COUNT(u) AS c RETURN c`

δ. `MATCH (u:Υπάλληλος) RETURN DISTINCT u.ειδικότητα`

**Σωστή απάντηση: α**

---

16. Ποιο ερώτημα επιστρέφει τα projects στα οποία δεν συμμετέχουν υπάλληλοι με ειδικότητα 'Manager';

α. `MATCH (p:Project) WHERE NOT EXISTS { MATCH (u:Υπάλληλος {ειδικότητα: 'Manager'})-[:ΣΥΜΜΕΤΕΧΕΙ]->(p) } RETURN p`

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) WHERE u.ειδικότητα <> 'Manager' RETURN DISTINCT p`

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: α**

---

17. Ποιο ερώτημα βρίσκει τον υπάλληλο με τα περισσότερα projects;

α. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) WITH u, COUNT(p) AS num ORDER BY num DESC LIMIT 1 RETURN u`

β. `MATCH (u:Υπάλληλος) RETURN COUNT(u)`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN COUNT(p)`

**Σωστή απάντηση: α**

---

18. Ποιο ερώτημα βρίσκει εταιρείες στις οποίες δεν ανήκει κανένα project;

α. `MATCH (e:Εταιρεία) WHERE NOT EXISTS { MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e) } RETURN e`

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN e`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος) RETURN e`

δ. `MATCH (p:Project) RETURN DISTINCT p`

**Σωστή απάντηση: α**

---

19. Ποιο ερώτημα επιστρέφει projects που έχουν υπαλλήλους από περισσότερες από μία εταιρείες;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WITH p, COUNT(DISTINCT e) AS cnt
WHERE cnt > 1
RETURN p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN DISTINCT p`

γ. `MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project) RETURN e`

δ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

**Σωστή απάντηση: α**

---

20. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται μεταξύ τους και ανήκουν στην ίδια εταιρεία;

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος),
      (u1)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία),
      (u2)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e)
RETURN DISTINCT u1, u2
```

β. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) RETURN u1, u2`

γ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

δ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος) RETURN e`

**Σωστή απάντηση: α**

---

21. Ποιο ερώτημα επιστρέφει υπαλλήλους που δεν συνεργάζονται με κανέναν;

α. `MATCH (u:Υπάλληλος) WHERE NOT (u)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->() RETURN u`

β. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(x) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

δ. `MATCH (u:Υπάλληλος) WHERE (u)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->() RETURN u`

**Σωστή απάντηση: α**

---

22. Ποιο ερώτημα βρίσκει υπαλλήλους που συμμετέχουν σε projects που ανήκουν σε εταιρείες εκτός της δικής τους;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e1:Εταιρεία),
      (u)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία)
WHERE e1 <> e2
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN DISTINCT u`

γ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

δ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

**Σωστή απάντηση: α**

---

23. Ποιο ερώτημα επιστρέφει τον συνολικό αριθμό projects ανά εταιρεία;

α. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN e.name, COUNT(p) AS projectCount`

β. `MATCH (e:Εταιρεία) RETURN COUNT(e)`

γ. `MATCH (p:Project) RETURN COUNT(DISTINCT p)`

δ. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN e`

**Σωστή απάντηση: α**

---

24. Ποιο ερώτημα επιστρέφει projects που ανήκουν σε περισσότερες από μία εταιρείες αλλά έχουν μόνο έναν υπάλληλο να συμμετέχει;

α.

```cypher
MATCH (p:Project)<-[:ΑΝΗΚΕΙ_ΣΕ]-(e:Εταιρεία),
      (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COUNT(DISTINCT e) AS ec, COUNT(DISTINCT u) AS uc
WHERE ec > 1 AND uc = 1
RETURN p
```

β. `MATCH (p:Project)<-[:ΑΝΗΚΕΙ_ΣΕ]-(e:Εταιρεία) RETURN p`

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

δ. `MATCH (p:Project)<-[:ΑΝΗΚΕΙ_ΣΕ]-(e:Εταιρεία) WHERE SIZE(e) > 1 RETURN p`

**Σωστή απάντηση: α**

---

25. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται μόνο με υπαλλήλους της ίδιας εταιρείας;

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία),
      (u1)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος),
      (u2)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία)
WITH u1, COLLECT(DISTINCT e2) AS εταιρείες
WHERE SIZE(εταιρείες) = 1 AND εταιρείες[0] = e
RETURN DISTINCT u1
```

β. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) RETURN u1`

γ. `MATCH (u1:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u1`

δ. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) WHERE u1 = u2 RETURN u1`

**Σωστή απάντηση: α**

---

26. Ποιο ερώτημα βρίσκει projects στα οποία συμμετέχουν υπάλληλοι από όλες τις εταιρείες του γράφου;

α.

```cypher
MATCH (e:Εταιρεία) WITH COLLECT(DISTINCT e) AS όλες_οι_εταιρείες
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WITH p, COLLECT(DISTINCT e) AS εταιρείες_στο_project, όλες_οι_εταιρείες
WHERE εταιρείες_στο_project = όλες_οι_εταιρείες
RETURN DISTINCT p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

γ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

**Σωστή απάντηση: α**

---

27. Ποιο ερώτημα επιστρέφει υπαλλήλους που ανήκουν σε εταιρείες χωρίς κανένα project;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WHERE NOT EXISTS { MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e) }
RETURN u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (u:Υπάλληλος) RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

**Σωστή απάντηση: α**

---

28. Ποιο ερώτημα επιστρέφει τα projects στα οποία όλοι οι συμμετέχοντες υπάλληλοι έχουν την ίδια ειδικότητα;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COLLECT(DISTINCT u.ειδικότητα) AS ειδικότητες
WHERE SIZE(ειδικότητες) = 1
RETURN DISTINCT p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (u:Υπάλληλος) RETURN DISTINCT u.ειδικότητα`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

**Σωστή απάντηση: α**

---

29. Ποιο ερώτημα επιστρέφει projects όπου δεν συνεργάζονται καθόλου οι υπάλληλοι που συμμετέχουν;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u1:Υπάλληλος),
      (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u2:Υπάλληλος)
WHERE u1 <> u2 AND NOT (u1)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]-(u2)
WITH p, COUNT(*) AS c
RETURN DISTINCT p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

γ. `MATCH (p:Project)<-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]-(:Υπάλληλος) RETURN p`

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: α**

---

30. Ποιο ερώτημα επιστρέφει υπαλλήλους που εργάζονται σε εταιρείες οι οποίες ανήκουν σε τουλάχιστον δύο projects;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία),
      (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e)
WITH u, e, COUNT(DISTINCT p) AS cnt
WHERE cnt >= 2
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

**Σωστή απάντηση: α**

---

31. Ποιο ερώτημα βρίσκει εταιρείες που έχουν υπαλλήλους χωρίς καμία συμμετοχή σε project;

α.

```cypher
MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος)
WHERE NOT (u)-[:ΣΥΜΜΕΤΕΧΕΙ]->(:Project)
RETURN DISTINCT e
```

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(:Project) RETURN e`

γ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN DISTINCT e`

δ.

```cypher
MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
RETURN DISTINCT e
```

**Σωστή απάντηση: α**

---

32. Ποιο ερώτημα βρίσκει τον αριθμό των υπαλλήλων ανά ειδικότητα;

α. `MATCH (u:Υπάλληλος) RETURN u.ειδικότητα, COUNT(*) AS cnt`

β. `MATCH (u:Υπάλληλος) RETURN COUNT(u.ειδικότητα)`

γ. `MATCH (u:Υπάλληλος) RETURN DISTINCT u.ειδικότητα`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

33. Ποιο ερώτημα επιστρέφει υπαλλήλους που συμμετέχουν σε projects μαζί με τουλάχιστον δύο άλλους υπαλλήλους;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(other:Υπάλληλος)
WHERE u <> other
WITH u, p, COUNT(DISTINCT other) AS others
WHERE others >= 2
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN COUNT(u)`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(:Υπάλληλος) RETURN u`

**Σωστή απάντηση: α**

---

34. Ποιο ερώτημα επιστρέφει τα projects στα οποία συμμετέχουν υπάλληλοι μόνο από μία εταιρεία;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WITH p, COLLECT(DISTINCT e) AS εταιρείες
WHERE SIZE(εταιρείες) = 1
RETURN DISTINCT p
```

β. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: α**

---

35. Ποιο ερώτημα επιστρέφει εταιρείες που έχουν projects χωρίς συμμετοχή υπαλλήλων;

α.

```cypher
MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project)
WHERE NOT (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος)
RETURN DISTINCT e
```

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(:Project) RETURN e`

γ. `MATCH (p:Project) RETURN DISTINCT p`

δ.

```cypher
MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
RETURN DISTINCT e
```

**Σωστή απάντηση: α**

---

36. Ποιο ερώτημα επιστρέφει υπαλλήλους που εργάζονται σε εταιρείες που δεν έχουν συνεργασία με άλλες;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WHERE NOT EXISTS {
  MATCH (e)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]-(u2:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία)
  WHERE e <> e2
}
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) RETURN u`

δ. `MATCH (e:Εταιρεία) RETURN e`

**Σωστή απάντηση: α**

---

37. Ποιο ερώτημα επιστρέφει υπαλλήλους που εργάζονται σε εταιρείες με projects αλλά δεν συμμετέχουν σε κανένα;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WHERE EXISTS { MATCH (:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e) }
  AND NOT (u)-[:ΣΥΜΜΕΤΕΧΕΙ]->(:Project)
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

δ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

**Σωστή απάντηση: α**

---

38. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται με υπαλλήλους διαφορετικής ειδικότητας;

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος)
WHERE u1.ειδικότητα <> u2.ειδικότητα
RETURN DISTINCT u1
```

β. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) RETURN u1`

γ. `MATCH (u:Υπάλληλος) RETURN u`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

**Σωστή απάντηση: α**

---

39. Ποιο ερώτημα επιστρέφει εταιρείες με projects στα οποία όλοι οι συμμετέχοντες υπάλληλοι ανήκουν στην ίδια εταιρεία;

α.

```cypher
MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία),
      (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία)
WITH p, e, COLLECT(DISTINCT e2) AS εταιρείες
WHERE SIZE(εταιρείες) = 1 AND εταιρείες[0] = e
RETURN DISTINCT e
```

β. `MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project) RETURN e`

γ. `MATCH (e:Εταιρεία) RETURN e`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

**Σωστή απάντηση: α**

---

40. Ποιο ερώτημα επιστρέφει projects με περισσότερους από 5 υπαλλήλους διαφορετικής ειδικότητας;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COUNT(DISTINCT u.ειδικότητα) AS ειδικότητες
WHERE ειδικότητες > 5
RETURN p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

γ. `MATCH (u:Υπάλληλος) RETURN u.ειδικότητα`

δ. `MATCH (p:Project) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---


41. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται με τουλάχιστον τρεις διαφορετικούς υπαλλήλους;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(other:Υπάλληλος)
WITH u, COUNT(DISTINCT other) AS cnt
WHERE cnt >= 3
RETURN u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(other:Υπάλληλος) RETURN u`

γ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

δ.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(other:Υπάλληλος)
RETURN DISTINCT u, other
```

**Σωστή απάντηση: α**

---

42. Ποιο ερώτημα επιστρέφει τα projects στα οποία δεν συμμετέχει κανείς υπάλληλος;

α.

```cypher
MATCH (p:Project)
WHERE NOT (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος)
RETURN p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

γ. `MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία) RETURN p`

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: α**

---

43. Ποιο ερώτημα επιστρέφει εταιρείες με projects στα οποία συμμετέχουν υπάλληλοι από διαφορετικές εταιρείες;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία),
      (p)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία)
WITH p, e, COLLECT(DISTINCT e2) AS εταιρείες
WHERE SIZE(εταιρείες) > 1
RETURN DISTINCT e
```

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN e`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(:Υπάλληλος) RETURN e`

δ. `MATCH (e:Εταιρεία) RETURN e`

**Σωστή απάντηση: α**

---

44. Ποιο ερώτημα επιστρέφει projects με ακριβώς δύο υπαλλήλους να συμμετέχουν;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COUNT(DISTINCT u) AS cnt
WHERE cnt = 2
RETURN p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

δ.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
RETURN p, COUNT(u)
```

**Σωστή απάντηση: α**

---

45. Ποιο ερώτημα βρίσκει υπαλλήλους που δεν συνεργάζονται με κανέναν άλλο;

α.

```cypher
MATCH (u:Υπάλληλος)
WHERE NOT (u)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(:Υπάλληλος)
RETURN u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(other:Υπάλληλος) RETURN u`

δ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

46. Ποιο ερώτημα επιστρέφει εταιρείες που έχουν μόνο ένα project;

α.

```cypher
MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project)
WITH e, COUNT(p) AS cnt
WHERE cnt = 1
RETURN e
```

β. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(:Project) RETURN e`

γ. `MATCH (e:Εταιρεία)<-[:ΑΝΗΚΕΙ_ΣΕ]-(p:Project) RETURN COUNT(p)`

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: α**

---

47. Ποιο ερώτημα βρίσκει projects στα οποία όλοι οι υπάλληλοι έχουν την ίδια ειδικότητα;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COLLECT(DISTINCT u.ειδικότητα) AS ειδικότητες
WHERE SIZE(ειδικότητες) = 1
RETURN p
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

γ. `MATCH (p:Project) RETURN COUNT(*)`

δ. `MATCH (u:Υπάλληλος) RETURN u.ειδικότητα`

**Σωστή απάντηση: α**

---

48. Ποιο ερώτημα επιστρέφει υπαλλήλους που συμμετέχουν σε projects που ανήκουν σε εταιρείες διαφορετικές από αυτή που εργάζονται;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e1:Εταιρεία),
      (u)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e2:Εταιρεία)
WHERE e1 <> e2
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN p`

δ. `MATCH (e:Εταιρεία) RETURN e`

**Σωστή απάντηση: α**

---

49. Ποιο ερώτημα επιστρέφει πόσα projects έχει κάθε εταιρεία;

α.

```cypher
MATCH (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e:Εταιρεία)
RETURN e.name, COUNT(p) AS total
```

β. `MATCH (e:Εταιρεία) RETURN COUNT(*)`

γ. `MATCH (e:Εταιρεία)-[:ΑΝΗΚΕΙ_ΣΕ]->(p:Project) RETURN p`

δ. `MATCH (p:Project) RETURN COUNT(p)`

**Σωστή απάντηση: α**

---

50. Ποιο ερώτημα επιστρέφει υπαλλήλους που έχουν συμμετάσχει σε περισσότερα από ένα project;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
WITH u, COUNT(p) AS cnt
WHERE cnt > 1
RETURN DISTINCT u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

γ. `MATCH (p:Project) RETURN p`

δ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

51. Ποιο ερώτημα βρίσκει τα projects με υπαλλήλους που συνεργάζονται μεταξύ τους;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]-(u2:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p)
RETURN DISTINCT p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(:Υπάλληλος) RETURN u`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος) RETURN COUNT(*)`

---

52. Ποιο ερώτημα επιστρέφει υπαλλήλους που εργάζονται στην ίδια εταιρεία με αυτούς που συμμετέχουν σε κάποιο project;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u1:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u2:Υπάλληλος)
RETURN DISTINCT u2
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(:Εταιρεία) RETURN u`

γ. `MATCH (p:Project) RETURN COUNT(*)`

δ. `MATCH (e:Εταιρεία) RETURN e`

---

53. Ποιο ερώτημα υπολογίζει τον μέσο όρο αριθμού projects ανά υπάλληλο;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
WITH u, COUNT(p) AS project_count
RETURN AVG(project_count)
```

β. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

γ. `MATCH (p:Project) RETURN AVG(p)`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN COUNT(*)`

---

54. Ποιο ερώτημα βρίσκει ζεύγη υπαλλήλων που έχουν κοινό project;

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u2:Υπάλληλος)
WHERE u1 <> u2
RETURN DISTINCT u1, u2
```

β. `MATCH (u:Υπάλληλος) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(:Υπάλληλος) RETURN u`

δ. `MATCH (p:Project) RETURN COUNT(*)`

---

55. Ποιο ερώτημα βρίσκει τον υπάλληλο με τα περισσότερα projects;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
WITH u, COUNT(p) AS cnt
RETURN u
ORDER BY cnt DESC
LIMIT 1
```

β. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

γ. `MATCH (p:Project) RETURN COUNT(p)`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

---

56. Ποιο ερώτημα επιστρέφει projects που έχουν υπαλλήλους από περισσότερες από 2 ειδικότητες;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COLLECT(DISTINCT u.ειδικότητα) AS ειδικότητες
WHERE SIZE(ειδικότητες) > 2
RETURN p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (u:Υπάλληλος) RETURN u.ειδικότητα`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN COUNT(u)`

---

57. Ποιο ερώτημα επιστρέφει εταιρείες χωρίς υπαλλήλους;

α.

```cypher
MATCH (e:Εταιρεία)
WHERE NOT (e)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(:Υπάλληλος)
RETURN e
```

β. `MATCH (e:Εταιρεία)-[:ΕΧΕΙ]->(:Υπάλληλος) RETURN e`

γ. `MATCH (e:Εταιρεία) RETURN COUNT(*)`

δ. `MATCH (:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN e`

---

58. Ποιο ερώτημα βρίσκει projects που μοιράζονται μεταξύ δύο ή περισσότερων εταιρειών (μέσω υπαλλήλων);

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
WITH p, COLLECT(DISTINCT e) AS εταιρείες
WHERE SIZE(εταιρείες) >= 2
RETURN DISTINCT p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (e:Εταιρεία) RETURN COUNT(*)`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

---

59. Ποιο ερώτημα επιστρέφει projects που συμμετέχουν όλοι οι υπάλληλοι μιας εταιρείας;

α.

```cypher
MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος),
      (p:Project)-[:ΑΝΗΚΕΙ_ΣΕ]->(e)
WITH p, e, COLLECT(DISTINCT u) AS εταιρικοί
MATCH (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(p_u:Υπάλληλος)
WITH p, εταιρικοί, COLLECT(DISTINCT p_u) AS συμμετοχικοί
WHERE εταιρικοί = συμμετοχικοί
RETURN p
```

β. `MATCH (p:Project) RETURN p`

γ. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(:Υπάλληλος) RETURN e`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος) RETURN p`

---

60. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται με όλους όσους συμμετέχουν σε project τους;

α.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(colleague:Υπάλληλος)
WHERE u <> colleague
WITH u, COLLECT(DISTINCT colleague) AS colleagues
MATCH (u)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(c:Υπάλληλος)
WITH u, colleagues, COLLECT(DISTINCT c) AS συνεργάτες
WHERE ALL(x IN colleagues WHERE x IN συνεργάτες)
RETURN u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(:Υπάλληλος) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

δ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

---

61. Ποιο ερώτημα βρίσκει υπαλλήλους που ΔΕΝ συμμετέχουν σε κανένα project;

α.

```cypher
MATCH (u:Υπάλληλος)
WHERE NOT (u)-[:ΣΥΜΜΕΤΕΧΕΙ]->(:Project)
RETURN u
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

γ. `MATCH (:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN u`

δ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

62. Ποιο ερώτημα βρίσκει το πλήθος των projects ανά εταιρεία;

α. `MATCH (e:Εταιρεία) RETURN COUNT(*)`

β. `MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN COUNT(DISTINCT p)`

γ.

```cypher
MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
RETURN e.name, COUNT(DISTINCT p) AS projects
```

δ. `MATCH (p:Project) RETURN p`

**Σωστή απάντηση: γ**

---

63. Ποιο ερώτημα βρίσκει υπαλλήλους που συνεργάζονται αμφίδρομα (και οι δύο έχουν σχέση ΣΥΝΕΡΓΑΖΕΤΑΙ\_ΜΕ);

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος),
      (u2)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u1)
RETURN DISTINCT u1, u2
```

β. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(x) RETURN u`

γ. `MATCH (u1:Υπάλληλος)<-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]-(u2:Υπάλληλος) RETURN u1`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u`

**Σωστή απάντηση: α**

---

64. Ποιο ερώτημα επιστρέφει υπαλλήλους που συνεργάζονται με υπάλληλο άλλης εταιρείας;

α.

```cypher
MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος)
WHERE u1 <> u2
AND NOT (u1)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->()<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u2)
RETURN DISTINCT u1
```

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(x) RETURN u`

δ. `MATCH (u1:Υπάλληλος)-[:ΣΥΝΕΡΓΑΖΕΤΑΙ_ΜΕ]->(u2:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

65. Ποιο ερώτημα υπολογίζει πόσοι υπάλληλοι εργάζονται ανά εταιρεία;

α. `MATCH (e:Εταιρεία) RETURN e`

β. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN COUNT(e)`

γ.

```cypher
MATCH (e:Εταιρεία)<-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]-(u:Υπάλληλος)
RETURN e.name, COUNT(u) AS υπάλληλοι
```

δ. `MATCH (u:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: γ**

---

66. Ποιο ερώτημα βρίσκει projects όπου συμμετέχουν τουλάχιστον 3 υπάλληλοι;

α. `MATCH (p:Project) RETURN p`

β.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COUNT(u) AS συμμετοχές
WHERE συμμετοχές >= 3
RETURN p
```

γ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος) RETURN COUNT(*)`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

**Σωστή απάντηση: β**

---

67. Ποιο ερώτημα βρίσκει το project με τους περισσότερους συμμετέχοντες υπαλλήλους;

α.

```cypher
MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(u:Υπάλληλος)
WITH p, COUNT(u) AS cnt
RETURN p
ORDER BY cnt DESC
LIMIT 1
```

β. `MATCH (p:Project) RETURN COUNT(*)`

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN COUNT(p)`

δ. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος) RETURN COUNT(*)`

**Σωστή απάντηση: α**

---

68. Ποιο ερώτημα επιστρέφει τα ονόματα projects και υπαλλήλων που συμμετέχουν σε αυτά;

α. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

β. `MATCH (p:Project) RETURN p.name`

γ.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project)
RETURN p.name, u.name
```

δ. `MATCH (u:Υπάλληλος) RETURN u`

**Σωστή απάντηση: γ**

---

69. Ποιο ερώτημα βρίσκει projects χωρίς συμμετοχές υπαλλήλων;

α.

```cypher
MATCH (p:Project)
WHERE NOT (p)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος)
RETURN p
```

β. `MATCH (p:Project)<-[:ΣΥΜΜΕΤΕΧΕΙ]-(:Υπάλληλος) RETURN p`

γ. `MATCH (p:Project) RETURN p.name`

δ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN p`

**Σωστή απάντηση: α**

---

70. Ποιο ερώτημα επιστρέφει για κάθε υπάλληλο τα projects και τις εταιρείες που σχετίζεται;

α. `MATCH (u:Υπάλληλος) RETURN u`

β.

```cypher
MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project),
      (u)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία)
RETURN u.name, COLLECT(DISTINCT p.name) AS projects, e.name AS εταιρεία
```

γ. `MATCH (u:Υπάλληλος)-[:ΣΥΜΜΕΤΕΧΕΙ]->(p:Project) RETURN u, p`

δ. `MATCH (u:Υπάλληλος)-[:ΕΡΓΑΖΕΤΑΙ_ΣΕ]->(e:Εταιρεία) RETURN u`

**Σωστή απάντηση: β**




