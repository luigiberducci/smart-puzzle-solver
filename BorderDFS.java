import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class BorderDFS extends Border {
	private Stack<PuzzleNode> nodes;
	private Set<PuzzleState> states;
	private long maxSize;
	
	BorderDFS(){
		nodes = new Stack();
		states = new HashSet<PuzzleState>();
		maxSize = 0;
	}
	
	@Override
	public void add(PuzzleNode n) {
		nodes.push( n );
		states.add( n.getState() );
		maxSize = ( nodes.size()>maxSize?nodes.size():maxSize);
	}

	@Override
	public PuzzleNode get() {
		PuzzleNode n = nodes.pop();
		states.remove( n.getState() );
		maxSize = ( nodes.size()>maxSize?nodes.size():maxSize);
		return n;
	}

	@Override
	public boolean isEmpty() {
		return nodes.isEmpty();
	}

	public Set<PuzzleState> getStates() {
		return states;
	}

	public List<PuzzleNode> getNodes() {
		return nodes;
	}

	@Override
	public long getMaxSize() {
		return maxSize;
	}

	@Override
	public boolean contains( PuzzleState ps0 ){
		for( PuzzleState ps1 : states ){
			if( ps0.equals( ps1 ) )
				return true;
		}
		return false;
	}

}
