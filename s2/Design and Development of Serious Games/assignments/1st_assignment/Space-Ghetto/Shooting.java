import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract base class for all projectile types in the game.
 * Provides common collision detection and cleanup functionality.
 * 
 * Responsibilities:
 * - Handles wall/world boundary collision detection
 * - Provides automatic projectile cleanup
 * - Serves as foundation for directional projectiles
 * 
 * Note: Concrete implementations (LeftShoot/RightShoot) handle their own:
 * - Movement logic
 * - Specific collision responses
 * - Visual representations
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */ 
public class Shooting extends Actor
{
    /**
     * Checks for and handles collisions with walls/platforms or world edges.
     * 
     * @return true if projectile was removed, false if still active
     * 
     * Behavior:
     * 1. Checks for Platform collisions (walls)
     * 2. Checks for world boundary proximity
     * 3. Automatically removes projectile on collision
     * 4. Uses 5-pixel buffer zone at world edges
     */
    protected boolean checkAndRemoveIfHitsWallOrEdge() // protected = accessible by this class and its subclasses
    {
       // Check for platform/wall collisions
       Actor wall = getOneIntersectingObject(Platform.class);

        if (wall != null) {
           getWorld().removeObject(this); // Remove projectile on wall hit
           return true; // Indicate removal occurred
       }

       // Check world boundaries with buffer zone
        int worldWidth = getWorld().getWidth();
       if (getX() < 5 || getX() > worldWidth - 5) 
       {
           getWorld().removeObject(this); // Remove projectile at edges
           return true; // Indicate removal occurred
       }
       
       return false; // Projectile remains active
    }
}
