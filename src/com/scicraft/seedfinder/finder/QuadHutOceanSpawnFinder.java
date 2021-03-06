import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutOceanSpawnFinder extends QuadHutFinder {
	
	public QuadHutOceanSpawnFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}

		return checker.hasOceanSpawn(generator, radius, worldSpawn);
	}

}
