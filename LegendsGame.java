import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main class of the project!
 * It controls all the game logics.
 * It is a role playing game, therefore it inherits from the RPGGame class.
 */
public class LegendsGame extends RPGGame {
    private int numOfHeroes;
    private int row; // Row position of the hero
    private int col; // Column position of the hero

    private ArrayList<Hero> team; // The hero team
    private ArrayList<Monster> monsters; // The monster squad
    private ArrayList<Hero> faintedHeroes; // All fainted heroes
    private ArrayList<Hero> resetHeroes; // For resetting the heroes after a fight
    private Map map; // The game map
    private Market market; // The market in the market cell

    public LegendsGame() {
        numOfHeroes = 0;
        row = 0;
        col = 0;

        team = new ArrayList<>();
        monsters = new ArrayList<>();
        faintedHeroes = new ArrayList<>();
        resetHeroes = new ArrayList<>();
        map = new Map(8);
        market = new Market();
    }

    /*
        This is the CORE method of this class (or the entire project).
        It is very concise and easy to understand, because the code is highly modularized!
     */
    @Override
    public void play() {
        prepare();
        formHeroTeam();
        printTeamMembers();
        formResetHeroes();

        NotificationCenter.mapRelated(1);
        map.printMap();

        while (true) {
            // Keep playing the game until the user chooses to quit or all heroes in the team have fainted
            boolean shouldVisit = move();
            map.printMap();

            if (shouldVisit) {
                // Prevent the visit of a cell when the player enters 'i/I' in the move() method
                visit();
                map.printMap();
            }

            map.recoverCell(row, col);
        }
    }

    /*
        Display welcome messages and relevant game instructions.
        Get the desired number of heroes from the players (in order to form the heroes team).
     */
    @Override
    public void prepare() {
        NotificationCenter.welcome();
        NotificationCenter.playOrQuit(1);

        Scanner sc = new Scanner(System.in);
        String decision = sc.nextLine();

        // Check if the player wants to start the game or quit
        if (decision.equalsIgnoreCase("Q")) {
            quit();
        } else {
            NotificationCenter.playOrQuit(3);
        }

        // Get the desired heroes number
        NotificationCenter.heroesInfo(1);
        while (true) {
            if (sc.hasNextInt()) {
                numOfHeroes = sc.nextInt();

                if (numOfHeroes < 1 || numOfHeroes > 3) { NotificationCenter.heroesInfo(2); }
                else { break; }

            } else {
                sc.next();
                NotificationCenter.heroesInfo(3);
            }
        }
    }

    /*
        Form the hero team (1/2/3 heroes).
     */
    public void formHeroTeam() {
        Scanner sc = new Scanner(System.in);
        HeroTeam temp = new HeroTeam();
        HeroList.printHeroList();

        for (int i = 1; i <= numOfHeroes; ++i) {
            // Get the user desired hero for every hero headcount
            NotificationCenter.formHeroTeam(1, i);
            int choice;

            // Input validation checking
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();

                    if (choice < 1 || choice > HeroList.getList().size()) {
                        NotificationCenter.formHeroTeam(2, i);
                    } else {
                        temp.addMembers(HeroList.getList().get(choice - 1));
                        break;
                    }
                } else {
                    sc.next();
                    NotificationCenter.formHeroTeam(3, i);
                }
            }

            team = temp.getTeam();
        }
    }

    /*
        Initialize the resetHero ArrayList to contain exactly the same information as the initial team ArrayList.
        This is for the purpose of resetting and updating the stats of the heroes (e.g. HP, mana) after the fight.
     */
    public void formResetHeroes() {
        for (Hero hero : team) {
            resetHeroes.add(new Hero(hero.name, hero.level, hero.mana,
                    (int)hero.strength, (int)hero.dexterity, (int)hero.agility, hero.money, hero.exp));
        }
    }

    /*
        Override the printTeamMembers() method from the RPGGame class.
        Print the stats of all the heroes in the team ArrayList.
     */
    @Override
    public void printTeamMembers() {
        if (team.isEmpty()) { return; }

        NotificationCenter.teamInfo(1);

        String name;
        double hp, strength, dexterity, agility, money, mana;
        int level, exp;

        String splitLine = Colors.YELLOW +
                "--><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><--" +
                Colors.RESET + "\n";

        System.out.println("Name                       HP    Level    Mana    Strength    Dexterity    Agility    Money    Exp");
        System.out.print(splitLine);

        for (int i = 0; i < team.size(); ++i) {
            // Print all the team members together with their stats
            name = (i + 1) + ". " + team.get(i).name;
            hp = team.get(i).hp;
            level = team.get(i).level;
            mana = team.get(i).mana;
            strength = team.get(i).strength;
            dexterity = team.get(i).dexterity;
            agility = team.get(i).agility;
            money = team.get(i).money;
            exp = team.get(i).exp;

            System.out.printf("%-25s %-8.0f %-6d %-9.0f %-12.0f %-11.0f %-9.0f %-8.0f %-5d", name, hp, level, mana, strength,
                    dexterity, agility, money, exp);
            System.out.println();
        }
        System.out.println(splitLine);
    }

    /*
        Initialize the monster squad (same size as the hero team).
     */
    public void initializeMonsters() {
        MonsterList list = new MonsterList();
        Random seed = new Random();

        for (int i = 0; i < team.size(); ++i) {
            // This guarantees: number of heroes = number of monsters, and
            // the monsters are randomly selected from the monster list
            int monsterIndex = seed.nextInt(list.getMonsters().size());
            monsters.add(list.getMonsters().get(monsterIndex));
        }
    }

    /*
        Print the stats of all the monsters in the monster squad.
     */
    public void printMonsters() {
        if (monsters.isEmpty()) { return; }

        NotificationCenter.monstersInfo(1);

        String name;
        double hp, damage, defenseStat, dodgeChance;
        int level;

        String splitLine = Colors.YELLOW +
                "--><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><-><--" +
                Colors.RESET + "\n";

        System.out.println("Name                       HP    Level    Damage    Defense    Dodge Chance");
        System.out.print(splitLine);

        for (int i = 0; i < monsters.size(); ++i) {
            // Print all the monsters together with their stats
            name = (i + 1) + ". " + monsters.get(i).name;
            hp = monsters.get(i).hp;
            level = monsters.get(i).level;
            damage = monsters.get(i).damage;
            defenseStat = monsters.get(i).defenseStat;
            dodgeChance = monsters.get(i).dodgeChance;

            System.out.printf("%-25s %-8.0f %-7d %-9.0f %-11.0f %-6.2f", name, hp, level, damage, defenseStat, dodgeChance);
            System.out.println();
        }
        System.out.println(splitLine);
    }

    /*
        Let the user travel in the map using wW/aA/sS/dD, and print information using iI.
     */
    public boolean move() {
        Scanner sc = new Scanner(System.in);
        String action;
        int size = map.getSize();

        while (true) {
            NotificationCenter.mapRelated(2);
            action = sc.nextLine();

            if (!action.equalsIgnoreCase("W") && !action.equalsIgnoreCase("A") &&
                    !action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("D") &&
                    !action.equalsIgnoreCase("Q") && !action.equalsIgnoreCase("I")) {

                // Invalid input (i.e. not a/w/s/d/q/i)
                NotificationCenter.mapRelated(3);
                continue;
            }

            if ((action.equalsIgnoreCase("A") && (col - 1 < 0)) ||
                    (action.equalsIgnoreCase("D") && (col + 1 >= size)) ||
                    (action.equalsIgnoreCase("W") && (row - 1 < 0)) ||
                    (action.equalsIgnoreCase("S") && (row + 1 >= size))) {

                // Valid input, but cannot go outside of the map
                NotificationCenter.mapRelated(4);
                continue;
            }

            if ((action.equalsIgnoreCase("A") && (map.getMap()[row][col - 1] instanceof InaccessibleCell)) ||
                    (action.equalsIgnoreCase("D") && (map.getMap()[row][col + 1] instanceof InaccessibleCell)) ||
                    (action.equalsIgnoreCase("W") && (map.getMap()[row - 1][col] instanceof InaccessibleCell)) ||
                    (action.equalsIgnoreCase("S") && (map.getMap()[row + 1][col] instanceof InaccessibleCell))) {

                // Valid input, but cannot move to an Inaccessible Cell
                NotificationCenter.mapRelated(5);
            } else { break; }
        }

        // Valid input, and legal move
        int startRowPos = row, startColPos = col;

        if (action.equalsIgnoreCase("A")) { --col; }
        else if (action.equalsIgnoreCase("D")) { ++col; }
        else if (action.equalsIgnoreCase("W")) { --row; }
        else if (action.equalsIgnoreCase("S")) { ++row; }

        if (startRowPos == 0 && startColPos == 0) {
            // Special case: when the hero team leaves its starting cell.
            // Detailed explanation is in the setCellZeroZero() method of the Map class.
            map.setStartingCell();
        }

        map.setHeroCell(row, col); // Set the cell that the hero team moves to

        if (action.equalsIgnoreCase("Q")) { quit(); } // Quit the game
        if (action.equalsIgnoreCase("I")) {
            // Print relevant stats
            printTeamMembers();
            return false;
        }

        return true;
    }

    /*
        After the hero moves to a specific cell, visit that cell.
     */
    public void visit() {
        // If the cell is a Market cell, go and visit the market
        if (map.getOriginalCell() instanceof MarketCell) { visitMarket(); }

        // If the cell is a Common Space cell, flip a coin to decide if there is going to be a monster
        if (map.getOriginalCell() instanceof CommonSpaceCell) {
            Random seed = new Random();
            int createMonster = seed.nextInt(2);

            // 50% chance to come across monsters and begin to fight
            if (createMonster == 1) { fight(); }
        }
    }

    /*
        All possible operations when the hero team is visiting a market.
     */
    public void visitMarket() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < team.size(); ++i) {
            // Iterate through all the heroes and ask for their actions (i.e. buy/sell/pass)
            NotificationCenter.visitMarket(1, i + 1);
            printTeamMembers();
            NotificationCenter.visitMarket(2, i + 1);

            int choice; // Get the hero's action choice

            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();

                    // There are only 3 options for the hero (i.e. buy/sell/pass)
                    if (choice < 1 || choice > 3) { NotificationCenter.visitMarket(3, i + 1); }
                    else { break; }
                } else {
                    // If input is not an INTEGER
                    sc.next();
                    NotificationCenter.visitMarket(4, i + 1);
                }
            }

            if (choice == 1) {
                // The hero chooses to buy a prop
                market.printMarket();

                // Successfully bought the prop, print this hero's private inventory
                if (team.get(i).buy(chooseAPropFromMarket())) { team.get(i).printInventory(); }

                // Let the hero choose her/his option again (i.e. buy/sell/pass)
                --i;
            } else if (choice == 2) {
                // The hero chooses to sell a prop
                team.get(i).printInventory();
                int propIndex = chooseAPropFromInventory(team.get(i));

                if (propIndex != Integer.MIN_VALUE) {
                    // Successfully sold the prop, print this hero's private inventory
                    team.get(i).sell(propIndex);
                    team.get(i).printInventory();
                }

                // Let the hero choose her/his option again (i.e. buy/sell/pass),
                // because the hero can buy/sell multiple times in the market
                --i;
            } else {
                // The hero chooses to pass
                if (i == team.size() - 1) {
                    // If it is the last hero, display the "Goodbye" message of the market
                    NotificationCenter.marketMessages(6);
                }
            }
        }
    }

    /*
        A hero can choose her/his desired props from the market.
     */
    public Prop chooseAPropFromMarket() {
        Scanner sc = new Scanner(System.in);

        // Input validation checking
        while (true) {
            NotificationCenter.chooseAProp(1);
            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();

                if (choice < 1 || choice > market.getProps().size()) {
                    NotificationCenter.chooseAProp(2);
                } else { return market.getProps().get(choice - 1); }
            } else {
                sc.next();
                NotificationCenter.chooseAProp(3);
            }
        }
    }

    /*
        A hero can choose props from her/his private inventory.
     */
    public int chooseAPropFromInventory(Hero hero) {
        if (hero.props.isEmpty()) {
            // The hero's inventory is empty
            NotificationCenter.chooseAProp(6);
            return Integer.MIN_VALUE;
        }

        Scanner sc = new Scanner(System.in);

        // Input validation checking
        while (true) {
            NotificationCenter.chooseAProp(4);
            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();

                if (choice < 1 || choice > hero.props.size()) {
                    NotificationCenter.chooseAProp(5);
                } else { return choice; }
            } else {
                sc.next();
                NotificationCenter.chooseAProp(3);
            }
        }
    }

    /*
        The hero team can fight the monster squad.
     */
    public void fight() {
        Scanner sc = new Scanner(System.in);

        NotificationCenter.fight(1, "", "");

        initializeMonsters();

        while (true) {
            int hIndex = 0, mIndex = 0; // Index of the hero and index of the monster

            for (; hIndex < team.size() && mIndex < monsters.size(); ++hIndex) {
                // The hero fights her/his corresponding monster
                printTeamMembers();
                printMonsters();

                String heroName = team.get(hIndex).name;
                String monsterName = monsters.get(mIndex).name;

                NotificationCenter.fight(2, heroName, monsterName);
                team.get(hIndex).printInventory();

                int choice;

                // Get the hero's action choice (i.e. attack/cast a spell/use a potion/change an armor/change a weapon)
                while (true) {
                    NotificationCenter.fight(3, heroName, "");

                    if (sc.hasNextInt()) {
                        choice = sc.nextInt();

                        if (choice < 1 || choice > 5) {
                            // Only 5 options available
                            NotificationCenter.fight(4, "", "");
                        } else { break; }
                    } else {
                        // Input is not an INTEGER
                        sc.next();
                        NotificationCenter.fight(5, "", "");
                    }
                }

                if (!performAction(choice, team.get(hIndex), monsters.get(mIndex))) {
                    // If failed to perform the action, let the hero choose the action again
                    --hIndex;
                    continue;
                }

                // Monster's turn to fight (if the monster is not dead already)
                if (!monsters.get(mIndex).isDead()) {
                    NotificationCenter.monsterAttack(2);
                    monsters.get(mIndex).attack(team.get(hIndex));
                }

                // Fight until either the hero survives or the monster survives :)
                if (team.get(hIndex).hasFainted() || monsters.get(mIndex).isDead()) {
                    if (team.get(hIndex).hasFainted()) {
                        faintedHeroes.add(team.remove(hIndex));
                        --hIndex;
                        ++mIndex;
                    } else { monsters.remove(mIndex); }

                    continue;
                }

                --hIndex; // If neither side is down, the hero needs to continue to fight
            }

            // If the fight has finished, break out of the loop
            if (hasFightFinished()) { break; }
        }


    }

    /*
        A hero can perform different actions during a round of the fight.
     */
    public boolean performAction(int choice, Hero hero, Monster monster) {
        if (choice == 1) {
            // Attack
            hero.attack(monster);
            return true;
        }
        else if (choice == 2) { return hero.castASpell(monster); } // Cast a spell
        else if (choice == 3) { return hero.useAPotion(); } // Use a potion
        else if (choice == 4) { return hero.changeAnArmor(); } // Change an armor
        else { return hero.changeAWeapon(); } // Change a weapon
    }

    /*
        Check if the fight has finished.
     */
    public boolean hasFightFinished() {
        if (team.isEmpty() && monsters.isEmpty()) {
            // All heroes have fainted, but all monsters are dead too. The whole game ends
            // Literally speaking, this situation could not happen, but just in case ^
            NotificationCenter.processStatus(3);
            printTeamMembers();
            quit();
        } else if (team.isEmpty()) {
            // All heroes have been fainted, and monsters still exist. The WHOLE GAME ends
            // Notice that NO hero will be revived if ALL heroes are down after the fight!
            NotificationCenter.processStatus(1);
            printTeamMembers();
            quit();
        } else if (monsters.isEmpty()) {
            // All monsters are dead, and heroes still exist. The hero team wins. The FIGHT ends
            NotificationCenter.processStatus(2);
            rewardAndReviveAfterFight();
            printTeamMembers();
        } else {
            // There still exist at least one hero and one monster, the fight should continue
            return false;
        }

        return true;
    }

    /*
        Reward and revive the heroes after a fight.
     */
    public void rewardAndReviveAfterFight() {
        NotificationCenter.reward(1);

        for (Hero hero : team) {
            for (Hero resetHero : resetHeroes) {
                // For all heroes who are still ALIVE after the fight,
                // set their HP and mana to be their initial values
                if (hero.name.equalsIgnoreCase(resetHero.name)) {
                    hero.hp = resetHero.hp;
                    hero.mana = resetHero.mana;
                    break;
                }
            }

            // For all heroes who are still ALIVE after the fight, reward them with extra money and EXP
            hero.money += 100 * hero.level;
            hero.setExpBonus(5 * hero.level);

            // If the hero is qualified to level up, let it.
            // Otherwise the performLevelUp() method will just do nothing
            hero.performLevelUp();
        }

        if (!team.isEmpty()) {
            // If not all heroes have fainted, revive all fainted heroes (otherwise the game ends).
            Iterator<Hero> iterator = faintedHeroes.iterator();

            while (iterator.hasNext()) {
                // Iterate through all fainted heroes in the faintedHeroes ArrayList
                Hero hero = iterator.next();

                for (Hero resetHero : resetHeroes) {
                    // Revive all fainted heroes with half of their initial HP and mana, and no money or HP rewarded
                    if (hero.name.equalsIgnoreCase(resetHero.name)) {
                        hero.hp = resetHero.hp * 0.5;
                        hero.mana = resetHero.mana * 0.5;
                        break;
                    }
                }

                team.add(hero); // Add the fainted hero back to the hero team (i.e. revive)
                iterator.remove(); // Remove the fainted hero from faintedHeroes
            }
        }
    }

    /*
        The user can quit the game.
     */
    @Override
    public void quit() {
        printTeamMembers();
        NotificationCenter.playOrQuit(2);
        System.exit(0);
    }
}
