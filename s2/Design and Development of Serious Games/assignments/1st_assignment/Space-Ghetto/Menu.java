import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main menu world that appears when the game starts.
 * Provides navigation options to start the game or view tutorials.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
         // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1);
        setBackground("menu.png");
        Greenfoot.start();
        prepare();        
    }

    /**
     * Prepares the world by creating and adding all initial menu objects.
     * This includes:
     * - Play button (starts the game)
     * - Tutorial button (shows instructions)
     * 
     * Objects are added at specific pixel coordinates for proper layout.
     */
    private void prepare()
    {
        // Create and add the Play button at specified coordinates
        Play play = new Play();
        addObject(play, 485, 360); 

        // Create and add the Tutorial button at specified coordinates
        Tutorial tutorial = new Tutorial();
        addObject(tutorial, 484, 423);
    }
}
