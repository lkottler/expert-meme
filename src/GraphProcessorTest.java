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

	GraphProcessor graphP= new GraphProcessor();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testPopulateGraph() {
		Integer fill = graphP.populateGraph("wordlist.txt");
		Integer expected = 441;
		assertEquals("return value for number of words added", expected, fill);
	}
	@Test
	 public void testGetShortestPath() {
		 Integer fill = graphP.populateGraph("words.txt");
		 List<String> path = graphP.getShortestPath("cat", "wheat");
		 ArrayList<String> expected = new ArrayList<String>();
		 expected.add("cat");
		 expected.add("hat");
		 expected.add("heat");
		 expected.add("wheat");
		 assertEquals("shortest path", expected, path);
	 }
	@Test
	public void testGetShortestDistance() {
		Integer fill = graphP.populateGraph("words.txt");
		Integer dist= graphP.getShortestDistance("cat", "wheat");
		Integer expected = 3;
		assertEquals("shortest distance", expected, dist);
	}

	/*
	 * test populateGraph(filepath)
	 */

	/*
	 * test getShortestPath(word1, word2)
	 */

	/*
	 * test getShortestDistance(word1, word2)
	 */

}
