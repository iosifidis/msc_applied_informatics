import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Required for Color objects

/**
 * The player-controlled character with complete platformer mechanics.
 * Handles movement, physics, combat, animation, and damage systems.
 * 
 * Features:
 * - Smooth horizontal movement with wall collision
 * - Vertical jumping and falling with gravity
 * - Shooting mechanics with cooldown
 * - Sprite animation for all states
 * - Damage handling and invulnerability frames
 * - Moving platform synchronization
 * - World boundary checks
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Player extends Actor
{
    // *** PHYSICS - VERTICAL MOVEMENT (FALLING / JUMPING) ***
    private int vSpeed = 0;            // Current vertical speed
    private int acceleration = 1;      // Gravity acceleration
    private int maxVSpeed = 12;        // Maximum fall speed
    private boolean isJumping = false; // Jump state flag
    private int jumpStrength = 16;     // Initial jump velocity

    // *** MOVEMENT - HORIZONTAL ***
    private int horizontalSpeed = 4;   // Ground movement speed
    private int direction = 1;         // Facing direction (1=right, -1=left)

    // *** COMBAT - SHOOTING ***
    private int shootingCooldownDuration = 20; // Frames between shots
    private int shootingCounter = 0;           // Current cooldown timer

    // *** ANIMATION ***
    private GreenfootImage[] runRight = new GreenfootImage[4]; // Right-run frames
    private GreenfootImage[] runLeft = new GreenfootImage[4];  // Left-run frames 
    private GreenfootImage idleRight;  // Default facing right
    private GreenfootImage idleLeft;   // Default facing left
    private int animationFrame = 0;    // Current animation frame
    private int animationSpeed = 4;    // Frames per animation step
    private int animationCounter = 0;  // Animation timer

    // *** DAMAGE & INVULNERABILITY ***
    private int damageCooldownDuration = 60; // Invulnerability duration
    private int damageCooldownTimer = 0;     // Current invulnerability timer
    
    /**
     * Initializes player with animation frames and default state.
     */
    public Player(){
        // Load running animation frames
        runRight[0] = new GreenfootImage("p1r.png"); 
        runRight[1] = new GreenfootImage("p2r.png");
        runRight[2] = new GreenfootImage("p3r.png"); 
        runRight[3] = new GreenfootImage("p4r.png");
        
        runLeft[0] = new GreenfootImage("p1l.png"); 
        runLeft[1] = new GreenfootImage("p2l.png");
        runLeft[2] = new GreenfootImage("p3l.png"); 
        runLeft[3] = new GreenfootImage("p4l.png");

        // Set up idle images
        idleRight = new GreenfootImage("p0.png");
        idleLeft = new GreenfootImage("p0.png"); 
        idleLeft.mirrorHorizontally(); // Create left-facing version

        setImage(idleRight); // Default facing right
        shootingCounter = 0;
        damageCooldownTimer = 0;
    }

    /**
     * Main game loop - handles all player systems each frame.
     * Execution order is critical for proper physics.
     */
    public void act() 
    {
        checkKeyInputAndMove();  // 1. Process controls
        checkFall();             // 2. Apply gravity
        moveWithPlatform();      // 3. Sync with moving platforms
        checkRightWalls();       // 4. Horizontal collision
        checkLeftWalls();
        platformAbove();         // 5. Vertical collision
        checkFallingDamage();    // 6. World bounds check
        applyAnimation();        // 7. Update visuals
        updateCooldowns();       // 8. Refresh timers
        checkGameOver();         // 9. State check
    }

    /**
     * Updates all cooldown timers.
     */
    private void updateCooldowns() {
        shootingCounter--;
        animationCounter++;
        if (damageCooldownTimer > 0) damageCooldownTimer--;
    }

    /**
     * Synchronizes movement with any moving platform the player stands on.
     */
    private void moveWithPlatform() {
        Platform platformBeneath = getPlatformBeneath(); 
        if (platformBeneath != null && platformBeneath instanceof MovingPlatform) { 
            MovingPlatform movingPlatform = (MovingPlatform) platformBeneath; 
            setLocation(getX() + movingPlatform.getHorizontalSpeed(), getY());
        }
    }

    /**
     * Handles all keyboard input for movement and actions.
     */
    public void checkKeyInputAndMove() 
    {
        boolean isMovingHorizontally = false;
    
        // Horizontal movement
        if(Greenfoot.isKeyDown("right"))
        {
            moveRight();
            isMovingHorizontally = true;
        }
        else if(Greenfoot.isKeyDown("left")) 
        {
            moveLeft();
            isMovingHorizontally = true;
        }
        
        // Jumping (only when grounded)
        if(Greenfoot.isKeyDown("up") && onGround()) 
        {
            jump();
        }
    
        // Shooting
        if(Greenfoot.isKeyDown("space")){
            shoot();
        }
    }

    /**
     * Moves player right with world boundary check.
     */
    public void moveRight()
    {
        direction = 1;
        int nextX = getX() + horizontalSpeed;
        int worldRightBoundaryForCenter = getWorld().getWidth() - (getImage().getWidth() / 2); 

        if (nextX <= worldRightBoundaryForCenter) {
            setLocation(nextX, getY());
        } else {
             setLocation(worldRightBoundaryForCenter, getY());
        }
    }

    /**
     * Moves player left with world boundary check.
     */
    public void moveLeft()
    {
        direction = -1;
        int nextX = getX() - horizontalSpeed;
        int worldLeftBoundaryForCenter = (getImage().getWidth() / 2);

        if (nextX >= worldLeftBoundaryForCenter) {
            setLocation(nextX, getY());
        } else {
             setLocation(worldLeftBoundaryForCenter, getY());
        }
    }
    
    /**
     * Updates player sprite based on current state.
     */
    private void applyAnimation() 
    {
         if (animationCounter % animationSpeed == 0) 
         {
             boolean isMovingHorizontally = Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left");

             if (isMovingHorizontally && onGround())
             {
                 animationFrame++;
                 if (animationFrame >= runRight.length) animationFrame = 0;

                 if(direction == 1) setImage(runRight[animationFrame]);
                 else setImage(runLeft[animationFrame]);
             } 
             else if (isJumping)
             {
                  animationFrame++;
                  if (animationFrame >= runRight.length) animationFrame = 0;
                  if (direction == 1) setImage(runRight[animationFrame]); 
                  else setImage(runLeft[animationFrame]);
             } 
             else
             {
                animationFrame = 0;
                if (direction == 1) setImage(idleRight);
                else setImage(idleLeft);
             }
         }
    }

    /**
     * Fires a projectile in current facing direction if cooldown expired.
     * @return true if shot was fired, false if on cooldown
     */
    public boolean shoot() 
    {
        if(shootingCounter <= 0)
        {
            int xOffset = (getImage().getWidth() / 2 + 5) * direction;
            int yOffset = - getImage().getHeight() / 4;

            if(direction == 1)
            {
                getWorld().addObject(new RightShoot(), getX() + xOffset, getY() + yOffset);
            }
            else
            {
                getWorld().addObject(new LeftShoot(), getX() + xOffset, getY() + yOffset);
            }

            shootingCounter = shootingCooldownDuration; 
            Greenfoot.playSound("shot.mp3");
            return true;
        }
        return false;
    }

    // ===== PHYSICS & COLLISION METHODS =====
    
    /**
     * Gets the platform directly beneath the player.
     * @return Platform actor below, or null if none
     */
    private Platform getPlatformBeneath() {
        int verticalOffset = (getImage().getHeight() / 2) + 2;
        return (Platform) getOneObjectAtOffset(0, verticalOffset, Platform.class);
    }

    /**
     * Checks for ceiling collision during upward movement.
     * @return true if ceiling was hit
     */
    public boolean platformAbove() {
        int verticalOffset = - (getImage().getHeight() / 2 + 2);
        Actor ceiling = getOneObjectAtOffset(0, verticalOffset, Platform.class);
        
        if(ceiling != null && vSpeed < 0) {
            vSpeed = 1;  // Bounce down slightly
            bopHead(ceiling);
            return true;
        }
        return false;
    }

    public boolean checkRightWalls()
    {
        int horizontalOffset = (getImage().getWidth() / 2) + 2;
        Actor rightWall = getOneObjectAtOffset(horizontalOffset, 0, Platform.class); 
        if(rightWall != null)
        {
            stopByRightWall(rightWall);
            return true;
        }
        return false;
    }

    public void stopByRightWall(Actor rightWall)
    {
        int wallLeftEdgeX = rightWall.getX() - (rightWall.getImage().getWidth() / 2);
        int newX = wallLeftEdgeX - (getImage().getWidth() / 2);
        setLocation(newX - 1, getY());
    }

    public boolean checkLeftWalls()
    {
        int horizontalOffset = -(getImage().getWidth() / 2) - 2;
        Actor leftWall = getOneObjectAtOffset(horizontalOffset, 0, Platform.class); 
        if(leftWall != null)
        {
            stopByLeftWall(leftWall);
            return true;
        }
        return false;
    }

    public void stopByLeftWall(Actor leftWall)
    {
        int wallRightEdgeX = leftWall.getX() + (leftWall.getImage().getWidth() / 2);
        int newX = wallRightEdgeX + (getImage().getWidth() / 2); 
        setLocation(newX + 1, getY());
    }

    public void bopHead(Actor ceiling)
    {
        int ceilingBottomEdgeY = ceiling.getY() + (ceiling.getImage().getHeight() / 2);
        int newY = ceilingBottomEdgeY + (getImage().getHeight() / 2);
        setLocation(getX(), newY);
    }

    public void fall() {
        vSpeed += acceleration;
        if (vSpeed > maxVSpeed) vSpeed = maxVSpeed;
        setLocation(getX(), getY() + vSpeed);
        isJumping = true;
    }

    public boolean onGround() {
        Platform ground = getPlatformBeneath();
        
        if(ground != null && vSpeed >= 0) {
            moveToGround(ground);
            vSpeed = 0;
            isJumping = false;
            return true;
        }
        isJumping = true;
        return false;
    }

    public void moveToGround(Actor ground)
    {
        int groundTopEdgeY = ground.getY() - (ground.getImage().getHeight() / 2);
        int newY = groundTopEdgeY - (getImage().getHeight() / 2);
        setLocation(getX(), newY);
        isJumping = false;
    }

    public void checkFall() 
    {
        if(!onGround())
        {
            fall();
        }
    }

    public void jump() 
    {
        vSpeed = -jumpStrength;
        isJumping = true;
    }

    /**
     * Checks if player fell out of world bounds and applies damage.
     */
    public void checkFallingDamage()
    {
        int worldBottomY = getWorld().getHeight();
        int playerBottomY = getY() + (getImage().getHeight() / 2);

        if(playerBottomY >= worldBottomY)
        {
             if (damageCooldownTimer == 0) 
             {
                 GameData.lives--;
                 damageCooldownTimer = damageCooldownDuration;
             }
             setLocation(20,415); 
             vSpeed = 0;
             isJumping = false;
        }
    }

    /**
     * Checks game over condition when lives reach zero.
     */
    public void checkGameOver() 
    {
        if(GameData.lives <= 0)
        {
            Greenfoot.playSound("gameover.mp3");
            Greenfoot.setWorld(new GameOverWorld());
        }
    }
}
