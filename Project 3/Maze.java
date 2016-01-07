import java.util.Random;
// No other import statement is allowed

public class Maze
{
	// TO DO: Instance Variable
    int w;
    int h;

    boolean[][] hWalls;
    boolean[][] vWalls;

    Random r;

	/**
	 * Constructor
	 * @param aWidth the number of chambers in each row
	 * @param aHeight the number of chamber in each column
	 */
	public Maze(int aWidth, int aHeight)
	{
		// TO DO: Constructor
        w=aWidth;
        h=aHeight;
        hWalls = new boolean[h - 1][w];
        vWalls = new boolean[w - 1][h];

        r = new Random();

        genMaze(0, w, h, 0);


	}
    public void genMaze(int topBound, int rightBound,int bottomBound, int leftBound)
    {
        if (bottomBound - topBound <= 1 || rightBound - leftBound <= 1) {
            return;
        }

        int row = r.nextInt(bottomBound - topBound - 1) + topBound;
        int col = r.nextInt(rightBound - leftBound - 1) + leftBound;

        for (int i = leftBound; i < rightBound; i++)
            hWalls[row][i] = true;
        for (int i = topBound; i < bottomBound; i++)
            vWalls[col][i] = true;

        int wall = r.nextInt(4);
        int hole;
        if (wall != 0) {
            hole = r.nextInt(row - topBound + 1) + topBound;
            vWalls[col][hole] = false;
        }
        if (wall != 1) {
            hole = r.nextInt(rightBound - col - 1) + col + 1;
            hWalls[row][hole] = false;
        }
        if (wall != 2) {
            hole = r.nextInt(bottomBound - row - 1) + row + 1;
            vWalls[col][hole] = false;
        }
        if (wall != 3) {
            hole = r.nextInt(col - leftBound + 1) + leftBound;
            hWalls[row][hole] = false;
        }
        //Top Left
        genMaze(topBound,col+1,row+1,leftBound);
        //Top Right
        genMaze(topBound,rightBound,row+1,col+1);
        //Bottom Left
        genMaze(row+1,col+1,bottomBound,leftBound);
        //Bottom Right
        genMaze(row+1,rightBound,bottomBound,col+1);
    }

    /**
	 * getWidth
	 * @return the width of this maze
	 */

	public int getWidth()
	{
        return w;
	}
	
	/**
	 * getHeight
	 * @return the height of this maze
	 */
	public int getHeight()
	{
        return h;
	}
	
	/**
	 * isNorthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a north wall. Otherwise, return false
	 */
	public boolean isNorthWall(int row, int column)
    {
        return row == 0 || hWalls[row - 1][column];
    }
	
	/**
	 * isEastWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain an east wall. Otherwise, return false
	 */
	public boolean isEastWall(int row, int column)
	{
        return column == w - 1 || vWalls[column][row];
    }

	
	/**
	 * isSouthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a south wall. Otherwise, return false
	 */
	public boolean isSouthWall(int row, int column)
	{
        return row == h - 1 || hWalls[row][column];
    }

	
	/**
	 * isWestWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a west wall. Otherwise, return false
	 */
	public boolean isWestWall(int row, int column)
	{
        return column == 0 || vWalls[column - 1][row];
    }
}
