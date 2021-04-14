<h1 align = "center">Legends: Heroes and Monsters</h1>

---

> ## Background
>
> **What's up guys!**
>
> In this project, I have created an interesting and colorful RPG game called Legends: **Heroes and Monsters**. The **core** of this project is its **object-oriented design** and **design pattern implementation**. There are **40 classes** in total, which renders the whole project of high **scalability** and **extendability!**

---

## Examples of Gaming Experience

<img src="https://github.com/Superkakayong/Trivia/blob/master/Project_Images/Legends/Legends.jpeg" alt="image-20210210200440573" style="zoom:90%; float: left" />

---

## General workflow of the game

1. Ask the players if they want to start or quit the game.
2. If the players choose to play, the game will display welcome messages and relevant instructions.
3. Get the team's information (e.g. number of desired heroes).
4. Let the players select heroes for every hero headcount in the team.
5. Enter the game map. The players can use **w/a/s/d/i** to perform corresponding actions.
6. The hero team can enter a market in the map to buy/sell props.
7. The restricted areas are not allowed to be visited by the hero team.
8. There is 50% chance for the hero team to encounter monsters in a common space cell.
9. The hero team will fight monsters in the common space cell. A fight contains multiple rounds.
10. After the fight, the game will calculate the results and update related statistics.
11. If there is at least one hero alive, all fainted heroes can be revived and the game continues. The hero team can keep travelling in the game map.
12. If all heroes have fainted, the game will automatically end.

---

## Classes of the project (Recommended Viewing Order)

### 1. GameEntrance.java

- This class serves as the entrance of the entire project.
- Only this class has the main() method.

### 2. NotificationCenter.java

- This class serves as a center that stores all the messages during the game.
- So we don't need to write a lot of similar print statements in other classes!

### 3. Colors.java

- This class stores all the colors that will be used in this project.

### 4. Trade.java

- This interface guarantees the ability of a class that implements it can be traded (i.e. can be bought and sold).

###  5. Props.Java

- This class represents all props in the game.
- It is the super class of all kinds of props.
- It inherits from the **Trade** interface to guarantee that a prop can be bought and sold.

### 6. CauseDamage.java

- This interface serves as a contract to the user that if a class implements it, the class should be able to inflict other "classes" (i.e. players).

###  7. Weapon.java

- This class represents the weapon, which is a type of props.
- Since a weapon can be used to inflict the enemies, this class also implements the CauseDamage interface.

###  8. Armor.java

* This class manages the properties of an armor.
 * It also extends from the class Prop since an armor is a prop.

###  9. Potion.java

- This class represents the potion, which is a type of props.

###  10. Spell.java

* This class represents the potion, which is a type of props.
 * Since a spell can be used to inflict the enemies, this class also implements the CauseDamage interface.

###  11. FireSpell.java

- This class stands for the fire spell, which is a specific type of spells.

###  12. IceSpell.java

- This class stands for the ice spell, which is a specific type of spells.

###  13. LightningSpell.java

- This class stands for the lightning spell, which is a specific type of spells.

### 14. Role.java

- This class represents a role in the RPG games.
- In this game, both Hero and Monster have inherited from this class.

### 15. Fight.java

 * This interface serves as a contract to the user that if a class implements it, the class should be able to fight.
 * In this project, both the Hero class and Monster class have implemented this interface.

### 16. Monster.java

* This class manages all the common properties of all types of monsters.
 * It inherits from the Role class, and implements the Fight interface.
 * It is the super class for all specific monster classes.

### 17. Dragon.java

* This class stands for a dragon monster.
 * It inherits from the monster class.

### 18. Exoskeleton.java

*  This class stands for a exoskeleton monster.
 *  It inherits from the monster class.

### 19. Spirit.java

* This class stands for a spirit monster.
 * It inherits from the monster class.

### 20. MonsterList.java

* This class stores all the monsters in the game.

### 21. Hero.java

 * This class manages all the common properties of all types of heroes.
 * It inherits from the Role class, and implements the Fight interface.
 * It is the super class for all specific hero classes.

### 22. Paladin.java

* This class stands for the hero Paladin, which is a hero type.

### 23. Sorcerer.java

* This class stands for the hero Sorcerer, which is a hero type.

### 24. Warrior.java

* This class stands for the hero Warrior, which is a hero type.

### 25. HeroList.java

- This class stores all the heroes in this game.

### 26. HeroTeam.java

- This class manages the operations of a team of heroes during the game.

### 27. LevelUpBehavior.java

* Since the algorithms for the level up behavior of different types of heroes vary, I have implemented the Strategy Pattern to facilitate the level up operations for heroes.

### 28. PaladinLevelUp.java

 * This class holds the algorithms for the level up behavior of hero Paladin.
 * Implementation of the Strategy Pattern!

### 29. SorcererLevelUp.java

* This class holds the algorithms for the level up behavior of hero Sorcerer.
 * Implementation of the Strategy Pattern!

### 30. WarriorLevelUp.java

* This class holds the algorithms for the level up behavior of hero Warrior.
 * Implementation of the Strategy Pattern!

### 31. Map.java

- This class represents the game map.

### 32. Cell.java

- This class represents a map cell of the map.

### 33. CommonSpaceCell.java

- This class represents a common space cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.

### 34. HeroCell.java

* This class represents a hero cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.

### 35. InaccessibleCell.java

- This class represents an inaccessible cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.

### 36. MarketCell.java

 * This class represents a market cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.

### 37. Market.java

- This class stores all the props in the market of the game.

### 38. Game.java

- This class is the top super class for all types of games.

### 39. RPGGame.java

 * This class is the super class of all RPG games.
 * It also inherits from the Game class, which is the top super class of all games.

### 40. LegendsGame.java

* This is the main class of the project!
 * It controls all the game logics.
 * It is a role playing game, therefore it inherits from the RPGGame class.

---

## Instructions on how to compile and run the program via Mac Terminal

1. Create a new folder on your MacBook and copy-paste all the 40 classes mentioned above to that folder.
2. Open Terminal and type "cd " (notice there is a  **whitespace** after "cd" !).
3. Then **drag the folder** to the terminal so that Mac can automatically complete the directory of that folder for you.
4. Press "Enter". Now you are inside the newly created folder.
5. Type "javac GameEntrance.java" in the terminal.
6. Type "java GameEntrance" in the terminal.
7. Now you should be able to play the game through Mac Terminal.
8. Please note that you should have a **JDK** installed in you MacBook with version at least **1.8**. 
9. Since I am using **[ANSI escape code/sequence]** to display colors, there are chances that **Windows-based** machines can fail to render the colors. However, if you are using a Unix-based machine, things will work just fine ^.

---

## Instructions on how to compile and run the program in the IDE Console

1. Create a new JAVA project in IntelliJ IDEA CE.
2. Copy-paste all the 40 classes mentioned above to the **/src** folder.
3. Click the "Run" button or press Control+R to run the project.
4. Please note that you should have a **JDK** installed in you MacBook with version at least **1.8**. 
5. Since I am using **[ANSI escape code/sequence]** to display colors, there are chances that **Windows-based** machines can fail to render the colors. However, if you are using a Unix-based machine, things will work just fine ^.

---

## * Highlights of the Project

### 1. Colorful and lively terminal (console) experience

- I have implemented [ANSI escape code/sequence] to display a lot of messages and words during the game.

### 2. Tons of ASCII Arts

- I have implemented a lot of ASCII arts during the game to make it more graphic and vivid.

### 3. Use of Design Pattern

- Since I found that the algorithms for the level up behavior of different types of heroes vary, I have implemented the Strategy Pattern to facilitate the level up operations for heroes.

### 4. Proper code format

- Proper indentations, **very** **detailed** comments, etc.

---

## Things Worth Noting

### 1. Rules about endding the game

- If after a fight and all heroes are fainted, then the game will automatically end.

### 2. Rules about heroes statistics after each round of a fight

- To make the game more realistic and fair, the heroes will not regain any HP or mana after each round of a fight. 
- In other words, once a hero is in a fight, all her/his statistics will either decrease or remain unchanged.

### 3. Rules about heroes statistics after an entire fight

- After an entire fight, the HP and mana of all **SURVIVED** heroes will be reset to their initial values. 
- They will also receive **100 * hero_level**  money bonus and **5 * hero_level** EXP bonus.
- After an entire fight, the HP and mana of all **FAINTED** heroes will be reset to **half** of their initial values. 
- They will receive no money bonus or EXP bonus.

### 4. Rules about Level Up

- The heroes can level up if they have accumulated **10 * hero_level** EXP bonus.
- Therefore, a hero can level up if (s)he can survive for **two** complete fights (because **5 * hero_level** EXP bonus for one successful fight).

---

