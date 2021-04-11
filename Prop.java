/**
 * This class represents all props in the game.
 * It is the super class of all kinds of props.
 * It inherits from the Trade interface to guarantee that a prop can be bought and sold.
 */
public class Prop implements Trade {
    protected String name;
    protected int cost;
    protected int requiredLevel;

    public Prop() {}

    public Prop(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }

    /*
        Check if the hero can afford this prop.
     */
    @Override
    public boolean isAffordable(Hero hero) {
        return hero.level >= requiredLevel && hero.getMoney() >= cost;
    }
}
