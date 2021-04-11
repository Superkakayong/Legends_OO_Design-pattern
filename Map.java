import java.util.Random;

/**
 * This class represents the game map.
 */
public class Map {
    private int size;
    private Cell[][] map;
    private Cell originalCell; // To recover the previous cell after the hero team leaves

    public Map(int size) {
        this.size = size;
        this.map = new Cell[size][size];
        mapInitialization();
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getMap() {
        return map;
    }

    public Cell getOriginalCell() {
        return originalCell;
    }

    private void mapInitialization() {
        Random seed = new Random();

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == 0 && j == 0) {
                    map[i][j] = new HeroCell();
                    continue;
                }

                if (i < 3 && j < 3) {
                    if (i == 0 && j == 1) {
                        // Force a market at cell in row 0 col 1
                        // To let heroes equip themselves at the beginning
                        map[i][j] = new MarketCell();
                        continue;
                    }

                    // Prevent heroes from being stuck at the beginning
                    map[i][j] = new CommonSpaceCell();
                    continue;
                }

                int temp = seed.nextInt(100) + 1;

                if (1 <= temp && temp <= 20) {
                    map[i][j] = new InaccessibleCell();
                } else if (21 <= temp && temp <= 75) {
                    map[i][j] = new CommonSpaceCell();
                } else {
                    map[i][j] = new MarketCell();
                }
            }
        }
    }

    /*
        Print the map.
     */
    public void printMap() {
        System.out.println(Colors.PURPLE_BG + Colors.BLACK +" World Map: " + Colors.RESET);

        StringBuilder topBottomBoundary = new StringBuilder("");

        for (int i = 0; i < this.size; ++i) {
            // Create the top and bottom split boundary
            topBottomBoundary.append("+---");
        }
        topBottomBoundary.append("+");

        for (Cell[] row : map) {
            // For each row in the map
            System.out.println(topBottomBoundary);

            for (Cell cell : row) {
                // For each cell in the row
                System.out.print("|" + cell.getMarker() + "");
            }
            System.out.println("|");
        }
        System.out.println(topBottomBoundary);
        System.out.println();
    }

    /*
        Special case: when the hero team leaves its starting cell (i.e. Cell [0, 0]),
        we manually set it to be a COMMON AREA cell.

        Since the marker of the starting cell is already the hero team marker,
        the "originalCell" will also be a HeroCell. Then once the hero team leaves,
        there will be TWO hero team markers, which is not our intention.
     */
    public void setStartingCell() {
        map[0][0] = new CommonSpaceCell();
    }

    /*
        When the hero team visits a specific cell, covers it with a HeroCell
     */
    public void setHeroCell(int row, int col) {
        originalCell = map[row][col];
        map[row][col] = new HeroCell();
    }

    /*
        After the hero team leaves a specific cell, recovers it with its original cell
     */
    public void recoverCell(int row, int col) {
        map[row][col] = originalCell;
    }
}
