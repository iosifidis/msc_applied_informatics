# Greenfoot

## Crab - lobster

### Καθορισμός διάστασης κόσμου

```
import greenfoot.*;   // World, Actor, GreenfootImage, Greenfoot, MouseInfo
public class CrabWorld extends World
{
    public CrabWorld()
    {
        //Διάσταση του κόσμου 600x600 κελιά με μέγεθος κελιού 1x1 pixels.
        super(600, 400, 1);
    }
}
```

### Στροφή κατά 'angle' μοίρες

```
/**
 * Στροφή προς τη φορά των δεικτών του ρολογιού κατά 'angle' μοίρες
 */

public void turn(int angle)
{
    setRotation(getRotation() + angle);
}
```

### Μετακίνηση προς την τρέχουσα κατεύθυνση

```
/**
 * Κίνηση προς τα εμπρός.
 */

public void move()
{
    double angle = Math.toRadians( getRotation() );
    int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
    int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
    
    setLocation(x, y);
}
```

### Έλεγχος εντοπισμού των ορίων του κόσμου

```
/**
 * Επιστρέφει true αν βρίσκεται στα όρια του κόσμου.
 */

public boolean atWorldEdge()
{
    if(getX() < 20 || getX() > getWorld().getWidth() - 20)
        return true;
    if(getY() < 20 || getY() > getWorld().getHeight() - 20)
        return true;

    return false;
}
```

### Έλεγχος ύπαρξης άλλου τύπου αντικειμένου στην τρέχουσα θέση

```
/**
 * Επίστρεψε true αν υπάρχει αντικείμενο τύπου 'clss' στην τρέχουσα θέση,
 * false διαφορετικά.
 */

public boolean canSee(Class clss)
{
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    return actor != null;
}
```

### Ένα ζώο τρώει κάποιο άλλο που βρίσκεται στην ίδια θέση

```
/**
 * Προσπάθησε να φας ένα αντικείμενο τύπου'clss'. Αν δεν υπάρχει αντικείμενο
 * αυτού του τύπου στην τρέχουσα θέση η μέθοδος δεν έχει κάποιο αποτέλεσμα
 */

public void eat(Class clss)
{
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    if(actor != null) {
        getWorld().removeObject(actor);
    }
}
```

## Καβούρης

### Στρίβει αριστερά και δεξιά ελεγχόμενο από τον χρήστη

```
/**
 * Έλεγξε αν έχει πατηθεί το αριστερό ή δεξί βελάκι από το πληκτρολόγιο
 * και στρίψε κατάλληλα.
 */

public void checkKeypress()
{
    if (Greenfoot.isKeyDown("left"))
    {
        turn(-4);
    }
    if (Greenfoot.isKeyDown("right"))
    {
        turn(4);
    }
}
```

### Ψάχνει για σκουλήκι και το τρώει

```
/**
 * Έλεγξε αν έχεις πέσει πάνω σε ένα σκουλήκι προκειμένου να το φας.
 * Αν έχεις φάει 8 σκουλήκια κερδίζεις.
 */

public void lookForWorm()
{
    if ( canSee(Worm.class) )
    {
        eat(Worm.class);
        Greenfoot.playSound("slurp.wav");
        
        wormsEaten = wormsEaten + 1;
        if (wormsEaten == 8)
        {
            Greenfoot.playSound("fanfare.wav");
            Greenfoot.stop();
        }
    }
}
```

### Βασική συμπεριφορά καβουριού

```
/**
 * Υλοποίηση βασικής συμπεριφοράς καβουριού. Καλείται κάθε φορά που πατάμε
 * το κουμπί 'Act' ή 'Run' στο περιβάλλον.
 */

public void act()
{
    checkKeypress();
    move();
    lookForWorm();
}
```

### Βελτιώνοντας την κίνηση του καβουριού

```
public class Crab extends Animal
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    …
    public Crab()
    {
        image1 = new GreenfootImage("crab.png");
        image2 = new GreenfootImage("crab2.png");
        setImage(image1);
        wormsEaten = 0;
    }
    
public void act()
{
    …
    switchImage();
}
```

```
public void switchImage()
{
    if (getImage() == image1)
    {
        setImage(image2);
    }
    else
    {
        setImage(image1);
    }
}
```

## Αστακός

### Στρίβει αν είναι στα όρια του κόσμου

```
/**
 * Έλεγξε αν είσαι στα όρια του κόσμου και στρίψε.
 */

public void turnAtEdge()
{
    if ( atWorldEdge() )
    {
        turn(17);
    }
}
```

### Στρίβει (ή όχι) τυχαία αριστερά ή δεξιά

```
/**
 * Αποφασίζεται τυχαία να στρίψει ή όχι (πιθανότητα να στρίψει 9 /100)
 * Αν στρίψει, στρίβει τυχαία λίγο προς τα αριστερά ή δεξιά (-45..45 μοίρες)
 */

public void randomTurn()
{
    if (Greenfoot.getRandomNumber(100) > 90) 
    {
        turn(Greenfoot.getRandomNumber(90)-45);
    }
}
```

### Ελέγχει για καβούρι και το τρώει

```
/**
 * Έλεγξε αν έπεσες πάνω σε ένα καβούρι. Αν ναι απομάκρυνε το και τελείωσε
 * το παιχνίδι
 */

public void lookForCrab()
{
    if ( canSee(Crab.class) )
    {
        eat(Crab.class);
        Greenfoot.playSound("au.wav");
        Greenfoot.stop();
    }
}
```

### Βασική συμπεριφορά αστακού

```
/**
 * Υλοποίηση βασικής συμπεριφοράς αστακού. Καλείται κάθε φορά που πατάμε
 * το κουμπί 'Act' ή 'Run' στο περιβάλλον.
 */

public void act()
{
    turnAtEdge();
    randomTurn();
    move();
    lookForCrab();
}
```

## Προσθήκη επιπέδου

Δημιουργήστε το 2ο επίπεδο με τον ίδιο τρόπο που δημιουργήσατε και τον αρχικό κόσμο:

 Δημιουργήστε μια 2η υποκλάση της World, έστω `CrabWorld2`   
 Το 2ο επίπεδο πρέπει να έχει μεγαλύτερο βαθμό δυσκολίας – για παράδειγμα, περισσότερους εχθρούς ή/και μικρότερη περιοχή!   
 Αφού δημιουργήσετε την νέα κλάση κάντε δεξί κλικ πάνω σε αυτή και επιλέξτε `new CrabWorld2` για να δημιουργήσετε ένα στιγμιότυπο της κλάσης αυτής και να δημιουργήσετε το 2ο επίπεδο   
 Μην ξεχάσετε να αποθηκεύσετε την κατάσταση του κόσμου   
 Αν θέλετε να επεξεργαστείτε ξανά το 1ο επίπεδο, για να το φέρετε στο προσκήνιο χρειάζεται να δημιουργήσετε στιγμιότυπο της αντίστοιχης κλάσης   

Έχοντας πλέον επίπεδα στο παιχνίδι, ο πρωταγωνιστής του θα πρέπει να:

 μεταφέρεται από το ένα επίπεδο στο άλλο    
     ο πρωταγωνιστής (καβούρι) θα πρέπει να περνάει ως όρισμα στον κατασκευαστή του κάθε επιπέδου   
    
 γνωρίζει ανά πάσα στιγμή σε ποιο επίπεδο βρίσκεται   
     προσθήκη μιας σχετικής ιδιότητας/πεδίου στην κλάση του πρωταγωνιστή   

 ελέγχει σε κάθε βήμα αν πρέπει να προχωρήσει στο επόμενο επίπεδο και αν ναι να αλλάζει επίπεδο   
     προσθήκη σχετικής μεθόδου   
    
### Μετακίνηση πρωταγωνιστή μεταξύ επιπέδων

```
public class CrabWorld2 extends World
{
    // Αλλαγή κατασκευαστή ώστε να δέχεται τον πρωταγωνιστή
    public CrabWorld2(Crab crab)
    {
        super(600, 600, 1);
        prepare(crab);
    }

    private void prepare(Crab crab)
    {
        //ο αστακός περνάει πλέον μέσω παραμέτρου
        addObject(crab, 308, 303);
        …
    }
}
```

### Παρακολούθηση/αλλαγή επιπέδων

```
public class Crab extends Animal
{
    …
    private int level;
    public Crab()
    {
        …
        level = 1;
    }
    public void act()
    {
        …
        checkNextLevel();
    }
```

```
private void checkNextLevel()
{
    if (level==1 && wormsEaten==8)
    {
        level = 2;
        wormsEaten = 0;
        getWorld().removeObject(this);
        Greenfoot.playSound("fanfare.wav");
        Greenfoot.setWorld(new CrabWorld2(this));
    }
    else if (level==2 && wormsEaten==8)
    {
        Greenfoot.playSound("fanfare.wav");
        winMessage();
        Greenfoot.stop();
    }
}
```

Μήνυμα νίκης

```
private void winMessage()
{
    GreenfootImage bg = getWorld().getBackground();
    Font font = bg.getFont();
    bg.setColor(Color.RED);
    bg.setFont(font.deriveFont(48));
    bg.drawString("WINNER", 200, 250);
}
```


### Αλλαγή εικόνων σε πίνακα

```
private static final int NUM_OF_IMAGES = 2;

private GreenfootImage[] images;
private int currentImage;
…
public Crab()
{
    images = new GreenfootImage[NUM_OF_IMAGES];
    for(int i=0; i<images.length; i++)
    images[i] = new GreenfootImage("crab"+i+".png");
    currentImage = 0;
    setImage(images[currentImage]);
    wormsEaten = 0;
}
```

```
public void switchImage()
{
    currentImage = (currentImage+1)%NUM_OF_IMAGES;
    setImage(images[currentImage]);
}
```

```
public void act()
{
    …
    switchImage();
}
```

### Εικόνα gif

```
public class Bear extends Actor
{
    private GifImage gif;
    …
    public Bear()
    {
        gif = new GifImage("bear.gif");
        ...
    }
    
    public void act()
    {
        …
        setImage(gif.getCurrentImage());
    }
```

### Εικόνες για δεξιά και για αριστερά σε ένα πίνακα

```
public class Hero extends Actor
{
    private static final int NUM_OF_IMAGES = 6;
    private static final int IMAGES_PER_DIRECTION = 3;
    private GreenfootImage[] images;
    private int currentImage;
    …
    public Hero()
    {
        images = new GreenfootImage[NUM_OF_IMAGES];
        for(int i=0; i<images.length; i++)
        images[i] = new GreenfootImage("hero"+i+".png");
        currentImage = 0;
        setImage(images[currentImage]);
        ...
    }
    public void act()
    {
        …
        checkKeys();
    }
```

```
public void animate(int first, int last)
{
    if (currentImage < first || currentImage >= last)
    {
        currentImage = first;
    }
    else
    {
        currentImage++;
    }
    setImage(images[currentImage]);
}
```

```
private void checkKeys()
{
    if (Greenfoot.isKeyDown("right"))
    {
        animate(0,2);
    }
    if (Greenfoot.isKeyDown("left"))
    {
        animate(3,5);
    }
}
```
