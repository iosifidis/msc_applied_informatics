import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A horizontally moving platform that travels between two set boundaries.
 * Inherits from Platform to provide collision detection for other actors.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class MovingPlatform extends Platform // Επεκτείνει την Platform
{
    // Movement properties
    private int speed = 1;      // Pixels to move per act (absolute value)
    private int direction = 1;  // Current movement direction (1 = right, -1 = left) 
    
    // Movement boundaries
    private int minX;  // Leftmost x-coordinate boundary
    private int maxX;  // Rightmost x-coordinate boundary
    
    /**
     * Creates a new MovingPlatform with specified parameters.
     * 
     * @param imageFileName The image file to use for this platform
     * @param minXBound The left boundary x-coordinate
     * @param maxXBound The right boundary x-coordinate
     * @param initialDirection Starting direction (1 for right, -1 for left)
     */
    public MovingPlatform(String imageFileName, int minXBound, int maxXBound, int initialDirection)
    {
        setImage(imageFileName); 
        this.minX = minXBound;
        this.maxX = maxXBound;
        this.direction = initialDirection;
        
        // Note: Initial position is set in the World (addObject) between minX and maxX
    }

    /**
     * Main game loop - handles platform movement each frame.
     */
    public void act() 
    {
        movePlatform();  // Update platform position
    }
    
    /**
     * Moves the platform horizontally and handles boundary checks.
     * Adjusts position precisely when reaching boundaries to prevent overshooting.
     */
    private void movePlatform()
    {
        // Move platform according to current speed and direction
        setLocation(getX() + speed * direction, getY());

        // Calculate half width for boundary checks
        int halfWidth = getImage().getWidth() / 2;
        
        // Check right boundary collision
        if (getX() + halfWidth >= maxX) {
             // Adjust position to exactly at right boundary
            setLocation(maxX - halfWidth, getY());
            direction = -1; // Reverse direction to left 
        }
        // Check left boundary collision
        else if (getX() - halfWidth <= minX) {
            // Adjust position to exactly at left boundary
            setLocation(minX + halfWidth, getY());
            direction = 1; // Reverse direction to right 
        }
    }
    
    /**
     * Returns the current horizontal speed (including direction) of the platform.
     * This method is used by Actors standing on this platform to move along.
     */
    public int getHorizontalSpeed() {
        return speed * direction; 
    }
}
