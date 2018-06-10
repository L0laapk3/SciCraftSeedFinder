abstract public class SeedFinder {
	protected final static long endSeed = 1L<<48;

	abstract public void findSeeds(long startSeed, int radius);
}
