import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A projectile that travels rightward, damaging enemies on contact.
 * Inherits core projectile behavior from Shooting superclass.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class RightShoot extends Shooting // Επεκτείνει την Shooting
{
    // Movement properties
    private int speed = 8; // Horizontal speed (positive for rightward)

    /**
     * Creates a right-moving projectile with visual and audio effects.
     */
    public RightShoot(){
       setImage("shootr.png");
       Greenfoot.playSound("shot.mp3"); 
   }
    
    /**
     * Main game loop - handles movement and collision detection.
     * Executes in fixed order: move first, then check collisions.
     */
    public void act() 
    {
        // 1. Apply movement
        setLocation(getX() + speed, getY());  // Move right by speed value
        
        // 2. Check for collisions
        checkHit();
    }

    /**
     * Checks for and handles collisions with game objects.
     * Priority:
     * 1. Monster collisions (immediate damage)
     * 2. Wall/world boundary collisions (cleanup)
     */
    public void checkHit()
    {
        // Check for Monster collisions
        Monster monster = (Monster) getOneIntersectingObject(Monster.class);

        if (monster != null) {
            // Handle monster hit
            monster.die();  // Trigger monster's death sequence
            getWorld().removeObject(this);  // Remove projectile
            Greenfoot.playSound("explosion.mp3");  // Play impact sound
        }
        else
        {
            // Check for wall/world edge collisions (handled by superclass)
            checkAndRemoveIfHitsWallOrEdge(); 
        }
    }
}
