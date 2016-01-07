import javax.swing.*;
import java.util.ArrayList;

public class MazeFrame
{
	public static void main(String[] args) throws InterruptedException
	{
		int width = 15;
		int height = 15;
		JFrame frame = new JFrame();
		Maze maze = new Maze(width, height);
		ArrayList<Pair<Integer,Integer>> solution = new ArrayList<Pair<Integer,Integer>>();
		MazeComponent mc = new MazeComponent(maze, solution);
		frame.setSize(800,800);
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mc);
		frame.setVisible(true);
		
		solution.add(new Pair<Integer,Integer>(0,0));
		Thread.sleep(1000);
		solveMaze(solution, mc, maze,0,0,new Pair(-1,-1),2,0);
		mc.repaint();
        System.out.println("Done!!");
	}
	
	/** Solve Maze: recursively solve the maze
	 * 
	 * @param solution   : The array list solution is needed so that every recursive call,
	 *                     a new (or more) next position can be added or removed.
	 * @param mc         : This is the MazeComponent. We need that only for the purpose of
	 *                     animation. We need to call mc.repaint() every time a new position
	 *                     is added or removed. For example,
	 *                       :
	 *                     solution.add(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 *                     solution.remove(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 * @param maze       : The maze data structure to be solved. 
	 * @return a boolean value to previous call to tell the previous call whether a solution is
	 *         found.
	 * @throws InterruptedException: We need this because of our Thread.sleep(50);
	 */
	public static boolean solveMaze(ArrayList<Pair<Integer,Integer>> solution, MazeComponent mc, Maze maze, int xPos, int yPos,  Pair prevLoc, int prevDirection, int nextD) throws InterruptedException {
        // TO DO
        //Move North = solveMaze(solution, mc, maze,xPos-1,yPos,loc);
        //Move South = solveMaze(solution, mc, maze,xPos+1,yPos,loc);
        //Move East = solveMaze(solution, mc, maze,xPos,yPos+1,loc);
        //Move West = solveMaze(solution, mc, maze,xPos,yPos-1,loc);
        //If north
        if(xPos==0&&yPos==0)
        {
            if(!maze.isEastWall(xPos,yPos))
                nextD=1;
            else
                nextD=2;
        }
        System.out.println(nextD);
        Pair loc = new Pair(xPos, yPos);
        solution.add(loc);
        System.out.println(loc);
        mc.repaint();
        Thread.sleep(100);
        if(xPos==maze.getHeight()-1 && yPos==maze.getWidth()-1)
            return true;

        if(!maze.isNorthWall(xPos,yPos) && !loc.toString().equals(prevLoc.toString())&& prevDirection!=2 &&nextD==0)
        {
            if(solveMaze(solution, mc, maze,xPos-1,yPos,loc,0,0))
                return true;

        }
        if(nextD==0)
            nextD++;
        if(!maze.isEastWall(xPos,yPos) && !loc.toString().equals(prevLoc.toString())&&prevDirection!=3 && nextD==1)
        {
            if(solveMaze(solution, mc, maze,xPos,yPos+1,loc,1,0))
                return true;

        }
        if(nextD==1)
            nextD++;
        if(!maze.isSouthWall(xPos,yPos) && !loc.toString().equals(prevLoc.toString())&& prevDirection!=0 && nextD==2)
        {
            if(solveMaze(solution, mc, maze,xPos+1,yPos,loc,2,0))
                return true;

        }
        if(nextD==2)
            nextD++;
        if(!maze.isWestWall(xPos,yPos) && !loc.toString().equals(prevLoc.toString())&& prevDirection!=1&&nextD==3)
        {
            if(solveMaze(solution, mc, maze,xPos,yPos-1,loc,3,0))
                return true;
            else{
                solution.remove(loc);
                mc.repaint();
                Thread.sleep(500);
                return false;
            }
        }
        System.out.println("Removing");
        solution.remove(loc);
        mc.repaint();
        Thread.sleep(100);
        return false;
    }

}
