import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The victory screen displayed when the player completes the game.
 * Shows the player's final score and provides navigation options.
 * 
 * Features:
 * - Victory background image
 * - Final score display
 * - Consistent 960x540 resolution
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class WinWorld extends World
{
    /**
     * Constructs the victory screen world.
     * Initializes:
     * - World dimensions matching game resolution
     * - Victory background image
     * - Final score display from GameData
     */
    public WinWorld()
    {    
        super(960, 540, 1);
        setBackground("winbg.jpg");
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
}
