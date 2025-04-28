import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays the player's current lives and score on screen as a HUD (Heads-Up Display).
 * Continuously updates to reflect the current game state from GameData.
 * Automatically triggers game over when lives reach zero.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Lifes extends Actor
{
    /**
     * Main game loop - updates the HUD display every frame and checks for game over condition.
     */
    public void act()   
    {   
        // Get current game state from GameData
        int currentLives = GameData.lives;
        int currentScore = GameData.score;

        // Create a new image for the HUD display
        // Parameters: width, height of the HUD background
        GreenfootImage hudImage = new GreenfootImage(300, 50); 
        
        // Set text properties
        hudImage.setColor(Color.WHITE);
        hudImage.setFont(new Font("Arial", true, false, 20));
        
        // Draw lives and score text on the HUD image
        // Parameters: text, x-position, y-position
        hudImage.drawString("Lives: " + currentLives, 10, 20);
        hudImage.drawString("Score: " + currentScore, 10, 40);
        
         // Set this image as the actor's visual representation
        setImage(hudImage);
        
        // Check for game over condition (no lives remaining)
        if (currentLives <= 0) {
            getWorld().stopped();
            Greenfoot.setWorld(new GameOverWorld());
        }
    }
}
