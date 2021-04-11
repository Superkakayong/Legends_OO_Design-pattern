/**
 * This class represents an inaccessible cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.
 */
public class InaccessibleCell extends Cell{
    public InaccessibleCell() {
        super(Colors.BLACK_BG + Colors.WHITE + " X " + Colors.RESET);
    }
}
