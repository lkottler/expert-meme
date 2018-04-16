import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Test;

/*/
 * Junit class to test the implementation of the GraphProcessor class.
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
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopulateGraph() {
		Integer fill = graphP.populateGraph("wordlist.txt");
		assertEquals(fill, null);
			
	}
	/*
	/*
	 * test getShortestPath(word1, word2)
	 */

	/*
	 * test getShortestDistance(word1, word2)
	 */

}
