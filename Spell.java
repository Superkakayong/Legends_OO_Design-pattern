/**
 * This class represents the potion, which is a type of props.
 * Since a spell can be used to inflict the enemies, this class also implements the CauseDamage interface.
 */
public class Spell extends Prop implements CauseDamage{
    private int baseDamage;
    private int manaCost;

    public Spell() {}

    public Spell(String name, int cost, int requiredLevel, int baseDamage, int manaCost) {
        super(name, cost, requiredLevel);
        this.baseDamage = baseDamage;
        this.manaCost = manaCost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getManaCost() {
        return manaCost;
    }

    /*
        Override the damage() method.
        A spell can cause damage to other players.
     */
    @Override
    public double damage(double dexterity) {
        return baseDamage + (dexterity / 10000.0) * baseDamage;
    }
}
