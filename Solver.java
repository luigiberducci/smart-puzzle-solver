import java.util.HashSet;
import java.util.Set;


public class Solver {
	private PuzzleSolution solution;
	private Border border;
	private Set<PuzzleState> explored;
	private PuzzleState initState;	//TODO: implements random initialization
	private int DIM;
	
	/**
	 * Initialization of solver agent
	 */
	Solver( Algos alg, PuzzleState initSts ){
		solution = new PuzzleSolution();
		explored = new HashSet();
		initState = initSts;
		DIM = initState.getDim();
		//Border initialization depends on type of research algo
		switch( alg ){
		case BFS:
			border = new BorderBFS();
			break;
		case DFS:
			border = new BorderDFS();
			break;
		case BestFirstGreedy_hTiles:
			border = new BorderBestFirstGreedy( new H_TilesOutOfOrder() );
			break;
		case AStar_hTiles:
			border = new BorderAStar( new H_TilesOutOfOrder() );
			break;
		}
		border.add( new PuzzleNode( initState ) );
	}
	
	boolean solve(){
		while(true){
			if(border.isEmpty())	return false;
			
			PuzzleNode n = border.get();
			explored.add( n.getState() );
			
			if( n.getState().isSolution() ){
				solution.calculateSolution( n );	//Calculate the solution from initState to "n"
				return true;
			}
			else{
				for( Movements move : Movements.values() ){
					if( checkMove( n.getState(), move ) ){	//If the move is legal
						PuzzleState new_state = toMove( n.getState(), move );	// do the move
						if( !( isAlreadyExplored( new_state ) || border.contains( new_state ) ) ){
								border.add( new PuzzleNode( new_state, n ) );
							}
					}					
				}
			}			
		}
	}
	
	PuzzleSolution getSolution(){
		return solution;
	}
	
	boolean checkMove( PuzzleState state, Movements move ){
		int i_zero = state.getIndex( 0 );
		int row_zero = i_zero / DIM;
		int col_zero = i_zero % DIM;
		
		switch( move ){
		case Up:
			if( row_zero == 0 )
				return false;
			break;
		case Down:
			if( row_zero == DIM-1 )
				return false;
			break;
		case Left:
			if( col_zero == 0 )
				return false;
			break;
		case Right:
			if( col_zero == DIM-1 )
				return false;
			break;
		}
		return true;
	}
	
	boolean isAlreadyExplored( PuzzleState ps0 ){
		for( PuzzleState ps1 : explored ){
			if( ps0.equals( ps1 ) )
				return true;
		}
		return false;
	}
	
	PuzzleState toMove( PuzzleState state, Movements move ){
		int i_zero = state.getIndex( 0 );
		int i_value = state.getIndex( 0 );	//initialization index of value to change with zero
		PuzzleState new_state = new PuzzleState( state );
		int row_zero = i_zero / DIM;
		int col_zero = i_zero % DIM;
		
		switch( move ){
		case Up:
			i_value = ( row_zero-1 ) * DIM + col_zero;
			break;
		case Down:
			i_value = ( row_zero + 1 ) * DIM + col_zero;
			break;
		case Left:
			i_value = row_zero * DIM + ( col_zero - 1 ); 
			break;
		case Right:
			i_value = row_zero * DIM + ( col_zero + 1 );
			break;
		}
		new_state.switchPosition( i_zero, i_value );
		return new_state;
	}

	public int getExploredSize() {
		return explored.size();
	}
	
	
	public long getMaxBorderSize(){
		return border.getMaxSize();		
	}
	
}
