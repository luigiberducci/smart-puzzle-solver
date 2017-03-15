import java.util.Collection;
import java.util.List;
import java.util.Set;


public abstract class Border {
	public abstract void add( PuzzleNode n );
	public abstract PuzzleNode get();
	public abstract boolean isEmpty();
	public abstract long getMaxSize();
	public abstract boolean contains( PuzzleState s );
}
