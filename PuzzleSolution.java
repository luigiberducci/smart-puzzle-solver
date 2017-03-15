import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PuzzleSolution {
	List<Movements> seq;
	
	PuzzleSolution(){
		seq = new ArrayList();
	}
	public void add( Movements move ){
		seq.add( move );
	}
	public void calculateSolution( PuzzleNode finalNode ){
		PuzzleNode childNode = finalNode;
		PuzzleNode parentNode = finalNode.getParent();
		while( parentNode != null ){
			seq.add( 0, parentNode.getState().getMoveTo( childNode.getState() ) );	//Add the move at first position
			childNode = parentNode;
			parentNode = parentNode.getParent();
		}
	}
}
