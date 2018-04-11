import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node<E> {
	
	private E vertex;
	private List<Edge<E>> edges;
	private boolean isVisited;
	
	public Node(E vertex){
		this.vertex = vertex;
		this.edges = new ArrayList<>();
	}
	
	public E vertex(){
		return vertex;
	}
	
	public boolean addEdge(Node<E> node){
		if (hasEdge(node)) {
			return false;
		}
		Edge<E> newEdge = new Edge<>(this, node);
		return edges.add(newEdge);
	}
	
	public boolean removeEdge(Node<E> node) {
		Optional<Edge<E>> optional = findEdge(node);
		if (optional.isPresent()) {
			return edges.remove(optional.get());
		}
		return false;
	}
	
	
	public boolean hasEdge(Node<E> node) {
		return findEdge(node).isPresent();
	}
	
	public Optional<Edge<E>> findEdge(Node<E> node) {
		return edges.stream()
				.filter(edge -> edge.isAdjacent(this,  node))
				.findFirst();
	}
	
	public List<Edge<E>> edges() {return edges;       }
	public int getEdgeCount()    {return edges.size();}
	public boolean isVisited()   {return isVisited;}
	
	public void setVisited(boolean isVisited){ this.isVisited = isVisited;}
	
	
	
}
