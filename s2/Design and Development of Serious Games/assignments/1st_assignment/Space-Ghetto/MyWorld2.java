import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The second level world of the game.
 * Features more complex platforming challenges and enemies than MyWorld.
 * Manages level-specific data reset and background music.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class MyWorld2 extends World
{
    // Background music for level 2
    GreenfootSound backgroundMusic = new GreenfootSound("kernkraft.mp3");

    /**
     * Constructor for MyWorld2 - initializes the second level.
     * Sets world dimensions, background image, and prepares level objects.
     */
    public MyWorld2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960,540, 1); 
        prepare();
        setBackground("level2.jpg");
        GameData.resetLevelSpecificData();

    }

    /**
     * Populates the world with all game objects at their initial positions.
     * Includes:
     * - Player start position
     * - Platform layout (static and moving)
     * - Enemies with varying point values
     * - Level exit door
     * - HUD display
     */
    private void prepare()
    {
        // ===== PLATFORMS AND BLOCKS =====
        // Ground blocks
        Block1 block1 = new Block1();
        addObject(block1, 46, 494);
        
        Block1 block12 = new Block1();
        addObject(block12, 437, 494);
        
        // Moving platforms with defined movement ranges
        MovingPlatform movingblock1 = new MovingPlatform("tile2lv1.png", 100, 300, 1);
        addObject(movingblock1, 182, 421);
        
        MovingPlatform movingblock2 = new MovingPlatform("tile2lv1.png", 250, 500, -1);
        addObject(movingblock2, 319, 145);
        
        // Static platforms throughout the level
        addObject(new StaticPlatform(), 673, 426);
        addObject(new StaticPlatform(), 497, 335);
        addObject(new StaticPlatform(), 560, 335);
        addObject(new StaticPlatform(), 936, 91);
        addObject(new StaticPlatform(), 836, 526);
        addObject(new StaticPlatform(), 164, 235);
        addObject(new StaticPlatform(), 38, 142);
        addObject(new StaticPlatform(), 844, 149);
        addObject(new StaticPlatform(), 797, 526);
        addObject(new StaticPlatform(), 716, 314);
        
        // Additional blocks
        addObject(new Block1(), 835, 319);
        addObject(new Block1(), 650, 119);
        
        // ===== PLAYER AND EXIT =====
        Player player = new Player();
        addObject(player, 17, 430); // Starting position (left side)
        
        Door door = new Door();
        addObject(door, 938, 61); // Exit position (top-right)
        
        // ===== ENEMIES =====
        // Regular enemies (100 points)
        addObject(new Monster(false, 100), 828, 254);
        addObject(new Monster(false, 100), 522, 312);
        addObject(new Monster(false, 100), 328, 114);
        addObject(new Monster(false, 100), 41, 108);
        addObject(new Monster(false, 100), 857, 118);
        addObject(new Monster(false, 100), 175, 210);
        addObject(new Monster(false, 100), 650, 56);
        
        // Key-dropping enemy (center of level)
        addObject(new Monster(true, 100), 434, 430);
        
        // High-value enemy (500 points)
        addObject(new Monster(false, 500), 843, 492);
        
        // ===== HUD =====
        Lifes lifes = new Lifes();
        addObject(lifes, 769, 29); // Top-right display

    }
    
    /**
     * Called when world becomes active.
     * Starts playing background music in loop at 50% volume.
     */
    public void started(){
        backgroundMusic.playLoop();
        backgroundMusic.setVolume(50);
    }
    
    /**
     * Called when world becomes inactive.
     * Stops background music when leaving this world.
     */
    public void stopped(){
        backgroundMusic.stop();
    }
}
