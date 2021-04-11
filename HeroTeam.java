import java.util.ArrayList;

/**
 * This class manages the operations of a team of heroes during the game.
 */
public class HeroTeam {
    private ArrayList<Hero> team;

    public HeroTeam() {
        team = new ArrayList<>();
    }

    public ArrayList<Hero> getTeam() {
        return team;
    }

    public void addMembers(Hero h) {
        if (team.size() == 3) { return; } // The MAX team size is 3
        team.add(h);
    }
}
