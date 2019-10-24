package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import experiment.BoardCell;
import experiment.IntBoard;
//Will Hu, Josh Leyba
public class IntBoardTests extends JUnitCore{
	private IntBoard board;
	@Before
	public void beforeAll() {
		board = new IntBoard();
	}
	
	@Test
	public void testAdj0_0() { //Tests adjacencies for 0,0
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdj3_3() {//Tests adjacencies for 3,3
		BoardCell cell = board.getCell(3, 3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2,3)));
		assertTrue(testList.contains(board.getCell(3,2)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdj1_3() {//Tests adjacencies for 1,3
		BoardCell cell = board.getCell(1, 3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0,3)));
		assertTrue(testList.contains(board.getCell(2,3)));
		assertTrue(testList.contains(board.getCell(1,2)));
		assertEquals(3, testList.size());
	}
	@Test
	public void testAdj1_0() {//Tests adjacencies for 1,0
		BoardCell cell = board.getCell(1, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0,0)));
		assertTrue(testList.contains(board.getCell(1,1)));
		assertTrue(testList.contains(board.getCell(2,0)));
		assertEquals(3, testList.size());
	}
	@Test
	public void testAdj1_1() {//Tests adjacencies for 1,1
		BoardCell cell = board.getCell(1, 1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0,1)));
		assertTrue(testList.contains(board.getCell(1,0)));
		assertTrue(testList.contains(board.getCell(2,1)));
		assertTrue(testList.contains(board.getCell(1,2)));
		assertEquals(4, testList.size());
	}
	@Test
	public void testAdj2_2() {//Tests adjacencies for 2,2
		BoardCell cell = board.getCell(2, 2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1,2)));
		assertTrue(testList.contains(board.getCell(2,1)));
		assertTrue(testList.contains(board.getCell(2,3)));
		assertTrue(testList.contains(board.getCell(3,2)));
		assertEquals(4, testList.size());
	}
	@Test
	public void testTargets0_0() { //Tests targets of 0,0
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(targets.size(),3);
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
	}
	@Test
	public void testTargets4_4() { //Tests targets of 4,4
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 4);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(2,0)));
		assertTrue(targets.contains(board.getCell(0,2)));
		assertTrue(targets.contains(board.getCell(1,1)));
		assertTrue(targets.contains(board.getCell(2,2)));
		assertTrue(targets.contains(board.getCell(3,1)));
		assertTrue(targets.contains(board.getCell(1,3)));
		
	}
	@Test
	public void testTargets1_0() {
		BoardCell cell = board.getCell(1, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0,0)));
		assertTrue(targets.contains(board.getCell(2,0)));
		assertTrue(targets.contains(board.getCell(0,2)));
		assertTrue(targets.contains(board.getCell(1,1)));
		assertTrue(targets.contains(board.getCell(2,2)));
		assertTrue(targets.contains(board.getCell(3,1)));
		assertTrue(targets.contains(board.getCell(1,3)));
		
	}
	@Test
	public void testTargets1_1() {
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(1,2)));
		assertTrue(targets.contains(board.getCell(2,1)));
		assertTrue(targets.contains(board.getCell(0,1)));
		assertTrue(targets.contains(board.getCell(1,0)));
		
	}
	@Test
	public void testTargets2_2() {
		BoardCell cell = board.getCell(2, 2);
		board.calcTargets(cell, 5);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(0,1)));
		assertTrue(targets.contains(board.getCell(0,3)));
		assertTrue(targets.contains(board.getCell(1,0)));
		assertTrue(targets.contains(board.getCell(1,2)));
		assertTrue(targets.contains(board.getCell(2,1)));
		assertTrue(targets.contains(board.getCell(2,3)));
		assertTrue(targets.contains(board.getCell(3,0)));
		assertTrue(targets.contains(board.getCell(3,2)));
		
	}
	@Test
	public void testTargets3_3() {
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(2,3)));
		assertTrue(targets.contains(board.getCell(3,2)));
		
	}
	public static void main(String[] args) {
		
	}

}
