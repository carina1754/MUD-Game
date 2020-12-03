package application;
import java.util.ArrayList;
import java.util.Random;

public class map {
    final int rows;
    final int clms;
    Cell[][] cells;
    int count = 0;
    int threadDuration = 0;

    public map(int rows, int clms) {
        this.rows = rows;
        this.clms = clms;
        cells = new Cell[rows][clms];

        // Generate cells
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(j, i);
    }

    public map(int rows, int clms, int threadDuration){
        this(rows, clms);
        this.threadDuration = threadDuration;
    }

    public void create() {
        var startTime = System.currentTimeMillis();
        System.out.println(String.format("generation started for %d x %d Maze", rows, clms));
        generate(cells[0][0]);
        var endTime = System.currentTimeMillis();
        System.out.println("\nMaze Generated");
        System.out.println("Took " + (endTime - startTime) + " milliseconds");
    }

    void generate(Cell curntCell) {
        Random rand = new Random();
        curntCell.visit();
        count++;
        System.out.print(String.format("\r%d%% Complete (%d/%d)", count * 100 / (rows * clms), count, rows * clms));
        try {
            Thread.sleep(threadDuration);
        } catch (InterruptedException e) {
        }

        ArrayList<Cell> nextCells = getUnvisitedNeighbours(curntCell);
        while(nextCells.size() > 0){
            // pick an unvisited neighbour from the list by random
            // double checks if it's visited since it could've been visited after we generated the list
            // if it's visited: remove it from the list

            var nextIndx = rand.nextInt(nextCells.size());
            if(!nextCells.get(nextIndx).isVisited){
                curntCell.openWith(nextCells.get(nextIndx));
                generate(nextCells.get(nextIndx));
                nextCells.remove(nextIndx);
            }else
                nextCells.remove(nextIndx);
        }
    }

    /**
     * Returns an ArrayList of all the unvisited neighbours of cell
     */ 
    ArrayList<Cell> getUnvisitedNeighbours(Cell cell){
        ArrayList<Cell> output = new ArrayList<Cell>();

        for (int i = 0; i < 4; i++) {
            // i/2 == 0? + : -
            // i%2 == 0? y : x
            int cX = i%2 == 0? cell.x: i/2 == 0? cell.x+1: cell.x-1;
            int cY = i%2 != 0? cell.y: i/2 == 0? cell.y+1: cell.y-1;
            try {
                if(!cells[cY][cX].isVisited)
                output.add(cells[cY][cX]);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return output;
    }

}

class Cell {
    final int x;
    final int y;
    boolean isVisited = false;
    boolean[] walls = {false, false, false, false};

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void visit(){
        isVisited = true;
    }

    void openWith(Cell otherCell){
        // figuring out the otherCell position to this cell
        if(otherCell.y == this.y){
            switch (otherCell.x - this.x) {
                case -1: 
                    walls[3] = true;
                    otherCell.walls[1] = true;
                    break;
                case 1: 
                    walls[1] = true;
                    otherCell.walls[3] = true;
                    break;
                default: 
                    // cell isn't a neighbour
                    return;
            }
        }else if(otherCell.x == this.x){
            switch (otherCell.y - this.y) {
                case -1: 
                    walls[0] = true;
                    otherCell.walls[2] = true;
                    break;
                case 1:
                    walls[2] = true;
                    otherCell.walls[0] = true;
                    break;
                default: 
                    // cell isn't a neighbour
                    return;
            }
        }else{
            // cell isn't a neighbour
            return;
        }
    }
    
}
