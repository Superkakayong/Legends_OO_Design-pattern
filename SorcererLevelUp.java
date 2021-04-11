/**
 * This class holds the algorithms for the level up behavior of hero Sorcerer.
 * Implementation of the Strategy Pattern!
 */
public class SorcererLevelUp implements LevelUpBehavior{
    private Hero hero;

    public SorcererLevelUp(Hero hero) {
        this.hero = hero;
    }

    /*
        Check if a hero is qualified to level up.
     */
    @Override
    public boolean canLevelUp() {
        return (hero.expBonus >= hero.level * 10);
    }

    /*
        Perform level up.
     */
    @Override
    public void levelUp() {
        if (!canLevelUp()) { return; }

        ++hero.level;
        hero.exp += hero.expBonus;
        hero.expBonus = 0;

        hero.strength *= 1.05;
        hero.agility *= 1.1;
        hero.dexterity *= 1.1;
    }
}
