import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A door actor that transitions between levels when the player has the key.
 * Requires GameData.currentLevelKeyFound to be true for interaction.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Door extends Actor
{
    // Tracks local door state (could sync with GameData)
    private boolean isLocked = true;

    /**
     * Constructor - initializes the door as closed.
     * 
     */
    public Door(){
        
        setImage("door_closed.jpg"); 

    }
    
    /**
     * Main game loop - checks door state and player interaction.
     * 
     */
    public void act()
    {
        
        if (GameData.currentLevelKeyFound) {
            isLocked = false;
        }

        if (isTouching(Player.class) && !isLocked) // isTouching(Player.class) && GameData.currentLevelKeyFound
        {
            
             World currentWorld = getWorld();

             if (currentWorld instanceof MyWorld) { // IF MyWorld (Level 1)
                 Greenfoot.setWorld(new MyWorld2()); // GoTo MyWorld2 (Level 2)
             } else if (currentWorld instanceof MyWorld2) { // IF MyWorld2 (Level 2)
                 Greenfoot.setWorld(new WinWorld()); // GoTo WinWorld (winner)
             }
             
        }
    }
}
