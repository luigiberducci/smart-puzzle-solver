public class PuzzleState {
	private int[] state;
	private static int DIM;
	
	PuzzleState( int dim, int[] numbers ){
		state = numbers;
		DIM = dim;
	}
	
	PuzzleState( PuzzleState ps ) {
		state = ps.getState().clone();
		DIM = ps.getDim();
	}

	public PuzzleState(int dim, Integer[] array) {
		state = new int[dim*dim];
		for( int i=0; i<dim*dim; i++ ){
			state[ i ] = Integer.parseInt( array[i].toString() );
		}
		DIM = dim;
	}

	public int[] getState(){
		return state;
	}
	
	public int getIndex( int v ){
		int i=0;
		while( i<DIM*DIM ){
			if( state[i] == v )
				break;
			i++;
		}
		return i;
	}
	boolean isSolution(){
		for(int i=0; i<DIM*DIM; i++){
			if( state[i] != (i+1)%(DIM*DIM) )
				return false;
		}
		return true;
	}
	
	boolean equals( PuzzleState ps ){
		for( int i=0; i<DIM*DIM; i++ ){
			if( state[i] != ps.getState()[i] ){
				return false;
			}
		}
		return true;
	}
	
	void print(){
		for(int i=0; i<DIM; i++ ){
			System.out.print("| ");

			for( int j=0; j<DIM; j++ ){
				System.out.print( state[ i * DIM + j ]);
				System.out.print(" ");
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println();
	}
	
	void switchPosition( int i, int j ){
		int k = state[i];
		state[i] = state[j];
		state[j] = k;		
	}
	
	public Movements getMoveTo( PuzzleState s ){
		Movements move = null;
		int i0 = -1;
		int i1 = -1; 	//Index of zeros in current state and state "s"
		
		for( int i=0; i<DIM*DIM; i++ ){
			if( this.state[i] == 0 )
				i0=i;
			if( s.state[i] == 0 )
				i1=i;
		}

		int row0 = i0/DIM;
		int row1 = i1/DIM;
		int col0 = i0%DIM;
		int col1 = i1%DIM;

		if( row0 != row1 && col0 != col1 ) return null;	//Not exist a move from current state to "s"
		if( row1 == row0 - 1 )
			move = Movements.Up;
		else if( row1 == row0 + 1 )
			move = Movements.Down;
		else if( col1 == col0 - 1 )
			move = Movements.Left;
		else if( col1 == col0 + 1 )
			move = Movements.Right;
		
		return move;		
	}

	public int getDim() {
		return DIM;
	}
	
	public boolean checkSolvable(){
        int numberOfInversions = 0;

        for( int i = 0; i < DIM*DIM; i++ ){
            for( int j = i+1; j < DIM*DIM; j++ ){
                if( state[i] != 0 && state[j] != 0 && state[i] > state[j] ){
                    numberOfInversions++;
                }
            }
        }

        if( numberOfInversions % 2 == 1 ){
            return false;
        }else{
            return true;
        }
    }	
}
