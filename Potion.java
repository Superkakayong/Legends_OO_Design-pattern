/**
 * This class represents the potion, which is a type of props.
 */
public class Potion extends Prop{
    private int increasedAttr;
    private String affectedAttr;
    private boolean hasUsed;

    public Potion(String name, int cost, int requiredLevel, int increasedAttr, String affectedAttr) {
        super(name, cost, requiredLevel);
        this.increasedAttr = increasedAttr;
        this.affectedAttr = affectedAttr;
        this.hasUsed = false;
    }

    // Get the amount of increase of the specific attribute
    public int getIncreasedAttr() {
        return increasedAttr;
    }

    // Get the name of the specific attribute that is increased by the potion
    public String getAffectedAttr() {
        return affectedAttr;
    }
}
