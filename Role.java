/**
 * This class represents a role in the RPG games.
 * In this game, both Hero and Monster have inherited from this class.
 */
public class Role {
    protected String name;
    protected int level;
    protected double hp;

    public Role() {}

    public Role(String name, int level) {
        this.name = name;
        this.level = level;
        this.hp = 100.0 * level;
    }

    public void setHp() {
        this.hp = 100.0 * this.level;
    }
}
