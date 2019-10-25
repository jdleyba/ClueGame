package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class FileInitTests {
	//put in proper lengths
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 22;
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException {

		board = Board.getInstance();
		//put in proper files
		board.setConfigFiles("ClueLayout.csv", "ClueLayout.txt");		
		board.initialize();
	}
	
	@Test
	public void testRooms() {
		//Replace with proper rooms
		Map<Character, String> legend = board.getLegend();
		Map<Character, String> expectedLegend = new HashMap<>();
		
		expectedLegend.put('C', "Coolbaugh");
		expectedLegend.put('Z', "Marquez");
		expectedLegend.put('T', "CTLM");
		expectedLegend.put('H', "HillHall");
		expectedLegend.put('K', "CoorsTek");
		expectedLegend.put('M', "MinesMarket");
		expectedLegend.put('B', "Berthoud");
		expectedLegend.put('S', "StrattonHall");
		expectedLegend.put('L', "Library");
		expectedLegend.put('X', "Closet");
		expectedLegend.put('W', "Walkway");
		
		assertEquals(LEGEND_SIZE, legend.size());
		
		for(Map.Entry<Character,String> entry: legend.entrySet()) {
			Character currentKey = entry.getKey();
			assertEquals(expectedLegend.get(currentKey), entry.getValue());
		}
	}
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(13, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRoomInitials() {
		// Test first cell in room
		assertEquals('B', board.getCellAt(4, 0).getInitial());
		assertEquals('S', board.getCellAt(10, 6).getInitial());
		assertEquals('L', board.getCellAt(15, 2).getInitial());
		// Test middle cells in the room
		assertEquals('K', board.getCellAt(2,12).getInitial());
		assertEquals('M', board.getCellAt(17,10 ).getInitial());
		assertEquals('H', board.getCellAt(4,17).getInitial());
		// Test last cell in room
		assertEquals('C', board.getCellAt(6,21).getInitial());
		assertEquals('Z', board.getCellAt(9,20).getInitial());
		assertEquals('T', board.getCellAt(19,19).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(6, 0).getInitial());
		// Test the closet
		assertEquals('X', board.getCellAt(9,12).getInitial());
	}
	
	@Test
	public void FourDoorDirections() {
		
		BoardCell room = board.getCellAt(9,6);
		assertTrue(room.isDoorway());
		assertTrue(DoorDirection.RIGHT.equals(room.getDoorDirection()));
		
		room = board.getCellAt(4, 14);
		assertTrue(room.isDoorway());
		assertTrue(DoorDirection.DOWN.equals(room.getDoorDirection()));
		
		room = board.getCellAt(16, 19);
		assertTrue(room.isDoorway());
		assertTrue(DoorDirection.LEFT.equals(room.getDoorDirection()));
		
		room = board.getCellAt(16, 17);
		assertTrue(room.isDoorway());
		assertTrue(DoorDirection.UP.equals(room.getDoorDirection()));
		
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(16, 10);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(11, 11);
		assertFalse(cell.isDoorway());		

	}
}
