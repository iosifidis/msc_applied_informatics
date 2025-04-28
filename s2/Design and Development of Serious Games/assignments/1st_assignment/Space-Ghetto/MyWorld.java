import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The first level world of the game.
 * Contains all level design elements including platforms, enemies, and the player.
 * Features background music and manages game state when world starts/stops.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class MyWorld extends World
{

    // Background music for this level
    GreenfootSound backgroundMusic= new GreenfootSound("kernkraft.mp3");
    
    /**
     * Constructor for MyWorld - initializes the level layout.
     * Sets world dimensions, background image, and calls prepare() to populate the world.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1); 
        prepare(); // Add all game objects to the world
        setBackground("level1.jpg"); // Set level background image
    }
 
    /**
     * Prepares the world by adding all game objects at their initial positions.
     * Includes:
     * - Player character
     * - Platform blocks (static and moving)
     * - Enemies (monsters)
     * - Level exit door
     * - HUD elements
     */
    public void prepare()
    {
    
        // Add player at starting position (left side of level)
        Player player = new Player();
        addObject(player,20,415);
        
        // Add static blocks and platforms
        addObject(new Block1(),48,493);
        addObject(new Block1(),892,193);
        
        // Add moving platforms with their movement boundaries
        addObject(new MovingPlatform("tile2lv1.png", 100, 300, 1), 155, 402);
        addObject(new MovingPlatform("tile2lv1.png", 200, 506, -1), 435, 222);
            
        // Add various static platforms throughout the level
        addObject(new StaticPlatform(),383,332);
        addObject(new StaticPlatform(),480,332);
        addObject(new StaticPlatform(),440,332);
        addObject(new StaticPlatform(),576,268);
        addObject(new StaticPlatform(),640,268);
        addObject(new StaticPlatform(),202,289);
        addObject(new StaticPlatform(),33,234);
        addObject(new StaticPlatform(),97,234);
        addObject(new StaticPlatform(),343,132);
        addObject(new StaticPlatform(),279,132);
        addObject(new StaticPlatform(),189,63);
        addObject(new StaticPlatform(),130,63);
        addObject(new StaticPlatform(),75,63);
        addObject(new StaticPlatform(),25,63);
        addObject(new StaticPlatform(),846,460);
        addObject(new StaticPlatform(),897,460);

        // Add more block elements
        addObject(new Block1(),560,493);
        addObject(new Block1(),720,388);

        // Add level exit door (top-left corner)
        addObject(new Door(),24,31);

        // Add enemies at various positions
        // Parameters: (dropsKey, pointsValue)
        addObject(new Monster(false,100),707,321);
        addObject(new Monster(false,100),317,100);
        addObject(new Monster(true,100),48,198); // This one drops a key
        addObject(new Monster(false,100),882,128);
        addObject(new Monster(false,500),566,432); // High-value enemy

        // Add final platform and HUD
        StaticPlatform StaticPlatform17 = new StaticPlatform();
        addObject(StaticPlatform17,730,180);
        Lifes lifes = new Lifes();
        addObject(lifes,732,23); // Top-right corner HUD
        
        started(); // Start background music
    }

    /**
     * Called when world becomes active.
     * Starts playing background music in loop at reduced volume.
     */    
    public void started(){
        backgroundMusic.playLoop();
        backgroundMusic.setVolume(90);
        //Greenfoot.setSpeed(55);
    }
    
    /**
     * Called when world becomes inactive.
     * Stops background music when leaving this world.
     */    
    public void stopped(){
        backgroundMusic.stop();
    }
}
