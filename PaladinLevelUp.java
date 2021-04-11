/**
 * This class holds the algorithms for the level up behavior of hero Paladin.
 * Implementation of the Strategy Pattern!
 */
public class PaladinLevelUp implements LevelUpBehavior{
    private Hero hero;

    public PaladinLevelUp(Hero hero) {
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
        hero.expBonus = 0;

        hero.strength *= 1.1;
        hero.agility *= 1.05;
        hero.dexterity *= 1.1;
    }
}
