import com.scicraft.seedfinder.*;

public class ExtraCloseQuadHutFinder extends QuadHutFinder {
	public ExtraCloseQuadHutFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
	}
	protected int getCloseness() { return 1; }
}
