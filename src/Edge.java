//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Edge.java
// Due Date:		April 16, 2018
// Course:          CS 400, Spring, 2018
//
// Author:          Logan Kottler, Neeshan Khanikar, Kevin Kemp, Abby Kisicki
// Email:           lkottler@wisc.edu, khanikar@wisc.edu, kkemp3@wisc.edu, kisicki@wisc.edu
// Lecturer's Name: Deb Deppeler
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class Edge<E> {

	private Node<E> node1;
	private Node<E> node2;
	
	public Edge(Node<E> node1, Node<E> node2){
		this.node1 = node1;
		this.node2 = node2;
	}
	
	public boolean isAdjacent(Node<E> node1, Node<E> node2){
		return (this.node1 == node1 && this.node2 == node2);
	}
	
}
