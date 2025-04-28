import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Play button in the game's menu system.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Play extends Buttons
{   
    /**
     * Constructor for Play button.
     * Sets the visual appearance of the button.
     */
    public Play(){
        setImage("button_play-game.png");
    }
    
    /**
     * Main game loop - handles button click detection and response.
     * 
     * When clicked:
     * 1. Resets all game data via GameData.reset()
     * 2. Transitions to MyWorld (Level 1)
     * 
     * Note: The Menu world's stopped() method is automatically called by Greenfoot
     * during world transition, so no manual call is needed.
     */
    public void act() 
    {
         // Check if this button was clicked
        if(Greenfoot.mouseClicked(this)){
            // Reset all persistent game data (score, lives, etc.)
            GameData.reset(); 
            // Create and transition to Level 1 (MyWorld)
            Greenfoot.setWorld(new MyWorld()); 
        }
    }    
}
