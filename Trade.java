/**
 * This interface guarantees the ability of a class that implements it can be traded
 * (i.e. can be bought and sold).
 */
public interface Trade {
    /*
        Check if a specific hero can afford a specific prop
     */
    boolean isAffordable(Hero hero);
}

