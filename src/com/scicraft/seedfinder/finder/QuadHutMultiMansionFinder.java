import java.lang.Math;
import com.scicraft.seedfinder.*;

public class QuadHutMultiMansionFinder extends QuadHutFinder {
	protected final WoodlandMansionLocator mansion;

	public QuadHutMultiMansionFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
		mansion = new WoodlandMansionLocator();
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}
		if (!checker.hasOceanSpawn(generator, radius, worldSpawn)) {
			return false;
		}
		return checker.mansionCount(mansion, seed, generator, radius) > 1;
	}
}
