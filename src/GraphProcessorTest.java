//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           GraphProcessorTest.java
// Due Date:		April 16, 2018
// Course:          CS 400, Spring, 2018
//
// Author:          Logan Kottler, Neeshan Khanikar, Kevin Kemp, Abby Kisicki
// Email:           lkottler@wisc.edu, khanikar@wisc.edu, kkemp3@wisc.edu, kisicki@wisc.edu
// Lecturer's Name: Deb Deppeler
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*/
 * JUnit class to test the implementation of the GraphProcessor class.
 * See @GraphProcessor
 */
public class GraphProcessorTest {

	// instance variables here

	GraphProcessor graphP;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		this.graphP = new GraphProcessor();
	}

	@After
	public void tearDown() throws Exception {
		this.graphP = null;
	}

	
	/*
	 * test populateGraph(filepath)
	 */
	@Test
	public final void testPopulateGraph() {
		Integer fill = graphP.populateGraph("wordlist.txt");
		Integer expected = 441;
		assertEquals("return value for number of words added", expected, fill);
	}
	
	
	/*
	 * test getShortestPath(word1, word2) by comparing it to an ArrayList that was manually implemented
	 */
	@Test
	 public final void testGetShortestPath() {
		 Integer fill = graphP.populateGraph("words.txt");
		 List<String> path = graphP.getShortestPath("cat", "wheat");
		 ArrayList<String> expected = new ArrayList<String>();
		 expected.add("cat");
		 expected.add("hat");
		 expected.add("heat");
		 expected.add("wheat");
		 assertEquals("shortest path", expected, path);
	 }
	
	
	/*
	 * test getShortestDistance(word1, word2)
	 */
	@Test
	public final void testGetShortestDistance() {
		Integer fill = graphP.populateGraph("words.txt");
		Integer dist = graphP.getShortestDistance("cat", "wheat");
		Integer expected = 3;
		assertEquals("shortest distance", expected, dist);
	}

}
