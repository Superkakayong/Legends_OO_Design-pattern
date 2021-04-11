/**
 * This class is the super class of all RPG games.
 * It also inherits from the Game class, which is the top super class of all games.
 */
public abstract class RPGGame extends Game {
    public RPGGame() {}

    /*
        Display welcome messages and relevant game instructions.
        Get the necessary information from the players.
     */
    public abstract void prepare();

    /*
        Print the stats of all the heroes in the team ArrayList.
     */
    public abstract void printTeamMembers();
}
