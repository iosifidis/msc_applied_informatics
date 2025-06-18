# **Ερωτήσεις Πολλαπλής Επιλογής - Playground Dialogue**

1.  Σύμφωνα με το "STEP 2 – BACKGROUND", ποια εικόνα χρησιμοποιείται ως φόντο;   
    Α. BG_Space   
    Β. BG_GreenHills   
    Γ. BG_Urban   
    Δ. Playground   

2.  Γιατί είναι σημαντικό να προσαρμόσουμε την εικόνα φόντου και την κάμερα ώστε να έχουμε το επιθυμητό αποτέλεσμα στην περιοχή που "πιάνει" η κάμερα;   
    Α. Για να μειώσουμε το μέγεθος της σκηνής.   
    Β. Γιατί μόνο το ορατό τμήμα έχει σημασία για τον παίκτη.   
    Γ. Για να κάνουμε το παιχνίδι πιο δύσκολο.   
    Δ. Δεν είναι σημαντικό.   

3.  Στο "STEP 3 – ΠΡΟΣΘΗΚΗ ΕΔΑΦΟΥΣ", από πού επιλέγουμε την εικόνα για το έδαφος (Stone);   
    Α. Prefabs folder   
    Β. Images folder   
    Γ. Scripts folder   
    Δ. Scenes folder   

4.  Πώς δημιουργούμε ένα κενό αντικείμενο (Empty GameObject) στην Hierarchy;   
    Α. Δεξί κλικ στο Project -> Create Empty.   
    Β. GameObject menu -> Create Empty.   
    Γ. Assets -> Create -> Empty.   
    Δ. Και τα A και B είναι σωστά. (Βάσει tutorial: Δεξί κλικ στην Hierarchy -> Create Empty)   

5.  Σύμφωνα με το "STEP 3", γιατί μετονομάζουμε το κενό αντικείμενο που δημιουργούμε σε Environment;   
    Α. Είναι υποχρεωτικό όνομα.   
    Β. Για λόγους οργάνωσης, ώστε να περιέχει αντικείμενα που απαρτίζουν το περιβάλλον.   
    Γ. Για να λειτουργεί ως script.   
    Δ. Για να λειτουργεί ως collider.   

6.  Σύμφωνα με το "STEP 4 – ΔΗΜΙΟΥΡΓΙΑ ΧΑΡΑΚΤΗΡΩΝ", από πού επιλέγουμε την εικόνα CharacterBody;   
    Α. Prefabs folder   
    Β. Images folder -> Character folder   
    Γ. Scripts folder -> Character folder   
    Δ. Scenes folder   

7.  Αφού σύρουμε την εικόνα CharacterBody στην Hierarchy, πώς αλλάζουμε το όνομα του αντικειμένου σε Player;   
    Α. Επιλέγοντας το και πληκτρολογώντας.   
    Β. Δεξί κλικ -> Rename.   
    Γ. Χρησιμοποιώντας τον Inspector.   
    Δ. Και τα Α και Β είναι σωστά.   

8.  Για να ορίσουμε το GameObject του παίκτη με την ετικέτα (Tag) "Player", ποια επιλογή χρησιμοποιούμε στον Inspector;   
    Α. Rename   
    Β. Tag (drop-down)   
    Γ. Layer   
    Δ. Add Component   

9.  Σύμφωνα με το "STEP 4", πού ρυθμίζεται το "Order in Layer" για τα επιπλέον μέρη του σώματος του παίκτη (μάτια, μαλλιά κτλ.);   
    Α. Inspector -> Transform   
    Β. Inspector -> Sprite Renderer -> Visibility Options   
    Γ. Inspector -> Collider 2D   
    Δ. Inspector -> Rigidbody 2D   

10. Τι script προστίθεται στο σώμα του παίκτη για να του επιτρέψει την κίνηση;   
    Α. Rotate (script)   
    Β. Push (script)   
    Γ. Move with Arrows (script)   
    Δ. Move (script)   

11. Σύμφωνα με το "STEP 5 – ΑΛΛΗΛΕΠΙΔΡΑΣΗ ΑΝΤΙΚΕΙΜΕΝΩΝ", ποια ιδιότητα ρυθμίζεται στο Move with Arrows script ώστε ο παίκτης να κινείται μόνο οριζόντια;   
    Α. Movement Type = Only Horizontal   
    Β. Direction = Horizontal   
    Γ. Freeze Position Y στο Rigidbody 2D   
    Δ. Gravity = 0 στο Rigidbody 2D   

12. Ποια είναι τα σωστά βήματα για τη ρύθμιση του Rigidbody 2D ώστε ο παίκτης να μην παίφτει λόγω βαρύτητας και να μην γλιστράει;   
    Α. Gravity = 9.8, Friction = 0   
    Β. Gravity = 0, Friction = 3   
    Γ. Gravity = 0, Friction = 0   
    Δ. Gravity = 9.8, Friction = 3   

13. Σύμφωνα με το "STEP 5", πώς ρυθμίζουμε την κάμερα να ακολουθεί τον παίκτη;   
    Α. Add Camera Follow script στο Player και Target στην κάμερα.   
    Β. Add Camera Follow script στην κάμερα και Target στο Player.   
    Γ. Add Camera Follow script και στην κάμερα και στον παίκτη.   
    Δ. Αλλάζουμε απλά τη θέση της κάμερας στην Update method.   

14. Ποια ιδιότητα ρυθμίζεται στο Camera Follow script για να οριστεί η περιοχή που θέλω να "βλέπει" η κάμερα;   
    Α. Target   
    Β. Limits > Use Bounds   
    Γ. Position   
    Δ. Size   

15. Σύμφωνα με το "STEP 6 – CONDITION AREA", τι είναι το Condition Area;   
    Α. Ένα script.   
    Β. Ένα GameObject.   
    Γ. Ένα Prefab.   
    Δ. Ένα Component.   

16. Σε τι Component προστίθεται το Condition Area script;   
    Α. Move with Arrows (script)   
    Β. Camera Follow (script)   
    Γ. Box Collider 2D   
    Δ. Condition Area (το ίδιο το GameObject)   

17. Σύμφωνα με το "STEP 6", τι component προστίθεται στο Condition Area GameObject για να ορίσει το σχήμα της περιοχής του;   
    Α. Rectangle   
    Β. Circle Collider 2D   
    Γ. Box Collider 2D      
    Δ. Capsule Collider 2D   

18. Τι συμβαίνει όταν ο παίκτης βρεθεί στην περιοχή του Condition Area;   
    Α. Ο παίκτης παθαίνει ζημιά.   
    Β. Λαμβάνει χώρα ένα σύνολο από Game Actions.   
    Γ. Το παιχνίδι τελειώνει.   
    Δ. Ο παίκτης συλλέγει ένα αντικείμενο.   

19. Σύμφωνα με το "STEP 6", ποιες ενέργειες (Game Actions) μπορούν να λάβουν χώρα στο Condition Area;   
    Α. Κίνηση παίκτη, επίθεση.   
    Β. Διάλογος, αλλαγή επιπέδου, animation.   
    Γ. Συλλογή νομισμάτων, καταστροφή εχθρών.   
    Δ. Περιστροφή, κλίμακα.   

20. Σύμφωνα με το "STEP 7 – ΔΙΑΛΟΓΟΣ ΜΕΤΑΞΥ ΧΑΡΑΚΤΗΡΩΝ", τι GameObject δημιουργείται και μετονομάζεται σε Dialogue;   
    Α. Ένα κενό αντικείμενο.   
    Β. Ένα Dialogue Balloon.   
    Γ. Ένας χαρακτήρας.   
    Δ. Ένα UI Canvas.   

21. Τι Component προστίθεται στο Dialogue GameObject;   
    Α. Dialogue Balloon   
    Β. Text To Display   
    Γ. Options   
    Δ. Και τα A, B, C   

22. Τι script προστίθεται στο Dialogue GameObject;   
    Α. DialogueManager   
    Β. Dialogue   
    Γ. DialogueBalloon   
    Δ. GameManager   

23. Πού αντιστοιχίζεται (target object) στο script Dialogue το Guard (ποιος θα "μιλήσει");   
    Α. Inspector -> Text To Display   
    Β. Inspector -> Options   
    Γ. Inspector -> Options -> Target Object slot   
    Δ. Inspector -> Target Object   

24. Σύμφωνα με το "STEP 7", τι Disappear Mode ορίζεται ώστε το μήνυμα διαλόγου να φεύγει πατώντας ένα πλήκτρο;   
    Α. Auto Hide   
    Β. Timed   
    Γ. Button Press   
    Δ. Distance Threshold   

25. Τι Key To Press ορίζεται στο Dialogue script ώστε το μήνυμα να φεύγει πατώντας το πλήκτρο Return;   
    Α. Space   
    Β. Enter   
    Γ. Return   
    Δ. Esc   

26. Σύμφωνα με το "STEP 7", ποιο UI element χρησιμοποιείται ως βάση για το σύστημα διεπαφής διαλόγου;   
    Α. Canvas   
    Β. Button   
    Γ. UIInterface (Prefab)   
    Δ. Text   

27. Πού προστίθεται το UIInterface prefab για να λειτουργήσει το σύστημα διεπαφής διαλόγου;   
    Α. Hierarchy   
    Β. Scene   
    Γ. Inspector   
    Δ. Assets   

28. Ποια ενέργεια (Action) προστίθεται στο Condition Area για να ενεργοποιήσει τον διάλογο;   
    Α. Dialogue   
    Β. Animation   
    Γ. Collect   
    Δ. Combat   

29. Σύμφωνα με το "STEP 8 – COLLECTING & CONSUMING OBJECTS", τι component προστίθεται στο Coin (νόμισμα);   
    Α. Rigidbody 2D   
    Β. Box Collider 2D   
    Γ. Collectable (script)   
    Δ. Resource (script)   

30. Τι script προστίθεται στο Guard (εχθρός) για να "καταναλώνει" πόρους (νομίσματα);   
    Α. Collectable (script)   
    Β. Consume Resource (script)   
    Γ. Resource (script)   
    Δ. Modify System (script)   

31. Σύμφωνα με το "STEP 8", τι Action προστίθεται στο Condition Area για να εξαφανίσει τον φύλακα (Guard);   
    Α. Dialogue   
    Β. On-Off (script)   
    Γ. Combat   
    Δ. Collect   

32. Τι script προστίθεται στο Guard για να ελέγχει την ορατότητά του (να εξαφανίζεται);   
    Α. Consume Resource (script)   
    Β. On-Off (script)   
    Γ. Modify System (script)   
    Δ. Resource (script)   

33. Σύμφωνα με το "STEP 8", πού γίνεται "Lock" ο Inspector του Condition Area για να αποφύγουμε να κάνουμε λάθος κλικ και να αλλάξουμε τα στοιχεία;   
    Α. Στην κορυφή του παραθύρου Inspector (κουμπί "Lock").   
    Β. Στην κορυφή της λίστας Actions.   
    Γ. Στο component Collider 2D.   
    Δ. Δεν γίνεται Lock.   

34. Σύμφωνα με το "STEP 8", πού σύρουμε το component On-Off από τον Guard για να το προσθέσουμε στις Game Actions του Condition Area;   
    Α. Στο πεδίο Object To Affect του Consume Resource script.   
    Β. Στο πεδίο Object To Affect του On-Off script.   
    Γ. Στο πεδίο None (Action) της λίστας Game Actions στο Condition Area.   
    Δ. Στο πεδίο Type of Resource.   

35. Σύμφωνα με το "STEP 8", τι ελέγχουμε στο UserInterface script για να ορίσουμε τη συνθήκη τερματισμού του παιχνιδιού βάσει σκορ;   
    Α. Game type = Combat, Score To Win = 5   
    Β. Game type = Racing, Score To Win = 10   
    Γ. Game type = Score, Score To Win = 5   
    Δ. Game type = Rescuing, Score To Win = 3   

36. Αν το Score To Win οριστεί στο 5, πόσοι Collectable εξωγήινοι πρέπει να προστεθούν στην σκηνή;   
    Α. 1   
    Β. 3   
    Γ. 5   
    Δ. 10   

37. Στο Condition Area script, ποιες επιλογές υπάρχουν για το Action field (πεδίο δράσης);   
    Α. Dialogue, Collect, Combat   
    Β. Animation, Dialogue, Collect, Combat   
    Γ. Move, Rotate, Scale   
    Δ. Script, Prefab, Component   

38. Σύμφωνα με το "STEP 8", ποιος τύπος Resource προστίθεται στο Coin;   
    Α. Currency   
    Β. Weapon   
    Γ. Armor   
    Δ. Food   

39. Στο Coin component Resource script, ποια ιδιότητα ρυθμίζεται για να ορίσει τον τύπο του resource (π.χ. Currency)?   
    Α. Amount Needed   
    Β. Points Worth   
    Γ. Type of Resource   
    Δ. Is Collectable   

40. Σύμφωνα με το "STEP 8", ποια ιδιότητα του Consume Resource script του Guard ρυθμίζεται για να ορίσει τον τύπο του resource που καταναλώνει και την ποσότητα;   
    Α. Points Worth και Is Collectable   
    Β. Type of Resource και Amount Needed   
    Γ. Object To Affect και On-Off   
    Δ. Resource Amount και Resource Type   

41. Ποιο Action στο Condition Area script μπορεί να εξαφανίσει ή εμφανίσει ένα αντικείμενο (π.χ. τον φύλακα);   
    Α. Dialogue   
    Β. Collect   
    Γ. Combat   
    Δ. On-Off   

42. Τι component πρέπει να έχει ένα αντικείμενο (π.χ. τον φύλακα) για να μπορεί να γίνει On-Off η ορατότητά του μέσω script;   
    Α. Rigidbody 2D   
    Β. Collider 2D   
    Γ. On-Off script   
    Δ. Sprite Renderer (και το script On-Off το ελέγχει)   
   
43. Στο Dialogue script, πώς ορίζεται το κείμενο που θα εμφανιστεί;   
    Α. GameObject name   
    Β. Component Dialogue Balloon > Text property   
    Γ. Script variable Text To Display   
    Δ. Inspector field Text To Display   

44. Στο Dialogue script, πώς συνδέουμε το object (ποιος θα μιλήσει) που θα είναι ο στόχος του διαλόγου;   
    Α. Μέσω Tag.   
    Β. Μέσω Layer.   
    Γ. Μέσω public variable Target Object.   
    Δ. Μέσω Inspector field Guard. (Ονομάζουμε το πεδίο έτσι, αλλά ο τύπος είναι GameObject ή Transform).   

45. Σύμφωνα με το "STEP 8", πώς ορίζεται πόσοι πόντοι αξίζει ένα συλλέξιμο αντικείμενο (Coin) στο script Resource;   
    Α. Type of Resource   
    Β. Amount Needed   
    Γ. Points Worth   
    Δ. Is Collectable   

46. Τι component προστίθεται στο Alien για να περιστρέφεται αυτόματα;   
    Α. RigidBody 2D      
    Β. Capsule Collider 2D   
    Γ. Auto Rotate (script)   
    Δ. Collectable (script)   

47. Σύμφωνα με το "STEP 5", ποιο component απαιτείται και στα δύο αντικείμενα (Spaceship, Asteroid) για να λειτουργήσει ο έλεγχος σύγκρουσης μέσω Polygon Collider 2D;   
    Α. RigidBody 2D   
    Β. Capsule Collider 2D   
    Γ. Transform   
    Δ. Collider 2D (διαφορετικός τύπος)   

48. Τι Action προστέθηκε στο Condition Area script για την περίπτωση Combat;   
    Α. Dialogue   
    Β. Collect   
    Γ. Combat   
    Δ. On-Off   

49. Ποιο asset χρησιμοποιήθηκε για την αναπαράσταση των Alien;   
    Α. Images -> Creatures -> Alien1   
    Β. Images -> Monsters -> Alien1   
    Γ. Prefabs -> Alien1   
    Δ. Models -> Characters -> Alien1   

50. Ποιο script χρησιμοποιήθηκε για τον έλεγχο του Combat;   
    Α. CombatManager   
    Β. Combat   
    Γ. BattleSystem   
    Δ. EncounterManager   

**Απαντήσεις:**

1.  Α   
2.  Β   
3.  Β   
4.  Β   
5.  Γ   
6.  Β   
7.  Δ   
8.  Β   
9.  Β   
10. Γ   
11. Β   
12. Β   
13. Β   
14. Β   
15. Β   
16. Δ   
17. Γ   
18. Β   
19. Β   
20. Α   
21. Δ   
22. Β   
23. Δ   
24. Γ   
25. Γ   
26. Γ   
27. Α   
28. Γ   
29. Δ   
30. Β   
31. Β   
32. Α   
33. Δ   
34. Γ   
35. Γ   
36. Γ   
37. Α   
38. Δ   
39. Γ   
40. Β   
41. Δ   
42. Δ   
43. Δ   
44. Δ   
45. Γ   
46. Γ   
47. Δ   
48. Γ   
49. Α   
50. Β   
