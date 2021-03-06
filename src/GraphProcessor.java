//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           GraphProcessor.java
// Due Date:		April 16, 2018
// Course:          CS 400, Spring, 2018
//
// Author:          Logan Kottler, Neeshan Khanikar, Kevin Kemp, Abby Kisicki
// Email:           lkottler@wisc.edu, khanikar@wisc.edu, kkemp3@wisc.edu, kisicki@wisc.edu
// Lecturer's Name: Deb Deppeler
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {

    /**
     * Graph which stores the dictionary words and their associated connections
     */
    private GraphADT<String> graph;
    private HashMap<String, HashMap<String, Integer>> distances; //HashMap<Source, HashMap<Target, Distance>>
	private HashMap<String, HashMap<String, String>> previous;   //HashMap<Source, HashMap<Target, Previous>>

    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
        distances = new HashMap<String, HashMap<String, Integer>>();
        previous = new HashMap<String, HashMap<String, String>>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     */
    public Integer populateGraph(String filepath) {
        try {
//			Stream<String> file = WordProcessor.getWordStream(filepath);
//			for (String s : file..forEach(this.graph.addVertex(s)));

        		String[] words = WordProcessor.getWordStream(filepath).toArray(String[]::new);
        		
        		
        		for (int i = 0; i < words.length; i++) {
        			this.graph.addVertex(words[i]);
        		}
        		for (int i = 0; i < words.length; i++) {
        			for (int j = 0; j < words.length; j++) {
        				if (i != j) {
        					if (graph.isAdjacent(words[i], words[j])) {
        						graph.addEdge(words[i], words[j]);
        					}
        				}
        			}
        		}
        		shortestPathPrecomputation();
            	return words.length;
        		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
  
    }

    
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
    
    public List<String> getShortestPath(String word1, String word2) {
    	
    	List<String> path = new ArrayList<String>();
    	String currWord = word2;
    	while (currWord != null){
    		path.add(currWord);
    		currWord = previous.get(word1).get(word2);
    	}
    	Collections.reverse(path);
        return path;
    }
    
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     *   = 3 (the number of edges in the shortest path)
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
    public Integer getShortestDistance(String word1, String word2) {
        return distances.get(word1).get(word2);
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {
    	HashMap<String, HashMap<String, Integer>> distances = new HashMap<String, HashMap<String, Integer>>(); //HashMap<Source, HashMap<Target, Distance>>
    	HashMap<String, HashMap<String, String>> previous = new HashMap<String, HashMap<String, String>>();   //HashMap<Source, HashMap<Target, Previous>>
    	Iterable<String> nodeList = graph.getAllVertices();
    	
    	for (String node : nodeList) {
	    	HashSet<String> vertices = new HashSet<String>();
	    	HashMap<String, Integer> currNodeDistances = new HashMap<String, Integer>();
	    	HashMap<String, String> currNodePrevious = new HashMap<String, String>();
	    	
	    	for (String s: nodeList){
		    	currNodeDistances.put(s, Integer.MAX_VALUE);
		    	currNodePrevious.put(s, null);
		    	vertices.add(s);
	    	}
	    	
	    	currNodeDistances.replace(node, 0);  //Distance to source is 0.
	    	
	    	while(!vertices.isEmpty()){
	    		String minWord = node; // minWord represents the node with the shortest distance to the source.
	    		for (String s: vertices){
	    			minWord = (currNodeDistances.get(s) < currNodeDistances.get(minWord)) ? s : minWord;
	    		}
	    		vertices.remove(minWord);
	    		
	    		for (String s: graph.getNeighbors(minWord)){
	    			int altPath = currNodeDistances.get(minWord) + 1;
	    			if (altPath < currNodeDistances.get(s)){
	    				currNodeDistances.replace(s, altPath);
	    				currNodePrevious.replace(s, minWord);
	    			}
	    		}
	    	}
	    	distances.put(node, currNodeDistances);
	    	previous.put(node, currNodePrevious);
    	}
    }
}
