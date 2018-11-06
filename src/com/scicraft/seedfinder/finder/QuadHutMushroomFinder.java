import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutMushroomFinder extends QuadHutFinder {
	
	public QuadHutMushroomFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
	}

	private static final float MUSHROOM_FRACTION = 0.018f;
	private static final Biome[] MUSHROOM_BIOMES = {
		Biome.mushroomIsland,
		Biome.mushroomIslandShore,
		Biome.mushroomIslandM,
		Biome.mushroomIslandShoreM,
	};

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}

		if (!checker.hasOceanSpawn(generator, radius, worldSpawn)) {
			return false;
		}

		return checker.hasBiomes(
				generator, radius*32*16*2, worldSpawn.getX(), worldSpawn.getZ(),
				MUSHROOM_BIOMES, checker.WATER_BIOMES, MUSHROOM_FRACTION);
	}
}
