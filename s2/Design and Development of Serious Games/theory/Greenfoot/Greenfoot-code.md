# Greenfoot-code

***

## **Καθορισμός διάστασης κόσμου**
```java
import greenfoot.*; // World, Actor, GreenfootImage, Greenfoot, MouseInfo

public class CrabWorld extends World
{
    public CrabWorld()
    {
        super(600, 400, 1);
    }
}
```

## **Στροφή κατά 'angle' μοίρες**
```java
public void turn(int angle)
{
    setRotation(getRotation() + angle);
}
```

## **Μετακίνηση προς την τρέχουσα κατεύθυνση**
```java
public void move()
{
    double angle = Math.toRadians(getRotation());
    int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
    int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
    setLocation(x, y);
}
```

## **Έλεγχος αν είναι στα όρια του κόσμου**
```java
public boolean atWorldEdge()
{
    if(getX() < 20 || getX() > getWorld().getWidth() - 20)
        return true;
    if(getY() < 20 || getY() > getWorld().getHeight() - 20)
        return true;
    return false;
}
```

## **Έλεγχος ύπαρξης άλλου τύπου αντικειμένου στην τρέχουσα θέση**
```java
public boolean canSee(Class clss)
{
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    return actor != null;
}
```

## **Φάε αντικείμενο τύπου ‘clss’**
```java
public void eat(Class clss)
{
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    if(actor != null) {
        getWorld().removeObject(actor);
    }
}
```

---

## **Στρίβει αριστερά και δεξιά ελεγχόμενο από τον χρήστη**
```java
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

## **Το καβούρι ψάχνει για σκουλήκι και το τρώει**
```java
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

## **Βασική συμπεριφορά καβουριού (μέθοδος act)**
```java
public void act()
{
    checkKeypress();
    move();
    lookForWorm();
}
```

## **Βελτίωση κίνησης κάβουρα – αλλαγή εικόνας**
```java
public class Crab extends Animal
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    
    public Crab()
    {
        image1 = new GreenfootImage("crab.png");
        image2 = new GreenfootImage("crab2.png");
        setImage(image1);
        wormsEaten = 0;
    }
}

    public void act()
    {
        // ...
        switchImage();
    }
    
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
}
```

## **Ο αστακός στρίβει όταν είναι στα όρια**
```java
public void turnAtEdge()
{
    if ( atWorldEdge() )
    {
        turn(17);
    }
}
```

## **Ο αστακόs στρίβει (ή όχι) τυχαία**
```java
public void randomTurn()
{
    if (Greenfoot.getRandomNumber(100) > 90) {
        turn(Greenfoot.getRandomNumber(90)-45);
    }
}
```

## **Ο αστακός ψάχνει για καβούρι και το τρώει**
```java
public void lookForCrab()
{
    if (canSee(Crab.class))
    {
        eat(Crab.class);
        Greenfoot.playSound("au.wav");
        Greenfoot.stop();
    }
}
```

## **Βασική συμπεριφορά αστακού**
```java
public void act()
{
    turnAtEdge();
    randomTurn();
    move();
    lookForCrab();
}
```

---

## **Μετακίνηση πρωταγωνιστή (καβουριού) μεταξύ επιπέδων**
```java
public class CrabWorld2 extends World
{
    public CrabWorld2(Crab crab)
    {
        super(600, 600, 1);
        prepare(crab);
    }
    
    private void prepare(Crab crab)
    {
        addObject(crab, 308, 303);
        // ...
    }
}
```

## **Παρακολούθηση και αλλαγή επιπέδων**
```java
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



    private void checkNextLevel()
    {
        if (level == 1 && wormsEaten == 8)
        {
            level = 2;
            wormsEaten = 0;
            getWorld().removeObject(this);
            Greenfoot.playSound("fanfare.wav");
            Greenfoot.setWorld(new CrabWorld2(this));
        }
        else if (level == 2 && wormsEaten == 8)
        {
            Greenfoot.playSound("fanfare.wav");
            winMessage();
            Greenfoot.stop();
        }
    }

    private void winMessage()
    {
        GreenfootImage bg = getWorld().getBackground();
        Font font = bg.getFont();
        bg.setColor(Color.RED);
        bg.setFont(font.deriveFont(48));
        bg.drawString("WINNER", 200, 250);
    }
```

---

## **Αλλαγή εικόνας (frame) χαρακτήρα**
```java
public class Crab extends Animal
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    // ...
    public Crab()
    {
        image1 = new GreenfootImage("crab.png");
        image2 = new GreenfootImage("crab2.png");
        setImage(image1);
        wormsEaten = 0;
    }
    
    public void act()
    {
        // ...
        switchImage();
    }
    
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
}
```

## **Χρήση περισσότερων frames για καλύτερη κίνηση**
```java
public class Crab extends Animal
{
    private static final int NUM_OF_IMAGES = 2;
    private GreenfootImage[] images;
    private int currentImage;
    // ...
    public Crab()
    {
        images = new GreenfootImage[NUM_OF_IMAGES];
        for(int i = 0; i < images.length; i++)
            images[i] = new GreenfootImage("crab" + i + ".png");
        currentImage = 0;
        setImage(images[currentImage]);
        wormsEaten = 0;
    }
    
    public void act()
    {
        // ...
        switchImage();
    }
}

    public void switchImage()
    {
        currentImage = (currentImage + 1) % NUM_OF_IMAGES;
        setImage(images[currentImage]);
    }
}
```

## **Χρήση της κλάσης GifImage για αρχεία GIF**
```java
public class Bear extends Actor
{
    private GifImage gif;
    // ...
    public Bear()
    {
        gif = new GifImage("bear.gif");
        // ...
    }
    
    public void act()
    {
        // ...
        setImage(gif.getCurrentImage());
    }
}
```

## **Χρήση διαφορετικών frames ανά κατεύθυνση**
```java
public class Hero extends Actor
{
    private static final int NUM_OF_IMAGES = 6;
    private static final int IMAGES_PER_DIRECTION = 3;
    private GreenfootImage[] images;
    private int currentImage;
    // ...
    public Hero()
    {
        images = new GreenfootImage[NUM_OF_IMAGES];
        for(int i = 0; i < images.length; i++)
            images[i] = new GreenfootImage("hero" + i + ".png");
        currentImage = 0;
        setImage(images[currentImage]);
        // ...
    }
    
    public void act()
    {
        // ...
        checkKeys();
    }
    
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

    private void checkKeys()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            animate(0, 2);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            animate(3, 5);
        }
}
```

---

## **Σελίδα 9 – Ρύθμιση του κόσμου με ήλιο και πλανήτες**
```java
// Sun and Planet
removeAllObjects();
addObject(new Body(50, 240.0, new Vector(270, 0.03), new Color(255, 216, 0)), 460, 270);
addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 695, 260);

// Sun and Two Planets
public void sunAndTwoPlanets() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 310);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 695, 300);
    addObject(new Body(44, 4.6, new Vector(270, 1.8), new Color(48, 160, 86)), 180, 290);
}

// Sun, Planet and Moon
public void sunPlanetMoon() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 270);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 720, 260);
    addObject(new Body(5, 0.8, new Vector(90, 3.25), new Color(240, 220, 96)), 748, 260);
}

// addObject(new Body(μέγεθος, μάζα, new Vector(κατεύθυνση, ταχύτητα), new Color(r, g, b)), x, y);

// Αφαίρεση όλων των αντικειμένων
private void removeAllObjects() {
    removeObjects(getObjects(Actor.class));
}
```

## **Σελίδα 10 – sunPlanetMoon (επανάληψη)**
```java
public void sunPlanetMoon() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 270);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 720, 260);
    addObject(new Body(5, 0.8, new Vector(90, 3.25), new Color(240, 220, 96)), 745, 260);
}
```

## **Σελίδα 20 – Ορισμός της κλάσης Body**
```java
public class Body extends SmoothMover {
    private static final double GRAVITY = 5.6;
    private static final Color defaultColor = new Color(255, 216, 0);
    private double mass;

    // Default constructor
    public Body() {
        this(20, 300, new Vector(0, 1.0), defaultColor);
    }

    // Constructor με ορίσματα
    public Body(int size, double mass, Vector movement, Color color) {
        this.mass = mass;
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(color);
        image.fillOval(0, 0, size - 1, size - 1);
        setImage(image);
        addForce(movement);
    }
}
    public double getMass() {
        return mass;
    }

    public void act() {
        // εδώ μπαίνει η κίνηση και η φυσική
    }
}
```

## **Σελίδα 29 – Υπολογισμός βαρυτικής δύναμης**
```java
private void applyGravity(Body other) {
    double dx = other.getExactX() - this.getExactX();
    double dy = other.getExactY() - this.getExactY();
    Vector force = new Vector(dx, dy);

    double distance = Math.sqrt(dx * dx + dy * dy);
    double strength = GRAVITY * this.mass * other.mass / (distance * distance);
    double acceleration = strength / this.mass;

    force.setLength(acceleration);
    addForce(force);
}
```

---

## **Κατασκευαστής κλάσης Space: Σχεδίαση σκηνικού με κώδικα**
```java
// Στο constructor της Space:
GreenfootImage bg = getBackground();
bg.setColor(Color.BLACK);
bg.fill();
Rocket rocket = new Rocket();
addObject(rocket, getWidth()/2, getHeight()/2);
addAsteroids(5);
Counter scoreboard = new Counter("Score: ");
addObject(scoreboard, 50, 20);
Explosion.initializeImages();
ProtonWave.initializeImages();
```

## **Μέθοδος addAsteroids: Προσθήκη αστεροειδών**
```java
private void addAsteroids(int count) {
    for (int i = 0; i < count; i++) {
        Asteroid a = new Asteroid();
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(a, x, y);
    }
}
```

## **Κλάση Rocket: Έλεγχος κίνησης & ώθηση**
```java
public void act() {
    if (Greenfoot.isKeyDown("left")) turn(-5);
    if (Greenfoot.isKeyDown("right")) turn(5);
    if (Greenfoot.isKeyDown("up")) {
        Vector thrust = new Vector(getRotation(), 0.3);
        addForce(thrust);
    }
    move();
    checkCollision();
}
```

## **Rocket: Έλεγχος σύγκρουσης με αστεροειδείς**
```java
private void checkCollision() {
    Asteroid a = (Asteroid) getOneObjectAtOffset(0, 0, Asteroid.class);
    if (a != null) {
        getWorld().removeObject(this);
        getWorld().addObject(new Explosion(), getX(), getY());
        ((Space)getWorld()).gameOver();
    }
}
```

## **Κλάση ProtonWave: Σταδιακή εμφάνιση κύματος**
```java
public class ProtonWave extends Actor {
    private static final int NUM_IMAGES = 10;
    private GreenfootImage[] images;
    private int imageCount;
    public ProtonWave() {
        images = new GreenfootImage[NUM_IMAGES];
        for (int i=0; i<NUM_IMAGES; i++) {
            images[i] = new GreenfootImage("wave.png");
            images[i].scale(20 + i*10, 20 + i*10);
        }
        imageCount = 0;
    }
}

    public void act() {
        grow();
    }

    private void grow() {
        if (imageCount < images.length) {
            setImage(images[imageCount]);
            imageCount++;
        } else {
            getWorld().removeObject(this);
        }
    }
}
```

## **Collision Detection στο ProtonWave**
```java
private void impact() {
    int range = getImage().getWidth()/2;
    List<Asteroid> list = getObjectsInRange(range, Asteroid.class);
    for (Asteroid a: list) {
        a.damage(10);
        if (a.getHealth() <= 0) {
            a.breakUp();
        }
    }
}
```

## **Κλάση Asteroid: breakUp / καταστροφή**
```java
public void breakUp() {
    if (getSize() <= 16) {
        getWorld().removeObject(this);
    } else {
        int newSize = getSize() / 2;
        Asteroid a1 = new Asteroid(newSize);
        Asteroid a2 = new Asteroid(newSize);
        a1.setVector(new Vector(getRotation() + 30, getSpeed() * 1.2));
        a2.setVector(new Vector(getRotation() - 30, getSpeed() * 1.2));
        getWorld().addObject(a1, getX(), getY());
        getWorld().addObject(a2, getX(), getY());
        getWorld().removeObject(this);
    }
}
```

## **Τερματισμός παιχνιδιού & ScoreBoard**
```java
public class Space extends World {
    public void gameOver() {
        showText("Game Over", getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
}

// Στο Counter.update():
public void addScore(int points) {
    value += points;
    setValue("Score: " + value);
}
```

---

## **Σχεδίαση του χάρτη (tile-based world)**
```java
public class TileWorld extends World {
    public static final int TWIDTH = 25;
    public static final int THEIGHT = 25;
    public static final String[] WORLD = {
        "BBBBBBBBBB",
        "BWUGWUUWBW",
        "BWWWWWWWBW",
        "BBBBBBBBBB"
    };

    public TileWorld() {
        super(WORLD[0].length() * TWIDTH, WORLD.length * THEIGHT, 1);
        for (int row = 0; row < WORLD.length; row++) {
            for (int col = 0; col < WORLD[row].length(); col++) {
                char tileType = WORLD[row].charAt(col);
                Actor block;
                switch (tileType) {
                    case 'B': block = new BlackBlock(); break;
                    case 'U': block = new BlueBlock(); break;
                    case 'W': block = new WhiteBlock(); break;
                    case 'G': block = new GoldBlock(); break;
                    default: continue;
                }
                addObject(block, col * TWIDTH + TWIDTH/2, row * THEIGHT + THEIGHT/2);
            }
        }
    }
}
```

## **Αφηρημένη κλάση ScrollingEnemy**
```java
public abstract class ScrollingEnemy extends Actor {
    protected int speedX, speedY;

    public void act() {
        sense();
        reaction();
        boundedMove();
    }

    protected abstract void sense();
    protected abstract void reaction();

    private void boundedMove() {
        int newX = getX() + speedX;
        int newY = getY() + speedY;
        setLocation(newX, newY);
    }
}
```

## **Τυχαία κίνηση: Spider**
```java
public class Spider extends ScrollingEnemy {
    protected void sense() {
        // Δεν αισθάνεται το περιβάλλον
    }

    protected void reaction() {
        if (Greenfoot.getRandomNumber(100) < 2) {
            speedX = Greenfoot.getRandomNumber(3) - 1;
            speedY = Greenfoot.getRandomNumber(3) - 1;
        }
    }
}
```

---

## **Ευρετικοί κανόνες: Snake**
```java
public class Snake extends ScrollingEnemy {
    private boolean pathing;
    private Actor player;

    protected void sense() {
        if (Math.abs(getX() - player.getX()) < 100 && Math.abs(getY() - player.getY()) < 100)
            pathing = true;
        else
            pathing = false;
    }

    protected void reaction() {
        if (pathing) {
            chasePlayer();
        } else {
            patrol();
        }
    }

    private void chasePlayer() {
        speedX = (player.getX() > getX()) ? 1 : -1;
        speedY = (player.getY() > getY()) ? 1 : -1;
    }

    private void patrol() {
        // Κίνηση μπρος-πίσω
        speedX = 1;
        speedY = 0;
    }
}
```

## **Έξυπνη εύρεση μονοπατιού: Mouse**
```java
public class Mouse extends ScrollingEnemy {
    private Point target;

    protected void sense() {
        // Προετοιμασία για εύρεση μονοπατιού
    }

    protected void reaction() {
        List<Point> path = TiledWorldPathfinding.findPath(
            TiledWorldPathfinding.pointAt(getX(), getY()),
            target
        );

        if (!path.isEmpty()) {
            Point next = path.get(0);
            setLocation(next.x * TWIDTH + TWIDTH/2, next.y * THEIGHT + THEIGHT/2);
        }
    }
}
```

---

## **Αλγόριθμος Α* (TiledWorldPathfinding)**
```java
public class TiledWorldPathfinding {
    public static List<Tile> findPath(Tile start, Tile goal) {
        PriorityQueue<Tile> open = new PriorityQueue<>(
            Comparator.comparingInt(t -> t.g + t.h)
        );

        Set<Tile> closed = new HashSet<>();
        start.g = 0;
        start.h = heuristic(start, goal);
        open.add(start);

        while (!open.isEmpty()) {
            Tile current = open.poll();
            if (current.equals(goal)) return reconstructPath(current);
            
            closed.add(current);
            for (Tile neighbor : current.getNeighbors()) {
                if (closed.contains(neighbor) || neighbor.isObstacle()) continue;
                
                int tentativeG = current.g + distance(current, neighbor);
                if (tentativeG < neighbor.g || !open.contains(neighbor)) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, goal);
                    open.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
}
```
