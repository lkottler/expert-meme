
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
