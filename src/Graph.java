//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Graph.java
// Due Date:		April 16, 2018
// Course:          CS 400, Spring, 2018
//
// Author:          Logan Kottler, Neeshan Khanikar, Kevin Kemp, Abby Kisicki
// Email:           lkottler@wisc.edu, khanikar@wisc.edu, kkemp3@wisc.edu, kisicki@wisc.edu
// Lecturer's Name: Deb Deppeler
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E>
 *            type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {

	/**
	 * Instance variables and constructors
	 */
	private final HashMap<E, Node<E>> adjacencyList;

	Graph() {
		adjacencyList = new HashMap<>();
	}

	/*
	 * Adds a Node to the HashMap.
	 */
	public E addVertex(E vertex) {
		// Check that the node being added is not already in the graph.
		if (vertex == null || containsNode(vertex))
			return null;
		adjacencyList.put(vertex, new Node<E>(vertex));
		return vertex;
	}

	/*
	 * Removes a Node from the graph and all associated edges.
	 */
	public E removeVertex(E vertex) {
		// Check if node exists
		if (!containsNode(vertex))
			return null;

		// Fetch node
		final Node<E> toRemove = getNode(vertex);

		// removes all edges of the node being removed.
		adjacencyList.values().forEach(node -> node.removeEdge(toRemove));

		// removes the node
		adjacencyList.remove(vertex);
		return vertex;
	}
	// These functions use the Edge class to call their respective roles.
	public boolean addEdge(E vertex1, E vertex2) {
		if (!containsNode(vertex1) || !containsNode(vertex2)){
			return false;
		}
		return getNode(vertex1).addEdge(getNode(vertex2));
	}
	
	
	// Removes an edge between two vertices if they are in the graph
	public boolean removeEdge(E vertex1, E vertex2) {
		if (!containsNode(vertex1) || !containsNode(vertex2)){
			return false;
		}
		return getNode(vertex1).removeEdge(getNode(vertex2));
	}

	// Checks if two vertices are adjacent by using the hasEdge method from the Node class
	public boolean isAdjacent(E vertex1, E vertex2) {
		if (!containsNode(vertex1) || !containsNode(vertex2)){
			return false;
		}
		return getNode(vertex1).hasEdge(getNode(vertex2));
	}

	// This functions does nothing other than re-define how to call a function.
	private boolean containsNode(E vertex) {
		return adjacencyList.containsKey(vertex);
	}

	private Node<E> getNode(E value) {
		return adjacencyList.get(value);
	}

	/*
	 * TODO this method could be fixed, I'm not sure how to do this one correctly. I
	 * am not very familiar with the Iterable or Streams This will also include
	 * itself it self-edges may exist.
	 */
	public Iterable<E> getNeighbors(E vertex) {
		if (vertex == null ) {
			throw new NullPointerException();
		}
		List<E> list = new ArrayList<E>();
		for (Node<E> node : adjacencyList.values()) {
			if (getNode(vertex).hasEdge(node) && !(vertex.equals(node)))
				list.add(node.vertex());
		}
		return list;
	}

	// TODO There is a lambda function that will make this more efficient I am sure.
	public Iterable<E> getAllVertices() {
		List<E> list = new ArrayList<E>();
		for (Node<E> node : adjacencyList.values())
			list.add(node.vertex());
		return list;
	}

}
