import com.scicraft.seedfinder.*;
import java.util.*;

public class StuffChecker {
	private static final int SPAWN_SIZE = 256;
	private static final int SPAWN_AREA = 256*256;

	private static final float WATER_FRACTION = 0.80f;
	public static final Biome[] WATER_BIOMES = {
		Biome.ocean,
		Biome.deepOcean,
		Biome.frozenOcean,
		Biome.oceanM,
		Biome.deepOceanM,
		Biome.frozenOceanM,
	};

	public boolean quadHutsWillSpawn(
			WitchHutLocator hut, XZPair[] chunkLocations, BiomeGenerator generator) {
		// Only iterate through first four structures so that subclasses can
		// have additional ones more easily.
		for (int i=0; i<4; i++) {
			XZPair location = chunkLocations[i];
			if (!hut.structureWillSpawn(location, generator)) {
				return false;
			}
		}
		return true;
	}

	public boolean hasBiomes(
			BiomeGenerator generator, int size, int centerX, int centerZ,
			Biome[] biomes, Biome[] excluded, float minFraction) {
		Hashtable<Integer, Float> census = generator.biomeCensus(
				centerX, centerZ, size, size, true);

		float exFraction = 0f;
		if (excluded != null) {
			for (Biome ex : excluded) {
				exFraction += census.get(ex.index);
			}
		}
		if (exFraction > 0.90f) { exFraction = 0.90f; }

		float fraction = 0f;
		for (Biome biome : biomes) {
			fraction += census.get(biome.index);
		}

		return fraction / (1f - exFraction) >= minFraction;
	}

	public boolean hasBiomes(
			BiomeGenerator generator, int size, Biome[] biomes, float minFraction) {
		return hasBiomes(generator, size, 0, 0, biomes, null, minFraction);
	}

	public boolean hasBiomes(
			BiomeGenerator generator, int size, int centerX, int centerZ, Biome[] biomes, float minFraction) {
		return hasBiomes(generator, size, centerX, centerZ, biomes, null, minFraction);
	}

	public boolean hasBiomes(
			BiomeGenerator generator, int size, Biome[] biomes, Biome[] excluded, float minFraction) {
		return hasBiomes(generator, size, 0, 0, biomes, excluded, minFraction);
	}

	public boolean hasOceanSpawn(
			BiomeGenerator generator, int radius, XZPair worldSpawn) {
		return hasBiomes(generator, SPAWN_SIZE, WATER_BIOMES, WATER_FRACTION);
	}

	public int mansionCount(
			 WoodlandMansionLocator mansion, long seed, BiomeGenerator generator, int witchHutRadius) {
		int mansionRadius = (int)Math.ceil((float)witchHutRadius*32f / 80f);

		int mansionCount = 0;
		for (int regionX=-mansionRadius; regionX<mansionRadius; regionX++) {
			for (int regionZ=-mansionRadius; regionZ<mansionRadius; regionZ++) {
				if (mansion.structureWillSpawn(regionX, regionZ, seed, generator)) {
					mansionCount++;
				}
			}
		}

		return mansionCount;
	}

}
