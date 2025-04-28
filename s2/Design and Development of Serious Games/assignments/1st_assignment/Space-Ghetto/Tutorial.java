import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Tutorial button in the game's menu system.
 * 
 * Handles:
 * - Visual representation of the tutorial button
 * - Click detection and response
 * - Transition to the TutorialWorld
 * 
 * Extends the Buttons class for common button functionality.
 *
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Tutorial extends Buttons
{

    /**
     * Creates a Tutorial button with the specified button image.
     */
    public Tutorial(){
        setImage("button_tutorial.png");
    }
    
    /**
     * Main game loop - handles button click detection and response.
     * 
     * When clicked:
     * 1. Transitions to TutorialWorld
     * 2. Menu world cleanup is handled automatically by Greenfoot
     * 
     * Note: No manual cleanup of Menu world is needed as:
     * - Greenfoot automatically calls stopped() during world transitions
     * - Menu world likely has no resources needing cleanup
     */
    public void act() 
    {
        // Check if this button was clicked
        if(Greenfoot.mouseClicked(this)){
            // Create and transition to the tutorial screen
            Greenfoot.setWorld(new TutorialWorld());
        }
    }    
}
