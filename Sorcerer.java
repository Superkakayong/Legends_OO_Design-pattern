/**
 * This class stands for the hero Sorcerer, which is a hero type.
 */
public class Sorcerer extends Hero{
    public Sorcerer(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);

        // Implementation of Strategy Pattern!
        levelUpBehavior = new SorcererLevelUp(this);
    }
}
