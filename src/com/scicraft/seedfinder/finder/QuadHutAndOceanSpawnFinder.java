import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutAndOceanSpawnFinder extends QuadHutFinder {
	private static final float WATER_FRACTION = 0.80f;
	private static final Biome[] WATER_BIOMES = {
		Biome.ocean,
		Biome.deepOcean,
		Biome.frozenOcean,
		Biome.oceanM,
		Biome.deepOceanM,
		Biome.frozenOceanM,
	};

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!quadHutsWillSpawn(chunkLocations, generator)) {
			return false;
		}

		Hashtable<Integer, Float> census = generator.biomeCensus(
				worldSpawn.getX(), worldSpawn.getZ(), SPAWN_SIZE, SPAWN_SIZE, true);
		float waterFraction = 0f;
		for (Biome biome : WATER_BIOMES) {
			waterFraction += census.get(biome.index);
		}
		return waterFraction >= WATER_FRACTION;
	}
}
