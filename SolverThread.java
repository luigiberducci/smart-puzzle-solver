
public class SolverThread extends java.lang.Thread{
	Algos alg;
	PuzzleState initState;
	String output;
	
	SolverThread( Algos a, PuzzleState sts ){
		alg = a;
		initState = sts;
		output = "**Output of " + a + "**\n";
	}
	
	public void run(){
		Solver solver = new Solver( alg, initState);
		System.out.println("Starting "+ alg +"...\n");		
		if( solver.solve() ){
			output += "Result:			SOLVED\n";
			output += "Path cost:		" + solver.getSolution().seq.size() + "\n";
			output += "Path founded:		" + solver.getSolution().seq + "\n";
			output += "Nodes explored:		" + solver.getExploredSize() + "\n";
			output += "Max border size:	" + solver.getMaxBorderSize() + "\n\n";
		}
		else{
			output += "Result:			UNSOLVED\n\n";
		}
	}
	
	public void printResult(){
		System.out.print( output );
	}
}
