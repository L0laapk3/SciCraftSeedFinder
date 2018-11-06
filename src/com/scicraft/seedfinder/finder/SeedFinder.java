import com.scicraft.seedfinder.*;

import java.lang.Thread;

abstract public class SeedFinder extends Thread {
	protected final static long endSeed = 1L<<48;
	protected final StuffChecker checker;


	protected final long startSeed;
	protected final int radius;
	protected final int threadCount;

	public SeedFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		this.startSeed = startSeed + threadNumber;
		this.radius = radius;
		this.threadCount = threadCount;
		
		this.checker = new StuffChecker();
		
		System.out.printf(
			"Thread %d/%d, Start seed: %d, Radius: %d regions (%d chunks, %d blocks)\n",
			 threadNumber+1, threadCount, this.startSeed, radius, radius*32, radius*32*16);
	}

	public void run() {
		try {
			findSeeds(startSeed, radius, threadCount);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}


	abstract protected void findSeeds(long startSeed, int radius, int threadCount);
}
