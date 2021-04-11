/**
 * Since the algorithms for the level up behavior of different types of heroes vary,
 * I have implemented the Strategy Pattern to facilitate the level up operations for heroes.
 */
public interface LevelUpBehavior {
    /*
        Check if a hero is qualified to level up.
     */
    boolean canLevelUp();

    /*
        Perform level up.
     */
    void levelUp();
}
