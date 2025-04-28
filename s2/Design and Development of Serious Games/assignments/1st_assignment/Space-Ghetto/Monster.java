import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a walking enemy that moves back and forth, falls off ledges,
 * and gives points/drops a key when defeated by a bullet.
 * Damages the player on contact if not in cooldown.
 * Moves horizontally along with Moving Platforms it stands on.
 * Extends the base Enemy class.
 * 
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class Monster extends Enemy 
{
    // *** MONSTER PROPERTIES / BEHAVIOR ON DEATH ***
    private final int pointsValue;  // Points awarded when monster is defeated
    private final boolean hasKey;   // Whether monster drops a key when killed
    private boolean isDead = false; // Current alive/dead state
    
    
    // *** DAMAGE TO PLAYER ***
    private int damageValue = 1;          // Damage dealt to player per hit
    private int damageCooldownDuration = 60; // Frames between damage instances
    private int damageCooldownTimer = 0;    // Current cooldown counter

    // *** MOVEMENT (HORIZONTAL) ***
    private int speed = 1;      // Horizontal movement speed
    private int direction = 1;  // Current facing direction (1 = right, -1 = left)

    // *** PHYSICS (VERTICAL - GRAVITY/FALLING) ***
    private int vSpeed = 0;     // Current vertical speed
    private int acceleration = 1; // Gravity acceleration
    private int maxVSpeed = 9;   // Maximum falling speed

    // *** ANIMATION ***
    private int frame = 0;            // Current animation frame
    private int animationSpeed = 4;    // Frames per animation step
    private int animationCounter = 0;  // Animation timer

    // Animation image arrays
    GreenfootImage[] walkLeft = new GreenfootImage[4];  // Left-facing frames
    GreenfootImage[] walkRight = new GreenfootImage[4]; // Right-facing frames


    /**
     * Constructor for Monster objects.
     * Initializes image, sets death behavior (points, key drop), and loads animations.
     * @param hasKey True if this monster drops a key on death.
     * @param pointsValue The number of points awarded when this monster is defeated.
     */
    public Monster(boolean hasKey, int pointsValue){
        this.hasKey = hasKey; 
        this.pointsValue = pointsValue;

        // Load animation images (adjust file names as needed)
        walkLeft[0] = new GreenfootImage("monster1l.png"); 
        walkLeft[1] = new GreenfootImage("monster2l.png"); 
        walkLeft[2] = new GreenfootImage("monster3l.png"); 
        walkLeft[3] = new GreenfootImage("monster4l.png");
        
        walkRight[0] = new GreenfootImage("monster1r.png");
        walkRight[1] = new GreenfootImage("monster2r.png");
        walkRight[2] = new GreenfootImage("monster3r.png"); 
        walkRight[3] = new GreenfootImage("monster4r.png");

        // Set initial image and calculate dimensions
        setImage(walkLeft[0]); // Can start with left or right based on initial direction if randomized

        // Example for random start direction:
        this.direction = Greenfoot.getRandomNumber(2) == 0 ? 1 : -1; // Randomly set to 1 (right) or -1 (left)

    }

    /**
     * Main game loop - handles all monster behavior each frame.
     * Execution order is important for proper physics.
     */
    public void act() 
    {
        if (isDead) {
        return; // Skip if already dead
        }
        
        // 1. Apply external horizontal movement (from moving platforms) BEFORE gravity or own movement
        moveWithPlatform(); 

        // 2. Apply gravity and check/handle landing on ground
        checkFall();          

        // 3. Determine own horizontal movement direction based on environment boundaries
        // This includes checking world edges, platform edges, and walls.
        checkEdge();           
        checkRightWalls();    
        checkLeftWalls();     

        // 4. Apply own horizontal movement
        move();                

        // 5. Update animation frame based on direction and movement
        setAnimation();        
        animationCounter ++;     

        // 6. Check for interactions (bullets, player) and trigger death or damage
        checkHitByBullet();    
        checkPlayerContact();  

        // 7. Update timers
        if (damageCooldownTimer > 0) {
            damageCooldownTimer--;
        }

    }   

    /**
     * Moves the monster horizontally by the speed of a MovingPlatform it's standing on.
     * Should be called at the start of act().
     */
    private void moveWithPlatform() {
        // Find the platform underneath
        Platform platformBeneath = getPlatformBeneath(); 
        
        // Check if the found platform is indeed a MovingPlatform instance
        if (platformBeneath != null && platformBeneath instanceof MovingPlatform) { 
            MovingPlatform movingPlatform = (MovingPlatform) platformBeneath; // Cast it
            int platformSpeed = movingPlatform.getHorizontalSpeed(); // Get its horizontal speed
            setLocation(getX() + platformSpeed, getY()); // Apply that speed to the monster's X position
        }
    }


    // *** PHYSICS - VERTICAL (GRAVITY/FALLING) ***

     /**
     * Applies gravitational pull, increasing vertical speed and moving the monster downwards.
     */
    public void fall() 
    {
        setLocation(getX(), getY() + vSpeed); 
        vSpeed = vSpeed + acceleration; // Increase speed by gravity's acceleration
        // Limit maximum falling speed
        if (vSpeed > maxVSpeed) {
            vSpeed = maxVSpeed;
        }
    }

    /**
     * Checks if the monster is currently standing on a Platform directly beneath its center.
     * Used by checkFall() and checkEdge(). Corrects position if landing.
     */
    public boolean onGround() // Checks for Platform below (using getPlatformBeneath helper)
    {
        Platform ground = getPlatformBeneath(); // Find ground platform below

        if(ground != null) // If a ground Platform is found
        {
            moveToGround(ground); // Snap monster onto the platform surface
            vSpeed = 0; // Stop vertical movement (landed)
            return true; // Monster is on the ground
        } else {
            return false; // Monster is in the air
        }
    }

    /**
     * Finds the Platform object located immediately beneath the monster's center point.
     * Helper method used by onGround() and moveWithPlatform().
     */
    private Platform getPlatformBeneath() { // Helper method (similar to Player's)
        // Point to check: Just below monster's bottom edge.
        int verticalOffset = (getImage().getHeight() / 2) + 5; // 5 pixels below bottom edge
        
        Actor groundActor = getOneObjectAtOffset(0, verticalOffset, Platform.class);
        
        if (groundActor != null) {
             return (Platform) groundActor; // Return found Platform actor
        } else {
            return null; // No Platform found
        }
    }

    /**
     * Corrects the monster's Y position precisely on top of a Platform it landed on.
     */
    public void moveToGround(Actor ground) 
    {
        // Calculate monster's center Y needed to align its bottom edge with ground's top edge
        int groundTopEdgeY = ground.getY() - (ground.getImage().getHeight() / 2);
        int newY = groundTopEdgeY - (getImage().getHeight() / 2); // Monster's new center Y
        setLocation(getX(), newY);
    }

    /**
     * Combines gravity application and check for ground collision.
     */
    public void checkFall() // Calls fall() if not onGround()
    {
        if(!onGround()) // If not currently on the ground
        {
            fall(); // Continue applying gravity and vertical movement
        }
        // If onGround() returns true, vSpeed is set to 0 in that method.
    }


    // *** MOVEMENT - HORIZONTAL (INCLUDING BOUNDARY CHECKS) ***

    /**
     * Checks for the edge of the world or the edge of a platform.
     * If an edge is detected in the current direction, reverses the monster's horizontal direction.
     * Assumes monster is onGround() when checking platform edges.
     */
    public void checkEdge() 
    {
        // 1. Check World Edges: If the monster's edge is within 5 pixels of the world boundary.
        int halfWidth = getImage().getWidth() / 2;
        if (getX() <= halfWidth + 5 || getX() >= getWorld().getWidth() - halfWidth - 5) 
        {
            direction *= -1; // Reverse horizontal direction
        }

        // 2. Check Platform Edges: Look for a Platform slightly ahead and below.
        // Horizontal offset: 5 pixels beyond the edge in the monster's current direction.
        int horizontalOffset = (halfWidth + 5) * direction; 
        // Vertical offset: 5 pixels below the monster's bottom edge.
        int verticalOffset = (getImage().getHeight() / 2) + 5; 

        Actor groundAhead = getOneObjectAtOffset(horizontalOffset, verticalOffset, Platform.class);

        // If there is NO platform ahead at ground level AND the monster IS currently on the ground,
        // it means the monster is approaching a ledge.
        if (groundAhead == null && onGround()) 
        {
             direction *= -1; // Reverse direction to avoid falling off
        }
    }
    
    /**
     * Applies the monster's own horizontal movement based on its current speed and direction.
     */
    public void move() 
    {
          // Collision with walls (checkRightWalls, checkLeftWalls) after this movement
         // will correct position if a wall was hit and reverse direction.
         setLocation(getX() + speed * direction, getY());

    }

    /**
     * Checks for a Platform wall object slightly to the right of the monster.
     * If found, calls stopByRightWall to correct position and reverses monster direction.
     */
    public boolean checkRightWalls() // Checks for wall collision on the right
    {
        int horizontalOffset = (getImage().getWidth() / 2) + 2; // 2 pixels outside right edge
        Actor rightWall = getOneObjectAtOffset(horizontalOffset, 0, Platform.class);
        if(rightWall != null)
        {
            stopByRightWall(rightWall); // Adjust position
            direction *= -1; // Reverse direction after hitting wall
            return true; // Hit a wall
        }
        return false; // No wall hit
    }

    /**
     * Adjusts the monster's X position precisely next to a right wall after collision.
     */
    public void stopByRightWall(Actor rightWall) // Corrects position after hitting right wall
    {
        // Align monster's right edge with wall's left edge
        int wallLeftEdgeX = rightWall.getX() - (rightWall.getImage().getWidth() / 2);
        int newX = wallLeftEdgeX - (getImage().getWidth() / 2); 
        setLocation(newX - 1, getY()); // Add 1 pixel buffer
    }

    /**
     * Checks for a Platform wall object slightly to the left of the monster.
     * If found, calls stopByLeftWall and reverses monster direction.
     */
    public boolean checkLeftWalls() // Checks for wall collision on the left
    {
        int horizontalOffset = -(getImage().getWidth() / 2) - 2; // 2 pixels outside left edge (negative offset)
        Actor leftWall = getOneObjectAtOffset(horizontalOffset, 0, Platform.class);
        if(leftWall != null)
        {
            stopByLeftWall(leftWall); // Adjust position
            direction *= -1; // Reverse direction after hitting wall
            return true; // Hit a wall
        }
        return false; // No wall hit
    }

    /**
     * Adjusts the monster's X position precisely next to a left wall after collision.
     */
    public void stopByLeftWall(Actor leftWall) // Corrects position after hitting left wall
    {
         // Align monster's left edge with wall's right edge
         int wallRightEdgeX = leftWall.getX() + (leftWall.getImage().getWidth() / 2);
        int newX = wallRightEdgeX + (getImage().getWidth() / 2); 
        setLocation(newX + 1, getY()); // Add 1 pixel buffer
    }


    // *** ANIMATION ***
   
    /**
     * Updates the monster's image for walking animation based on current frame,
     * direction, and animation speed.
     * Updates animation frame every 'animationSpeed' acts.
     */
    public void setAnimation() 
    {
        // Update animation frame only every 'animationSpeed' acts
        if(animationCounter % animationSpeed == 0) 
        {
             if(direction < 0) // Facing/Moving left
             {
                 frame++; // Go to next frame
                 if (frame >= walkLeft.length) frame = 0; // Loop animation
                 setImage(walkLeft[frame]); // Set left-facing frame
             }
             else // Facing/Moving right
             {
                 frame++; // Go to next frame
                 if (frame >= walkRight.length) frame = 0; // Loop animation
                 setImage(walkRight[frame]); // Set right-facing frame
             }
        }
    }


    // *** COMBAT - TAKING DAMAGE ***

    /**
     * Checks if the monster is currently colliding with an object of type Shooting (a bullet).
     * If hit, removes the bullet, triggers the monster's death process, and plays a sound.
     */
    public void checkHitByBullet() {
        if (isDead || getWorld() == null) {
            return; // Ο Monster έχει ήδη αφαιρεθεί
        }
    
        Actor bullet = getOneIntersectingObject(Shooting.class);
    
        if (bullet != null) {
            getWorld().removeObject(bullet);
            die();
            Greenfoot.playSound("explosion.mp3");
        }
    }


    /**
     * Handles the consequences of the monster being defeated.
     * Adds points to the player's score (via GameData), optionally drops a key,
     * and removes the monster from the world.
     */
    public void die() {
    
        if (isDead || getWorld() == null) {
        return;
        }
    
        // Set death flag FIRST to block concurrent interactions
        isDead = true;
        
        // -- Execute death effects --
        if (hasKey) {
            Key key = new Key();
            getWorld().addObject(key, getX(), getY());
        }
    
        GameData.score += pointsValue;
        
        // Remove from world LAST (after all other operations)
        try {
            getWorld().removeObject(this);
        } catch (IllegalStateException e) {
        // Fail silently if world is transitioning
        }
    }
    
    // *** INTERACTION WITH PLAYER ***

    /**
     * Checks if the monster's image is currently touching the player's image.
     * If contact is detected AND the player has passed the damage cooldown duration from *this* monster,
     * reduces the player's life count (in GameData) and activates this monster's damage cooldown timer.
     */
    public void checkPlayerContact() // Checks collision with the Player for damage
    {
        // Check if touching an object of type Player AND the monster's damage cooldown timer is 0 (or less).
        if (damageCooldownTimer <= 0 && isTouching(Player.class) ) // Use <=0 to be safe if it ever goes negative
        {
            // Collision detected with the Player, and this monster can deal damage again.
            
            GameData.lives -= damageValue; // Reduce player's lives count in the central GameData state

            // Reset this monster's damage cooldown timer to prevent it from damaging the player too quickly.
            damageCooldownTimer = damageCooldownDuration; 

        }
    }
}
