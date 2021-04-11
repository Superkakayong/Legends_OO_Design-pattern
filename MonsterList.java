import java.util.ArrayList;

/**
 * This class stores all the monsters in the game.
 */
public class MonsterList {
    private ArrayList<Monster> monsters;

    public MonsterList() {
        monsters = new ArrayList<>();

        monsters.add(new Dragon("Chinese Fireball", 9, 900, 600, 60));
        monsters.add(new Dragon("Hungarian Horntail", 9, 900, 500, 60));
        monsters.add(new Dragon("Welsh Green", 6, 600, 400, 40));
        monsters.add(new Dragon("Swedish Short Snout", 7, 750, 400, 45));
        monsters.add(new Dragon("T-Rex", 10, 1500, 900, 65));
        monsters.add(new Dragon("Peruvian Vipertooth", 4, 500, 500, 30));
        monsters.add(new Dragon("Norwegian Ridgeback", 5, 580, 400, 30));

        monsters.add(new Exoskeleton("Erumpent", 7, 600, 800, 30));
        monsters.add(new Exoskeleton("Grindylow", 8, 650, 850, 45));
        monsters.add(new Exoskeleton("Centaur", 6, 350, 650, 50));
        monsters.add(new Exoskeleton("Basilisk", 10, 700, 1000, 70));
        monsters.add(new Exoskeleton("Unicorn", 4, 400, 500, 30));

        monsters.add(new Spirit("FallenAngel", 5, 400, 500, 35));
        monsters.add(new Spirit("Bellatrix", 8, 800, 700, 85));
        monsters.add(new Spirit("Malfoy", 6, 600, 400, 70));
        monsters.add(new Spirit("Death Eaters", 7, 600, 500, 75));
        monsters.add(new Spirit("Voldemort", 10, 1200, 1000, 90));
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
