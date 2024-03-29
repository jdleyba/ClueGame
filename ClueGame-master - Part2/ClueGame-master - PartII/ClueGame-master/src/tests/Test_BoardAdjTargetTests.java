package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		testList = board.getAdjList(6, 9);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(15,5);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(18,11);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(9,16);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(0,10);
		assertEquals(0, testList.size());
	}
	
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(15,13);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(15,14)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(21,7);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(21,6)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(14,18);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(15,18)));
		//TEST DOORWAY UP
		testList = board.getAdjList(16,17);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(15,17)));
		//TEST DOORWAY DOWN, WHERE THERE'S A WALKWAY TO THE RIGHT
		testList = board.getAdjList(4,14);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(16,17)));
	}
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(9,7);
		assertTrue(testList.contains(board.getCellAt(8,7)));
		assertTrue(testList.contains(board.getCellAt(10,7)));
		assertTrue(testList.contains(board.getCellAt(9,6)));
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(6,0);
		assertTrue(testList.contains(board.getCellAt(6,1)));
		assertTrue(testList.contains(board.getCellAt(5,0)));
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(0,19);
		assertTrue(testList.contains(board.getCellAt(0,18)));
		assertTrue(testList.contains(board.getCellAt(0,20)));
		assertTrue(testList.contains(board.getCellAt(1,19)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(15,17);
		assertTrue(testList.contains(board.getCellAt(15,16)));
		assertTrue(testList.contains(board.getCellAt(15,18)));
		assertTrue(testList.contains(board.getCellAt(16,17)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(0,9);
		assertTrue(testList.contains(board.getCellAt(1,9)));
		assertEquals(1, testList.size());
		
		// Test on left edge of board, just one walkway piece
		testList = board.getAdjList(13,0);
		assertTrue(testList.contains(board.getCellAt(13,1)));
		assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(13,4);
		assertTrue(testList.contains(board.getCellAt(13,3)));
		assertTrue(testList.contains(board.getCellAt(13,5)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(6,13);
		assertTrue(testList.contains(board.getCellAt(6,12)));
		assertTrue(testList.contains(board.getCellAt(6,14)));
		assertTrue(testList.contains(board.getCellAt(5,13)));
		assertTrue(testList.contains(board.getCellAt(7,13)));
		assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(21,15);
		assertTrue(testList.contains(board.getCellAt(20,15)));
		assertTrue(testList.contains(board.getCellAt(21,16)));
		assertEquals(2, testList.size());
		
		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(10,21);
		assertTrue(testList.contains(board.getCellAt(9,21)));
		assertTrue(testList.contains(board.getCellAt(11,21)));
		assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(4,15);
		assertTrue(testList.contains(board.getCellAt(3,15)));
		assertTrue(testList.contains(board.getCellAt(5,15)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(21,6,1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(20,6)));	
		
		board.calcTargets(11,21,1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(10,21)));
		assertTrue(targets.contains(board.getCellAt(12,21)));		
	}
	// Tests of just walkways, 2 steps
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsTwoSteps() {
			board.calcTargets(4,19,2);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(2, targets.size());
			assertTrue(targets.contains(board.getCellAt(2,19)));
			assertTrue(targets.contains(board.getCellAt(6,19)));
			
			board.calcTargets(15,14,2);
			targets= board.getTargets();
			assertEquals(3, targets.size());
			assertTrue(targets.contains(board.getCellAt(16,13)));
			assertTrue(targets.contains(board.getCellAt(14,13)));	
			assertTrue(targets.contains(board.getCellAt(15,16)));
			assertTrue(targets.contains(board.getCellAt(16,15)));
		}
		
		@Test
		public void testTargetsSixSteps() {
			board.calcTargets(13,3,6);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(7, targets.size());
			assertTrue(targets.contains(board.getCellAt(15,0)));
			assertTrue(targets.contains(board.getCellAt(16,1)));	
			assertTrue(targets.contains(board.getCellAt(15,2)));	
			assertTrue(targets.contains(board.getCellAt(17,2)));	
			assertTrue(targets.contains(board.getCellAt(16,3)));	
			assertTrue(targets.contains(board.getCellAt(17,4)));	
			assertTrue(targets.contains(board.getCellAt(16,5)));
			assertTrue(targets.contains(board.getCellAt(16,6)));
			assertTrue(targets.contains(board.getCellAt(12,8)));
			assertTrue(targets.contains(board.getCellAt(13,8)));
		}	
		public void testTargetsIntoRoom()
		{
			board.calcTargets(0,17,2);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(5, targets.size());
			assertTrue(targets.contains(board.getCellAt(0,15)));
			assertTrue(targets.contains(board.getCellAt(1,16)));
			assertTrue(targets.contains(board.getCellAt(2,17)));
			assertTrue(targets.contains(board.getCellAt(0,19)));
			assertTrue(targets.contains(board.getCellAt(1,18)));
		}
		@Test
		public void testTargetsIntoRoomShortcut() 
		{
			board.calcTargets(15,19,3);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(5, targets.size());
			assertTrue(targets.contains(board.getCellAt(15,16)));
			assertTrue(targets.contains(board.getCellAt(16,17)));
			assertTrue(targets.contains(board.getCellAt(16,19)));
			assertTrue(targets.contains(board.getCellAt(16,21)));
			assertTrue(targets.contains(board.getCellAt(14,21)));
		}
		@Test
		public void testRoomExit()
		{
			board.calcTargets(8,16, 1);
			Set<BoardCell> targets= board.getTargets();
			
			assertEquals(1, targets.size());
			assertTrue(targets.contains(board.getCellAt(7,17)));
			
			board.calcTargets(8,16, 1);
			targets= board.getTargets();
			
			assertEquals(1, targets.size());
			assertTrue(targets.contains(board.getCellAt(7,15)));
			assertTrue(targets.contains(board.getCellAt(7,17)));
		}
}
