
public class H_TilesOutOfOrder implements Heuristic{

	public int eval(PuzzleNode n) {
		int number = 0;
		PuzzleState sts = n.getState();
		int dim = sts.getDim();
		
		for( int i=0; i<dim*dim; i++){
			if( sts.getState()[ i ] != (i+1)%(dim*dim) )
				number++;
		}
		return number;
	}

}
