public class PuzzleNode {
	private PuzzleState state;
	private PuzzleNode parent;
	private Movements move;
	private int depth;
	
	PuzzleNode( PuzzleState sts, PuzzleNode prt ){
		state = sts;
		parent = prt;
		depth = prt.depth + 1;
	}
	
	PuzzleNode( PuzzleState sts ){
		state = sts;
		parent = null;
		depth = 0;
		move = null;
	}
	
	public PuzzleState getState(){
		return state;
	}
	public PuzzleNode getParent(){
		return parent;
	}
	
}
