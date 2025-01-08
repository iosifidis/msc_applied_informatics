# Σχέδιο Σχεδίασης - Πρότυπο Builder

Το πρότυπο Builder δημιουργεί ένα σύνθετο αντικείμενο χρησιμοποιώντας απλά αντικείμενα και ακολουθώντας μια διαδικασία βήμα προς βήμα. Αυτό το πρότυπο σχεδίασης ανήκει στην κατηγορία των δημιουργικών προτύπων, καθώς προσφέρει έναν από τους καλύτερους τρόπους για να δημιουργηθεί ένα αντικείμενο.

Η κλάση Builder δημιουργεί το τελικό αντικείμενο βήμα προς βήμα. Ο builder είναι ανεξάρτητος από άλλα αντικείμενα.

## Υλοποίηση

Έχουμε εξετάσει μια επιχειρηματική περίπτωση ενός εστιατορίου fast food, όπου ένα τυπικό γεύμα θα μπορούσε να είναι ένα μπέργκερ και ένα αναψυκτικό. Το μπέργκερ θα μπορούσε να είναι είτε Veg Burger είτε Chicken Burger και θα συσκευάζεται σε περιτύλιγμα. Το αναψυκτικό θα μπορούσε να είναι είτε Coca-Cola είτε Pepsi και θα συσκευάζεται σε μπουκάλι.

Θα δημιουργήσουμε μια διεπαφή _Item_ που αντιπροσωπεύει τα είδη φαγητού όπως μπέργκερ και αναψυκτικά, και τις συγκεκριμένες κλάσεις που υλοποιούν τη διεπαφή _Item_, καθώς και μια διεπαφή _Packing_ που αντιπροσωπεύει τη συσκευασία των ειδών φαγητού και τις συγκεκριμένες κλάσεις που υλοποιούν τη διεπαφή _Packing_, καθώς το μπέργκερ θα συσκευάζεται σε περιτύλιγμα και το αναψυκτικό σε μπουκάλι.

Στη συνέχεια, θα δημιουργήσουμε μια κλάση _Meal_ που θα περιλαμβάνει έναν _ArrayList_ από _Item_ και έναν _MealBuilder_ για να δημιουργήσει διάφορους τύπους αντικειμένων _Meal_ συνδυάζοντας τα _Item_. Η κλάση _BuilderPatternDemo_ θα χρησιμοποιήσει τον _MealBuilder_ για να δημιουργήσει ένα _Meal_.

![Builder Pattern](builder_pattern_uml_diagram.jpg)

### Βήμα 1

Δημιουργήστε μια διεπαφή Item που θα αντιπροσωπεύει το είδος του φαγητού και τη συσκευασία.

```java
public interface Item {
    public String name();
    public Packing packing();
    public float price();    
}
```

### Βήμα 2

Δημιουργήστε τις συγκεκριμένες κλάσεις που υλοποιούν τη διεπαφή Packing.

```java
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
```

### Βήμα 3

Δημιουργήστε αφηρημένες κλάσεις που υλοποιούν τη διεπαφή item προσφέροντας προεπιλεγμένες λειτουργίες

```java
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
```

### Βήμα 4

Δημιουργήστε συγκεκριμένες κλάσεις που επεκτείνουν τις κλάσεις `Burger` και `ColdDrink`.

```java
public class VegBurger extends Burger {
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}

public class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}

public class Coke extends ColdDrink {
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}

public class Pepsi extends ColdDrink {
    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
```

### Βήμα 5

Δημιουργήστε μια κλάση `Meal` που θα περιλαμβάνει τα αντικείμενα `Item` που ορίστηκαν παραπάνω.

```java
import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<Item> items = new ArrayList<Item>();    

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;

        for (Item item : items) {
            cost += item.price();
        }        
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : " + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        }        
    }    
}
```

### Βήμα 6

Δημιουργήστε μια κλάση `MealBuilder`, την πραγματική κλάση `builder` που είναι υπεύθυνη για τη δημιουργία αντικειμένων `Meal`.

```java
public class MealBuilder {
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }   

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
```

### Βήμα 7

Η κλάση `BuilderPatternDemo` χρησιμοποιεί το `MealBuilder` για να δείξει το πρότυπο `builder`.

```java
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }
}
```

### Βήμα 8

Επαληθεύστε την έξοδο.

```
Veg Meal
Item : Veg Burger, Packing : Wrapper, Price : 25.0
Item : Coke, Packing : Bottle, Price : 30.0
Total Cost: 55.0

Non-Veg Meal
Item : Chicken Burger, Packing : Wrapper, Price : 50.5
Item : Pepsi, Packing : Bottle, Price : 35.0
Total Cost: 85.5
```

# ΠΗΓΗ:

[Design Patterns - Builder Pattern](https://sceweb.sce.uhcl.edu/helm/WEBPAGES-SoftwareDesignPatterns/myfiles/TableContents/Module-6/builder_pattern_builds_a_complex.html)
