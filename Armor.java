/**
 * This class manages the properties of an armor.
 * It also extends from the class Prop since an armor is a prop.
 */
public final class Armor extends Prop{
    private int damageReduction;

    public Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}
