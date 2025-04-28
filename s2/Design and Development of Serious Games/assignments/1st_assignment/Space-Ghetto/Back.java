import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Back button class that returns to the main menu when clicked.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Back extends Buttons
{
    /**
     * Constructor for Back button - sets the initial button image
     */
    public Back(){
        setImage("button_back.png");
    }

    /**
     * Act method - checks for mouse clicks and handles button press
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            getWorld().stopped(); 
            Greenfoot.setWorld(new Menu()); 
        }
    }
}
