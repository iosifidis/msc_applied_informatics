import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Represents the Game Over screen shown when the player runs out of lives.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class GameOverWorld extends World
{
    /**
     * Constructs the Game Over screen world.
     * Initializes background and displays final score.
     */
    public GameOverWorld() {    
        
        super(960, 540, 1);
        setBackground("youlose.jpg"); 
        
        
        // Display player's final score centered near bottom
        showFinalScore();

    }

    /**
     * Displays the player's final score on the victory screen.
     * Positioned at horizontal center, 50px from bottom.
     */
    private void showFinalScore()
    {
        showText("Your final score is: " + GameData.score, 
                getWidth() / 2,       // Centered horizontally
                getHeight() - 50);    // 50px from bottom
    }

    /**
     * Optional: Act method for handling clicks on the Game Over screen.
     */
    public void act() {
         // Optional: Go back to menu on any click
        if (Greenfoot.mouseClicked(null)) { // Clicks anywhere on the world
            GameData.reset(); // Reset score/lives for a new game
            Greenfoot.setWorld(new Menu()); // Go back to the Menu
        }
    }
}
