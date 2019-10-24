package experiment;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//Will Hu, Josh Leyba
import java.util.Map;
import java.util.Set;
//Will Hu, Josh Leyba
public class IntBoard {
	
	private Map<BoardCell,Set<BoardCell>> adjMtx = new HashMap<BoardCell,Set<BoardCell>>();;
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private Set<BoardCell> visited= new HashSet<BoardCell>();
	private int BS = 4;
	private BoardCell[][] grid = new BoardCell[BS][BS];
	
	
	private void calcAdjacencies() {
		for(int i = 0; i < 4; i++) {
			for(int j =0; j < 4; j++) { // loops through every point
				HashSet<BoardCell> tempSet = new HashSet<BoardCell>();
				if(i+1 == 4) {  //These if else statements check for edges, if the cell isn't an edge it will add both sides for adjacencies.
					tempSet.add(grid[i-1][j]);
				}
				else if(i - 1 < 0) {
					tempSet.add(grid[i+1][j]);
				}
				else {
					tempSet.add(grid[i-1][j]);
					tempSet.add(grid[i+1][j]);
				}
				if(j+1 == 4) {
					tempSet.add(grid[i][j-1]);
				}
				else if(j - 1 < 0) {
					tempSet.add(grid[i][j+1]);
				}
				else {
					tempSet.add(grid[i][j-1]);
					tempSet.add(grid[i][j+1]);
				}
				HashSet<BoardCell> tempHold = tempSet;
				adjMtx.put(grid[i][j],tempHold);
			}
		}
	}
	public Set<BoardCell> getAdjList(BoardCell cell) {
		Set<BoardCell> adjList = adjMtx.get(cell);
		return adjList;
	}
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		
	}
	private void findAllTargets(BoardCell p, int a) {
		for(BoardCell s: adjMtx.get(p)) { //Loops through the adjMtx in accordance to the cell. It will see the targets for the cells
			if(!visited.contains(s)) {
				visited.add(s);
				if(a == 1) {
					targets.add(s);
				}
				else {
					findAllTargets(s, a - 1);
				}
				visited.remove(s);
			}
		}
	}
	
	public Set<BoardCell> getTargets() {
		return this.targets; //pretty self explanatory
	}
	public BoardCell getCell(int x, int y) { //Getter
		BoardCell currentCell = grid[x][y];
		return currentCell;
	}
	public BoardCell getCellInList(int x, int y) {
		BoardCell currentCell = new BoardCell(x,y);
		return currentCell;
	}
	public IntBoard(Set<BoardCell> targets) { //Setter
		super();
		this.targets = targets;
	}
	public IntBoard() { //Initializes the board by looping through and creating a cell at each point of the array.
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				BoardCell temp = new BoardCell(i,j);
				this.grid[i][j] = temp;
			}
		}
		this.calcAdjacencies();
		}
		
	}
	
