import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A non-moving platform that provides solid ground for actors to stand on.
 * Inherits from Platform class to share common platform behavior.
 * 
 * Features:
 * - Immovable solid surface
 * - Basic collision detection (inherited from Platform)
 * - Simple static visual representation
 * 
 * Usage:
 * - Place in world to create walkable surfaces
 * - Player and enemies can land on these platforms
 * - No special behavior or movement
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class StaticPlatform extends Platform
{
    /**
     * Creates a StaticPlatform with default platform image.
     * The image "tile2lv1.png" will be used as the visual representation.
     */
    public StaticPlatform(){
        setImage("tile2lv1.png");
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
