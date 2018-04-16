import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node<E> {
	
	private E vertex;
	private List<Edge<E>> edges;
	private boolean isVisited;
	private int distanceFromSource;
	
	public Node(E vertex){
		this.vertex = vertex;
		this.edges = new ArrayList<>();
		distanceFromSource = Integer.MAX_VALUE;
	}
	
	public E vertex(){
		return vertex;
	}
	
	private void simpleAdd(Edge<E> edge){edges.add(edge);}
	
	public boolean addEdge(Node<E> node){
		if (hasEdge(node) || this == node) {
			return false;
		}
		Edge<E> newEdge = new Edge<E>(this, node);
		node.simpleAdd(newEdge);
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
				.filter(edge -> edge.isAdjacent(this, node))
				.findFirst();
	}
	
	public List<Edge<E>> edges()      {return edges;             }
	public int getEdgeCount()         {return edges.size();      }
	public boolean isVisited()        {return isVisited;         }
	public int getDistanceFromSource(){return distanceFromSource;}
	public List<Edge<E>> getEdges()   {return edges;             }
	
	public void setVisited(boolean isVisited)                {this.isVisited = isVisited;                  }
	public void setDistanceFromSource(int distanceFromSource){this.distanceFromSource = distanceFromSource;}
	public void setEdges(ArrayList<Edge<E>> edges)           {this.edges = edges;                          }
	
	
	
	
}
