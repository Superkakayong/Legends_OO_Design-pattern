/**
 * This class represents a market cell, which belongs to a specific type of map cells.
 * Therefore, it inherits from the Cell class.
 */
public class MarketCell extends Cell{
    public MarketCell() {
        super(Colors.YELLOW_BG + Colors.CYAN + " $ " + Colors.RESET);
    }
}
