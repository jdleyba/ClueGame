package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class Test_BoardAdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLayout.txt");		
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(7, 10);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(16,6);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(19,12);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(10,17);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(0,12);
		assertEquals(0, testList.size());
	}
}
