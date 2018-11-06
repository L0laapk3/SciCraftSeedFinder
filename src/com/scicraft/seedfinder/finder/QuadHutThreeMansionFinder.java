import java.lang.Math;
import com.scicraft.seedfinder.*;

public class QuadHutThreeMansionFinder extends QuadHutMultiMansionFinder {
	
	public QuadHutThreeMansionFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}
		return checker.mansionCount(mansion, seed, generator, radius) >= 3;
	}

}

