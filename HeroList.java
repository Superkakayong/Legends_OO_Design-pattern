import java.util.ArrayList;

/**
 * This class stores all the heroes in this game.
 */
public class HeroList {
    private static ArrayList<Hero> list; // List of all the heroes in this game

    public HeroList() {}

    private static void setHeroes() {
        list = new ArrayList<>();

        list.add(new Warrior("Neville", 2, 100, 700, 400, 400, 1354, 7));
        list.add(new Warrior("Ron", 4, 600, 800, 600, 400, 1204, 8));
        list.add(new Warrior("Harry Potter", 7, 750, 850, 400, 600, 2800, 6));
        list.add(new Warrior("Hermione", 8, 300, 400, 250, 600, 4000, 9));
        list.add(new Warrior("Luna", 5, 300, 600, 400, 350, 1111, 5));
        list.add(new Warrior("Albus Dumbledore", 10, 900, 1100, 600, 900, 3821, 10));

        list.add(new Sorcerer("Gilderoy Lockhart", 2, 600, 200, 444, 250, 2500, 5));
        list.add(new Sorcerer("Remus Lupin", 4, 800, 350, 500, 300, 1900, 6));
        list.add(new Sorcerer("Severus Snape", 6, 820, 550, 700, 550, 2800, 6));
        list.add(new Sorcerer("Sybill Trelawney", 5, 700, 280, 666, 460, 3000, 7));
        list.add(new Sorcerer("Nicolas Flamel", 8, 1200, 200, 1500, 650, 2400, 10));

        list.add(new Paladin("Mad-Eye", 5, 500, 700, 850, 250, 2500, 5));
        list.add(new Paladin("Filius Flitwick", 4, 430, 650, 750, 350, 1203, 7));
        list.add(new Paladin("Horace Slughorn", 4, 300, 800, 640, 200, 3023, 6));
        list.add(new Paladin("Quirinus Quirrell", 2, 350, 470, 120, 250, 1111, 5));
        list.add(new Paladin("Professor McGonagall", 8, 620, 900, 900, 400, 900, 8));
    }

    public static ArrayList<Hero> getList() {
        return list;
    }

    /*
        Print the list of all 3 kinds of heroes
     */
    public static void printHeroList() {
        setHeroes(); // Initialize the ArrayList heroes

        String name;
        double hp, strength, dexterity, agility, money, mana;
        int level, exp;

        NotificationCenter.heroesInfo(4);

        // A line to split different kinds of heroes (i.e. warriors/sorcerers/paladins)
        String splitLine = Colors.YELLOW_BG + Colors.BLACK +
                "--><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><--" +
                Colors.RESET + "\n";
        String title = "Name                       HP    Level    Mana    Strength    Dexterity    Agility    Money    Exp";

        NotificationCenter.heroesInfo(5);
        System.out.println(title);
        System.out.print(splitLine);

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) instanceof Sorcerer && list.get(i - 1) instanceof Warrior) {
                // If the class type has changed (i.e. from Warrior to Sorcerer), print the title and the split line again
                NotificationCenter.heroesInfo(6);
                System.out.println(title);
                System.out.print(splitLine);
            } else if (list.get(i) instanceof Paladin && list.get(i - 1) instanceof Sorcerer) {
                // If the class type has changed (i.e. from Sorcerer to Paladin), print the title and the split line again
                NotificationCenter.heroesInfo(7);
                System.out.println(title);
                System.out.print(splitLine);
            }

            name = (i + 1) + ". " + list.get(i).name;
            hp = list.get(i).hp;
            level = list.get(i).level;
            mana = list.get(i).mana;
            strength = list.get(i).strength;
            dexterity = list.get(i).dexterity;
            agility = list.get(i).agility;
            money = list.get(i).money;
            exp = list.get(i).exp;

            System.out.printf("%-25s %-8.0f %-6d %-9.0f %-12.0f %-11.0f %-9.0f %-8.0f %-5d", name, hp, level, mana, strength,
                    dexterity, agility , money, exp);
            System.out.println();

            // Java Reflection implementation
            if ((i + 1 < list.size()) && (!list.get(i).getClass().getName().equals
                    (list.get(i + 1).getClass().getName()))) {
                // If the class type has changed, print the split line again
                System.out.println(splitLine);
            }
        }
        System.out.println(splitLine);
    }
}
