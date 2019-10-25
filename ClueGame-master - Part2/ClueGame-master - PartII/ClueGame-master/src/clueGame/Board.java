package clueGame;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//Will Hu, Josh Leyba
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
//Will Hu, Josh Leyba
public class Board {
	public final int MAX_BOARD_SIZE = 100;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	
	private String boardConfigFile;
	private String roomConfigFile;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	
	private Board() {
		
	}
	// this method returns the only Board
	
	public static Board getInstance() {
		return theInstance;
	}	
	
	public void initialize(){
		this.numRows = this.getNumRows();
		this.numColumns = this.getNumColumns();
		this.board = new BoardCell[numRows][numColumns];
		String getLetters = boardConfigFile.replace(",", "\n");
		Scanner letterScanner = new Scanner(getLetters);
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				BoardCell temp = new BoardCell(i,j);
				temp.initial = letterScanner.next();
				this.board[i][j] = temp; 
			}
		}
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathlength) {
		
	}

	public void setConfigFiles(String string, String string2) throws FileNotFoundException {
		FileReader boardReader = new FileReader(string);
		FileReader legendReader = new FileReader(string2);
		Scanner boardScanner = new Scanner(boardReader);
		Scanner legendScanner = new Scanner(legendReader);
		
		this.boardConfigFile = boardScanner.nextLine() + "\n";
		while(boardScanner.hasNext()) {
			this.boardConfigFile = this.boardConfigFile + boardScanner.nextLine() + "\n";
		}
		this.roomConfigFile = legendScanner.nextLine() + "\n";
		while(legendScanner.hasNext()) {
			this.roomConfigFile = this.roomConfigFile + legendScanner.nextLine() + "\n";
		}

	}

	public Map<Character, String> getLegend() {
		Scanner parseLegend = new Scanner(this.roomConfigFile);
		Map<Character, String> legend = new HashMap<Character, String>();
		
		while(parseLegend.hasNext()) {
			String s = parseLegend.next();
			
			if( s.length() == 2 && parseLegend.hasNext()) {
				char charKey = s.charAt(0);
				s = parseLegend.next();
				if(s.charAt(s.length()-1) == ',') {
					String valueString = s.substring(0, (s.length()-1));
					legend.put(charKey, valueString);
				}
				else {
					s = s + " " + parseLegend.next();
					String valueString = s.substring(0, (s.length()-1));
					legend.put(charKey, valueString);
				}
			}
		}
		return legend;
	}

	public int getNumRows() {
		Scanner parseRooms = new Scanner(this.boardConfigFile);
		int rowCount = 0;
		
		while(parseRooms.hasNextLine()) {
			rowCount ++;
			parseRooms.nextLine();
		}
		this.numRows = rowCount;
		return this.numRows;
	}

	public int getNumColumns() {
		Scanner parseRooms = new Scanner(this.boardConfigFile);
		String commaCheck = parseRooms.nextLine();;
		int commaCount = 0;
		
		for(int i = 0; i < commaCheck.length(); i++) {
			if(commaCheck.charAt(i) == ',') {
				commaCount++;
			}				
		}
		this.numColumns = commaCount + 1;
		return this.numColumns;
	}

	public BoardCell getCellAt(int i, int j) {
		return this.board[i][j];
	}
//	public static void main(String[] args) throws FileNotFoundException {
//		Board board = Board.getInstance();
//		board.setConfigFiles("ClueLayout.csv", "ClueLayout.txt");
//		board.initialize();
//		
//		
//		System.out.println(board.getCellAt(9,6).getDoorDirection());
//	}

	public Set<BoardCell> getAdjList(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public void calcTargets(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}
}
	
