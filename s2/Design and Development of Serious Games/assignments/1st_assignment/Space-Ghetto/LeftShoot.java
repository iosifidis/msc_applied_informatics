import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet that moves to the left.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class LeftShoot extends Shooting
{
    // Movement speed (negative for leftward)
    private int speed = -8;

    /**
     * Creates a new left-moving projectile.
     * Sets the appropriate image and plays firing sound.
     */
    public LeftShoot(){
        setImage("shootl.png");
        Greenfoot.playSound("shot.mp3"); 
    }
    
    /**
     * Main game loop - handles movement and collision detection.
     */
    public void act() 
    {
        setLocation(getX() + speed, getY()); 
        checkHit();
    }

    /**
     * Checks for collisions with Enemies or Walls.
     */
    public void checkHit()
    {
        // Checks for and handles collisions with Monster enemies.
        Monster monster = (Monster) getOneIntersectingObject(Monster.class);

        if (monster != null) {
            monster.die(); 
            getWorld().removeObject(this);
            Greenfoot.playSound("explosion.mp3");
        }
        else
        {
            checkAndRemoveIfHitsWallOrEdge(); 
        }
    }
}
