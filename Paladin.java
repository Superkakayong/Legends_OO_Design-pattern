/**
 * This class stands for the hero Paladin, which is a hero type.
 */
public class Paladin extends Hero{
    public Paladin(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);

        // Implementation of Strategy Pattern!
        levelUpBehavior = new PaladinLevelUp(this);
    }
}
