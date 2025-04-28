/**
 * Central static class for managing persistent game state across levels.
 * 
 * Stores and manages global game data including:
 * - Player lives
 * - Game score
 * - Level-specific progress (keys found)
 * 
 * All members are static to maintain state between world transitions.
 * @author Efstathios Iosifidis 
 * @version 0.1
 */
public class GameData  
{
    // Player state variables
    public static int lives = 3;       // Current remaining lives
    public static int score = 0;       // Accumulated score

    // Level progression flags
    public static boolean currentLevelKeyFound = false;  // Current level key status
    

    /**
     * Resets all persistent game data to initial values.
     * Call when starting a new game session.
     */
    public static void reset() {
        lives = 3;
        score = 0;
    }

     /**
     * Resets level-specific data while maintaining persistent stats.
     * Call when transitioning to a new level.
     */
     public static void resetLevelSpecificData() {
         currentLevelKeyFound = false; 
     }
     
    /**
     * Empty constructor - no instantiation needed for static utility class.
     */
    public GameData() {
        // Prevent instantiation
    }
}
