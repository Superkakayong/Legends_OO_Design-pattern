/**
 * This class represents the weapon, which is a type of props.
 * Since a weapon can be used to inflict the enemies, this class also implements the CauseDamage interface.
 */
public final class Weapon extends Prop implements CauseDamage{
    private int baseDamage;
    private int requiredHands;

    public Weapon(String name, int cost, int requiredLevel, int baseDamage, int requiredHands) {
        super(name, cost, requiredLevel);
        this.baseDamage = baseDamage;
        this.requiredHands = requiredHands;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getRequiredHands() {
        return requiredHands;
    }

    /*
        Override the damage() method.
        A weapon can cause damage to other players.
     */
    @Override
    public double damage(double strength) {
        return (strength + baseDamage) * 0.5;
    }
}
