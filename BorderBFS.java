import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class BorderBFS extends Border {
	public LinkedList<PuzzleNode> nodes;
	public Set<PuzzleState> states;
	public long maxSize;
	
	BorderBFS(){
		nodes = new LinkedList<PuzzleNode>();
		states = new HashSet<PuzzleState>();
		maxSize = 0;
	}
	
	
	@Override
	public void add(PuzzleNode n) {
		states.add( n.getState() );
		nodes.add( n );
		maxSize = ( nodes.size()>maxSize?nodes.size():maxSize);
	}

	@Override
	public PuzzleNode get() {
		PuzzleNode n = nodes.poll();
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
	public boolean contains( PuzzleState ps0 ){
		for( PuzzleState ps1 : states ){
			if( ps0.equals( ps1 ) )
				return true;
		}
		return false;
	}

	@Override
	public long getMaxSize() {
		return maxSize;
	}
}
