import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a Key object that the Player must collect to unlock the next door.
 * Interacts with the Player to be collected.
 *
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Key extends Actor
{

    /**
     * Creates a new Key with default image.
     */
    public Key(){
        setImage("key-icon.png");
    }
    
    /**
     * Act - Checks if the Key is collected by the Player.
     */
    public void act()
    {
        checkIfCollected();
    }

    /**
     * Checks if the key is touching the Player.
     * If so, mark the key as found in GameData and remove this key object.
     */
    private void checkIfCollected()
    {
        if (isTouching(Player.class))
        {
            
            GameData.currentLevelKeyFound = true;
            getWorld().removeObject(this);
        }
    }
}
