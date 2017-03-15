import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class BorderAStar extends Border{
	Heuristic heuristic;
	PriorityQueue<Pair<PuzzleNode,Integer>> queue;
	Set<PuzzleState> states;
	Comparator<Pair<PuzzleNode,Integer>> comp = new Comparator<Pair<PuzzleNode,Integer>>(){
		public int compare(Pair<PuzzleNode,Integer> c1, Pair<PuzzleNode,Integer> c2) {
	        return c1.getR().compareTo(c2.getR());
	      }
	};
	int maxSize;
	
	BorderAStar( Heuristic h ){
		heuristic = h;
		maxSize = 0;
		states = new HashSet<PuzzleState>();
		queue = new PriorityQueue<Pair<PuzzleNode, Integer>>( comp );
	}
	
	@Override
	public void add(PuzzleNode n) {
		if( states.contains( n.getState() ) )	return;
		queue.add( new Pair<PuzzleNode, Integer>( n, 1 + heuristic.eval( n ) ) );	//In 8-puzzle each movements costs 1
		states.add( n.getState() );
		maxSize = ( queue.size()>maxSize?queue.size():maxSize);
	}

	@Override
	public PuzzleNode get() {
		Pair<PuzzleNode,Integer> pair = queue.poll();
		PuzzleNode n = pair.getL();
		Integer v = pair.getR();
		states.remove( n.getState() );
		maxSize = ( queue.size()>maxSize?queue.size():maxSize);
		return n;
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public long getMaxSize() {
		// TODO Auto-generated method stub
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
