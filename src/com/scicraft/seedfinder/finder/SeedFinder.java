import com.scicraft.seedfinder.*;

abstract public class SeedFinder {
	protected final static long endSeed = 1L<<48;
	protected final StuffChecker checker;

	abstract public void findSeeds(long startSeed, int radius);

	public SeedFinder() {
		checker = new StuffChecker();
	}
}
