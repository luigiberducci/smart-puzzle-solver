import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
	private static PuzzleState initState;
	
	public static void  main( String[] args ) throws InterruptedException  
	{
		testBFG();
	}

	private static void testBFG() throws InterruptedException {
		int[] initVect = { 	
							0, 	1, 	3,
				 			4,	2,	5,
				 			7,  8,  6	
				 		};
		int dim = 3;
		//int dim = args[0];
		do{
			
			//initState = getRandomState( dim );
			initState = new PuzzleState( dim, initVect );
			System.out.println( "Input istance:" );
			System.out.println();
			initState.print();
			
			System.out.println( "Is it solvable?		" + ( initState.checkSolvable()?"Yes":"No" ) );
			System.out.println();
		}while( ! initState.checkSolvable() );
		
		SolverThread t_BFGreedy = new SolverThread( Algos.BestFirstGreedy_hTiles, initState );
		SolverThread t_AStar = new SolverThread( Algos.AStar_hTiles, initState );
		
		t_BFGreedy.start();
		t_AStar.start();
		t_AStar.join();
		t_BFGreedy.join();
		
		t_BFGreedy.printResult();
		t_AStar.printResult();
		
		System.out.println( "End" );		
	}

	private static void test0() {
		PuzzleState state;
		int dim = 3;
		PuzzleState initState = getRandomState( dim );
		Heuristic h = new H_TilesOutOfOrder();
		Border border = new BorderBestFirstGreedy( h );
		border.add( new PuzzleNode( initState ) );
		
		for( int i = 0; i < 100; i++ ){
			state= getRandomState( dim );
			border.add( new PuzzleNode( state ) );			
		}
		
		PuzzleNode n;
		while( ! border.isEmpty() ){
			n = border.get();
		}
	}
	
	private static void testAll() throws InterruptedException{
		//int[] initVect = {5,2,8,4,1,7,0,3,6};
				//int[] initVect = {1,2,3,4,5,6,0,8,7};	//Unsolvable
				//int[] initVect = {1,2,3,4,5,6,7,8,0};	
				int[] initVect = {3,1,2,0,4,5,6,7,8};
				//int[] initVect = {0,3,1,2};
				int dim = 3;
				//int dim = args[0];
				do{
					//initState = getRandomState( dim );
					initState = new PuzzleState( dim, initVect );
					System.out.println( "Input istance:" );
					System.out.println();
					initState.print();
					
					System.out.println( "Is it solvable?		" + ( initState.checkSolvable()?"Yes":"No" ) );
					System.out.println();
				}while( ! initState.checkSolvable() );
				
				SolverThread t_BFS = new SolverThread( Algos.BFS, initState );
				SolverThread t_DFS = new SolverThread( Algos.DFS, initState );
				SolverThread t_BFGreedy = new SolverThread( Algos.BestFirstGreedy_hTiles, initState );
				
				t_BFS.start();
				t_DFS.start();
				t_BFGreedy.start();
				t_BFS.join();
				t_DFS.join();
				t_BFGreedy.join();
				
				t_BFS.printResult();
				t_DFS.printResult();
				t_BFGreedy.printResult();
				
				System.out.println( "End" );
	}
		
	private static PuzzleState getRandomState( int dim ){
		Integer[]  vect = new Integer[dim*dim];
		for( int i=0; i<dim*dim; i++ ){
			vect[i] = i;
		}
		List<Integer> lst = Arrays.asList( vect );
		Collections.shuffle( lst );
		return new PuzzleState( dim, lst.toArray( vect ) );
	}

}
