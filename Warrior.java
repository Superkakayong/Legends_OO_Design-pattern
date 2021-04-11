/**
 * This class stands for the hero Warrior, which is a hero type.
 */
public class Warrior extends Hero{
    public Warrior(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);

        // Implementation of Strategy Pattern!
        levelUpBehavior = new WarriorLevelUp(this);
    }
}
