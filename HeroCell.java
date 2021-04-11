/**
 * This class represents a hero cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.
 */
public class HeroCell extends Cell{
    public HeroCell() {
        super(Colors.RED + " † " + Colors.RESET);
    }
}
