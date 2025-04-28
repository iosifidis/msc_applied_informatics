import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The tutorial screen world that displays game instructions and controls.
 * 
 * Features:
 * - Full-screen tutorial background image
 * - Back button to return to main menu
 * - Standard 960x540 resolution (matches game worlds)
 * 
 * Usage:
 * - Accessed from the main menu via Tutorial button
 * - Provides players with game instructions
 * - Returns to menu when back button is clicked
 *
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class TutorialWorld extends World
{
    /**
     * Constructs the TutorialWorld with default settings.
     * Initializes:
     * - World dimensions (960x540)
     * - Tutorial background image
     * - Back button navigation
     */
    public TutorialWorld()
    {    
        super(960, 540, 1); 
        prepare();
        setBackground("tutorial.jpg");
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Back(), 886, 502); 
    }
}
