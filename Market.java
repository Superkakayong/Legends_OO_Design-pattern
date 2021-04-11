import java.util.ArrayList;

/**
 * This class stores all the props in the market of the game.
 */
public class Market {
    private final ArrayList<Weapon> weapons;
    private final ArrayList<Armor> armors;
    private final ArrayList<Potion> potions;
    private final ArrayList<Spell> spells;
    private final ArrayList<Prop> props;

    public Market() {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new ArrayList<>();
        spells = new ArrayList<>();
        props = new ArrayList<>();

        weapons.add(new Weapon("Sword", 700, 4, 800, 1));
        weapons.add(new Weapon("Wand", 900, 7, 1000, 1));
        weapons.add(new Weapon("Bow", 500, 2, 400, 2));
        weapons.add(new Weapon("Dagger", 350, 1, 250, 1));

        armors.add(new Armor("Platinum_Shield", 150, 1, 200));
        armors.add(new Armor("Breastplate", 350, 3, 600));
        armors.add(new Armor("Full_Body_Armor", 1000, 5, 1100));
        armors.add(new Armor("Invisibility_Cloak", 1500, 7, 1500));

        potions.add(new Potion("Liquid Luck", 800, 7, 200, "Strength"));
        potions.add(new Potion("Calming Draught", 250, 1, 50, "Strength"));
        potions.add(new Potion("Ageing Potion", 400, 3, 100, "Dexterity"));
        potions.add(new Potion("Elixir of Life", 1000, 9, 350, "Agility"));

        spells.add(new IceSpell("Petrificus Totalus", 250, 2, 300, 100));
        spells.add(new IceSpell("Wingardium Leviosa", 150, 1, 250, 80));
        spells.add(new IceSpell("Alohomora", 100, 1, 250, 90));
        spells.add(new IceSpell("Diffindo", 650, 5, 400, 450));

        spells.add(new FireSpell("Expelliarmus", 300, 3, 350, 150));
        spells.add(new FireSpell("Immobulus", 350, 3, 400, 150));
        spells.add(new FireSpell("Obliviate", 400, 5, 500, 280));
        spells.add(new FireSpell("Crucio", 800, 9, 900, 550));

        spells.add(new LightningSpell("Stupefy", 600, 7, 600, 320));
        spells.add(new LightningSpell("Obliviate", 400, 5, 500, 280));
        spells.add(new LightningSpell("Imperio", 750, 9, 850, 500));
        spells.add(new LightningSpell("Avada Kedavra", 1111, 10, 11111, 999));

        props.addAll(weapons);
        props.addAll(armors);
        props.addAll(potions);
        props.addAll(spells);
    }

    public ArrayList<Prop> getProps() {
        return props;
    }

    /*
        The market can self-print all its stocks (weapons/armors/potions/spells).
     */
    public void printMarket() {
        NotificationCenter.marketMessages(1);

        // A line to split different kinds of items (i.e. weapons/armors/potions/spells)
        String splitLine = Colors.YELLOW_BG + Colors.BLACK +
                "--><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><--" +
                Colors.RESET + "\n";

        // Print weapons
        NotificationCenter.marketMessages(2);
        System.out.println("Name                  Price    Required Level    Base Damage    Required Hands");
        System.out.print(splitLine);

        for (int i = 0; i < weapons.size(); ++i) {
            // Print all the stats of all the weapons
            String name = (i + 1) + ". " + weapons.get(i).name;
            int price = weapons.get(i).cost;
            int level = weapons.get(i).requiredLevel;
            int damage = weapons.get(i).getBaseDamage();
            int hands = weapons.get(i).getRequiredHands();

            System.out.printf("%-22s %-14d %-14d %-17d %-5d", name, price, level, damage, hands);
            System.out.println();
        }
        System.out.println(splitLine);

        // Print armors
        NotificationCenter.marketMessages(3);
        System.out.println("Name                    Price    Required Level    Damage Reduction");
        System.out.print(splitLine);

        for (int i = 0; i < armors.size(); ++i) {
            // Print all the stats of all the armors
            String name = (i + 1) + weapons.size() + ". " + armors.get(i).name;
            int price = armors.get(i).cost;
            int level = armors.get(i).requiredLevel;
            int damageReduction = armors.get(i).getDamageReduction();

            System.out.printf("%-24s %-14d %-15d %-14d", name, price, level, damageReduction);
            System.out.println();
        }
        System.out.println(splitLine);

        // Print potions
        NotificationCenter.marketMessages(4);
        System.out.println("Name                    Price    Required Level    Increased Amount    Affected Attr");
        System.out.print(splitLine);

        for (int i = 0; i < potions.size(); ++i) {
            // Print all the stats of all the potions
            String name = (i + 1) + weapons.size() + armors.size() + ". " + potions.get(i).name;
            int price = potions.get(i).cost;
            int level = potions.get(i).requiredLevel;
            int increaseAttr = potions.get(i).getIncreasedAttr();
            String affectedAttr = potions.get(i).getAffectedAttr();

            System.out.printf("%-24s %-14d %-16d %-15d %-9s", name, price, level, increaseAttr, affectedAttr);
            System.out.println();
        }
        System.out.println(splitLine);

        // Print spells
        NotificationCenter.marketMessages(5);
        System.out.println("Name                    Price    Required Level    Base Damage    Mana Cost    Spell Type");
        System.out.print(splitLine);

        for (int i = 0; i < spells.size(); ++i) {
            // Print all the stats of all the spells
            String name = (i + 1) + weapons.size() + armors.size() + potions.size() + ". " + spells.get(i).name;
            int price = spells.get(i).cost;
            int level = spells.get(i).requiredLevel;
            int damage = spells.get(i).getBaseDamage();
            int manaCost = spells.get(i).getManaCost();
            String type;

            // Polymorphism implementation
            if (spells.get(i) instanceof IceSpell) {
                type = Colors.CYAN + "Ice" + Colors.RESET;
            } else if (spells.get(i) instanceof FireSpell) {
                type = Colors.RED + "Fire" + Colors.RESET;
            } else {
                type = Colors.GREEN + "Lightning" + Colors.RESET;
            }

            System.out.printf("%-24s %-14d %-13d %-14d %-9d %-16s", name, price, level, damage, manaCost, type);
            System.out.println();
        }
        System.out.println(splitLine);
    }
}
