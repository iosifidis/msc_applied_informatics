import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A platform block (Tile) used in level design, with a specific image.
 * Inherits from the Platform class, likely representing a static or interactive
 * game element that characters can stand on or interact with.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Block1 extends Platform
{
    /**
     * Constructor for Block1 - initializes the block with its specific tile image.
     */
    public Block1(){
        setImage("tile1lv1.png");
    }
    
    /**
     * Act method - called every frame to perform game logic.
     * Currently empty as this block may not need per-frame behavior.
     * Can be overridden to add functionality like movement, collisions, or effects.
     */
    public void act()
    {
        // Add your action code here.
    }
}
